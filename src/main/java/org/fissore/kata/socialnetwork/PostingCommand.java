package org.fissore.kata.socialnetwork;

import java.util.Collections;
import java.util.List;

public class PostingCommand implements Command {

    private final MessageStorage messageStorage;

    public PostingCommand(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public boolean canHandle(String input) {
        return input.contains(" -> ");
    }

    @Override
    public List<String> handle(String input) {
        String[] parts = input.split(" -> ");
        String user = parts[0];
        String message = parts[1];

        messageStorage.add(user, message);

        return Collections.emptyList();
    }
}
