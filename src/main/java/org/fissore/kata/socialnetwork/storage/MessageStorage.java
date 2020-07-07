package org.fissore.kata.socialnetwork.storage;

import org.fissore.kata.socialnetwork.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageStorage {

    private final LinkedList<Message> messages;

    public MessageStorage() {
        this.messages = new LinkedList<>();
    }

    public List<Message> list(Set<String> users) {
        return messages.stream()
            .filter(message -> users.contains(message.getUser()))
            .collect(Collectors.toList());
    }

    public void add(String user, String message) {
        messages.addFirst(new Message(user, message));
    }
}
