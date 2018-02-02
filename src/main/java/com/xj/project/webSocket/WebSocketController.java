package com.xj.project.webSocket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * websocket 测试
 *
 * @author xiangjing
 * @date 2018/2/2
 * @company 天极云智
 */
@ServerEndpoint(value="/test",configurator= GetHttpSessionConfigurator.class)
public class WebSocketController {
    public Session  session ;

    @OnOpen
    public void onOpen(Session session,EndpointConfig config) throws IOException {
        //以下代码省略...
        System.out.println("用户"+session.getId()+"链接");
        this.session = session;
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        httpSession.setAttribute("scoketSessionId", session.getId());
        this.session.getBasicRemote().sendText("欢迎来到聊天室");
    }

    @OnMessage
    public void onMessage(Session session,String message) throws IOException {
        System.out.println("client"+session.getId()+"说:"+message);
       this.session.getBasicRemote().sendText("服务器说");

    }

    @OnError
    public void onError(Throwable t) {
        //以下代码省略...
        t.printStackTrace();
        System.err.println("出错了");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        //以下代码省略...
        System.out.println(session.getId()+"退出链接");
    }



}
