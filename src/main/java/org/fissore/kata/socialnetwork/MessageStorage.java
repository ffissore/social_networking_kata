package org.fissore.kata.socialnetwork;

import java.util.LinkedList;
import java.util.List;

public class MessageStorage {

    private final LinkedList<Message> messages;

    public MessageStorage() {
        this.messages = new LinkedList<>();
    }

    public List<Message> list() {
        return messages;
    }

    public void add(String user, String message) {
        messages.addFirst(new Message(user, message));
    }
}
