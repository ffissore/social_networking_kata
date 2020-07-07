package org.fissore.kata.socialnetwork;

import lombok.Value;

import java.util.Date;

@Value
public class Message {

    String user;
    String message;
    Date date;

    public Message(String user, String message, Date date) {
        this.user = user;
        this.message = message;
        this.date = date;
    }

    public Message(String user, String message) {
        this(user, message, new Date());
    }

}
