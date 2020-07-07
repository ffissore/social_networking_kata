package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.Message;
import org.fissore.kata.socialnetwork.storage.GraphStorage;
import org.fissore.kata.socialnetwork.storage.MessageStorage;

import java.util.List;

public class WallCommand implements Command {

    private final MessageStorage messageStorage;
    private final GraphStorage graphStorage;

    public WallCommand(MessageStorage messageStorage, GraphStorage graphStorage) {
        this.messageStorage = messageStorage;
        this.graphStorage = graphStorage;
    }

    @Override
    public boolean canHandle(String[] input) {
        return input.length == 2 && "wall".equals(input[1]);
    }

    @Override
    public List<Message> handle(String[] input) {
        String followingUser = input[0];

        List<String> users = graphStorage.listFollowedUsers(followingUser);
        users.add(followingUser);

        return messageStorage.list(users);
    }
}
