package com.example.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessionMap = Collections.synchronizedMap(new HashMap<>(100));

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
        System.out.println("clint connected!");
        session.sendMessage(new TextMessage("hello !"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String s = message.getPayload();

        System.out.println("client send: " + s);

        //返回原始数据
//        session.sendMessage(new TextMessage(s));
        brodCast(new TextMessage(s));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("clint closed!");
    }


    private void brodCast(TextMessage message) {
        sessionMap.forEach((id, session) -> {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
