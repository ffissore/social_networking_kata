package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.Message;
import org.fissore.kata.socialnetwork.storage.MessageStorage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostingCommand implements Command {

    private final MessageStorage messageStorage;

    public PostingCommand(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public boolean canHandle(String[] input) {
        return input.length > 1 && "->".equals(input[1]);
    }

    @Override
    public List<Message> handle(String[] input) {
        String user = input[0];
        String message = Arrays.stream(input).skip(2).collect(Collectors.joining(" "));

        messageStorage.add(user, message);

        return Collections.emptyList();
    }
}
