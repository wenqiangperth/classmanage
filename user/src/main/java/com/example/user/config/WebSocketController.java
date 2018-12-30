package com.example.user.config;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1：下一组展示
 * 2：抽取提问
 * 3：提问
 * @author ren
 */

@ServerEndpoint("/websocket/{klassseminarId}/{userId}/{role}")
@Component
public class WebSocketController {

    private final static String STUDENTROLE="ROLE_STUDENT";
    private final static String NEXTGROUP="下一组展示";
    private final static String CHOOSEQUESTION="抽取提问";
    private final static String ASKQUESTION="提问";

    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
    private Session session;
    Long seminarKlassId;
    Long userId;

    String role;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("klassseminarId") Long seminarKlassId, @PathParam("role")String role, @PathParam("userId")Long userId) throws IOException, EncodeException {

        this.userId=userId;
        this.role=role;
        this.seminarKlassId=seminarKlassId;
        this.session = session;
        webSocketSet.add(this);
        System.out.println("连接成功");
        //加入set中
        addOnlineCount();
        StringBuffer stringBuffer=new StringBuffer("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        //从set中删除

        subOnlineCount();
        //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }


    /**
     * 收到客户端消息后调用的方法
     * 1.下一个展示
     * 2.下一个提问
     * 3.结束讨论课
     * 提问、当前展示小组为：xxx、抽取提问
     * @param */
    @OnMessage
    public void onMessage(String message,Session session) throws IOException, EncodeException {
        System.out.println("消息："+message+this.getSeminarKlassId());

        if(message.equals(NEXTGROUP)){
            for (WebSocketController webSocketController:webSocketSet
                 ) {
                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
                    if (webSocketController.getRole().equals(STUDENTROLE)) {
                        webSocketController.sendMessage(NEXTGROUP);
                    }
                }
            }
        }else if(message.equals(ASKQUESTION)){
            for (WebSocketController webSocketController:webSocketSet
                 ) {
                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
                    webSocketController.sendMessage(ASKQUESTION);
                }
            }
        }else if(message.startsWith(CHOOSEQUESTION)){
            Long studentId=Long.parseLong(message.replace(CHOOSEQUESTION,""));
            for (WebSocketController webSocketController:webSocketSet
                 ) {
                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
                    if (webSocketController.getUserId().equals(studentId)) {
                        webSocketController.sendMessage("请您开始提问");
                    } else {
                        webSocketController.sendMessage("很遗憾，您未被选中，请您耐心等待！");
                    }
                }
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public void sendObject(Object object) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(object);
    }

    @Override
    public int hashCode(){
        return role.hashCode()+seminarKlassId.hashCode()+session.hashCode();
    }

    @Override
    public boolean equals(Object object){
        return this.seminarKlassId.equals(((WebSocketController)object).seminarKlassId) && this.userId.equals(((WebSocketController)object).userId);
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

    public Long getSeminarKlassId() {
        return seminarKlassId;
    }

    public void setSeminarKlassId(Long seminarKlassId) {
        this.seminarKlassId = seminarKlassId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
