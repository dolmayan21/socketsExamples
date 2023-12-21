package org.java.server.model;

import java.io.Serializable;

public class Message implements Serializable {
    private String messageType;
    private Object content;

    // Constructors, getters, setters

    public Message(String messageType, Object content) {
        this.messageType = messageType;
        this.content = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public Object getContent() {
        return content;
    }
}
