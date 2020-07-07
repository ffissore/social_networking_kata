package org.fissore.kata.socialnetwork;

import java.util.Collections;
import java.util.List;

public class ReadingCommand implements Command {

    private final MessageStorage messageStorage;

    public ReadingCommand(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public boolean canHandle(String[] input) {
        return input.length == 1;
    }

    @Override
    public List<Message> handle(String[] input) {
        return messageStorage.list(Collections.singletonList(input[0]));
    }
}
