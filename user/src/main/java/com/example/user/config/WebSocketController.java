package com.example.user.config;


import com.example.common.dao.TeamDao;
import com.example.common.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ren
 */

@ServerEndpoint("/websocket/{seminarKlassId}/{userId}/{role}")
@Component
public class WebSocketController {
    @Autowired
    private TeamDao teamDao;

    private static int onlineCount = 0;
    private static Map<Long,Long> questionNum=new HashMap<>();
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
    private Session session;
    Long seminarKlassId;
    Long userId;
    Long teamId;

    String role;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("seminarKlassId") Long seminarKlassId,@PathParam("role")String role,@PathParam("userId")Long userId) throws IOException, EncodeException {
        this.userId=userId;
        this.role=role;
        this.seminarKlassId=seminarKlassId;
        this.session = session;
        webSocketSet.add(this);
        System.out.println("连接成功");
        //加入set中
        addOnlineCount();
        StringBuffer stringBuffer=new StringBuffer("连接成功");
        sendMessage("200"+"perth"+onlineCount);
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
        System.out.println(message);
        if(message.equals("提问")){
            for(WebSocketController webSocketController:webSocketSet){
                webSocketController.sendMessage("1");
            }
        }else if(message.equals("1")){
            for(WebSocketController webSocketController:webSocketSet){
                webSocketController.sendMessage("2");
            }
        }else{
            sendMessage("系统出错");
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

    public void sendObject(Object object){

    }
    /**
     * 群发自定义消息
     * */
//    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
//        System.out.println("推送消息到窗口"+"，推送内容:"+message);
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

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
