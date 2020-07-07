package org.fissore.kata.socialnetwork;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageStorage {

    private final LinkedList<Message> messages;

    public MessageStorage() {
        this.messages = new LinkedList<>();
    }

    public List<Message> list(String user) {
        return messages.stream()
            .filter(message -> message.getUser().equals(user))
            .collect(Collectors.toList());
    }

    public void add(String user, String message) {
        messages.addFirst(new Message(user, message));
    }
}
