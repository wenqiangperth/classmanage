package com.example.user.config;


import com.example.common.dao.TeamDao;
import com.example.common.entity.Attendance;
import com.example.common.entity.Question;
import com.example.common.entity.Team;
import com.example.common.mapper.QuestionMapper;
import com.example.user.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    private final static String studentRole="ROLE_STUDENT";
    private final static String nextGroup="下一组展示";
    private final static String chooseQuestion="抽取提问";
    private final static String askQuestion="提问";
//    private final static String nowQuestionNum="当前提问人数";

    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
    private Session session;
    Long seminarKlassId;
    Long userId;
//    Long teamId;
//    boolean isAsked;

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
        System.out.println(message);

        if(message.equals(nextGroup)){
            for (WebSocketController webSocketController:webSocketSet
                 ) {
                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
                    if (webSocketController.getRole().equals(studentRole)) {
                        webSocketController.sendMessage(nextGroup);
                    }
                }
            }
        }else if(message.equals(askQuestion)){
            for (WebSocketController webSocketController:webSocketSet
                 ) {
                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
                    webSocketController.sendMessage(askQuestion);
                }
            }
        }else if(message.startsWith(chooseQuestion)){
            Long studentId=Long.parseLong(message.replace(chooseQuestion,""));
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



        //         if(message.equals("1")){
//         //   questionNum.put(this.getSeminarKlassId(),0L);
//            //Long attendanceId=Long.parseLong(message.replace("当前展示小组",""));
//           // Attendance attendance=attendanceService.getAttendanceById(attendanceId);
//            for (WebSocketController webSocketController:webSocketSet
//                 ) {
//                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
//                    if(webSocketController.getRole().equals(studentRole)){
//
//                  //      webSocketController.sendObject(attendance);
//                        webSocketController.sendMessage("1");
//
//                    }
//
//          //          webSocketController.sendMessage("question"+questionNum.get(webSocketController.getSeminarKlassId()));
//
//                }
//            }
//        }else if(message.startsWith("抽取提问")){
////             if(attendanceService==null){
////                 System.out.println("wuuwuwwuw");
////             }
////             System.out.println(attendanceService.toString());
////             System.out.println(message+"perth");
////            Long attendanceId=Long.parseLong(message.replace("抽取提问",""));
////             System.out.println(attendanceId);
////            Question question=attendanceService.getQuestionByAttendanceId(attendanceId);
////            this.sendObject(question);
////            questionNum.put(this.getSeminarKlassId(),questionNum.get(this.getSeminarKlassId())-1);
//               for (WebSocketController webSocketController:webSocketSet
//                 ) {
//                if(this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
//                    if (webSocketController.getUserId().equals(question.getStudentId())) {
//                        webSocketController.isAsked=false;
//
//                         webSocketController.sendMessage("请您开始提问");
//
//                    } else if(webSocketController.getRole().equals(studentRole)) {
//
//                        webSocketController.sendMessage("很遗憾，请你继续等待");
//
//                    }
//
//                    webSocketController.sendMessage(""+questionNum.get(webSocketController.getSeminarKlassId()));
//
//                }
//            }
//        }else if(message.startsWith("3")){
//            if(this.isAsked){
//
//                this.sendMessage("您已进入提问队列，请您耐心等待！");
//
//            }else{
//                Long attendanceId=Long.parseLong(message.replace("提问",""));
//                this.isAsked=true;
//                if(questionNum.get(this.getSeminarKlassId())==null){
//                    questionNum.put(this.getSeminarKlassId(),1L);
//                }else{
//                    questionNum.put(this.getSeminarKlassId(),questionNum.get(this.getSeminarKlassId())+1L);
//                }
//                Question question=new Question();
//                question.setKlassSeminarId(this.getSeminarKlassId());
//                question.setStudentId(this.getUserId());
//                question.setTeamId(this.teamId);
//                question.setAttendanceId(attendanceId);
//                question.setIsSelected(0);
//                questionMapper.insertQuestion(question);
//
//                this.sendMessage("提问成功");
//
//                for (WebSocketController webSocketController:webSocketSet
//                     ) {
//                    if (this.getSeminarKlassId().equals(webSocketController.getSeminarKlassId())) {
//
//                        webSocketController.sendMessage("" +questionNum.get(webSocketController.getSeminarKlassId()));
//
//                    }
//                }
//            }
//        }
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
