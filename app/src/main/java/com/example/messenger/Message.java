package com.example.messenger;

public class Message {
    private String mess, name;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message(String name, String mess) {
        this.mess = mess;
        this.name = name;
    }
}
