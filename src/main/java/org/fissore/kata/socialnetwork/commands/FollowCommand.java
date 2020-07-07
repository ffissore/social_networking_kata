package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.Message;
import org.fissore.kata.socialnetwork.storage.GraphStorage;

import java.util.Collections;
import java.util.List;

public class FollowCommand implements Command {

    private final GraphStorage graphStorage;

    public FollowCommand(GraphStorage graphStorage) {
        this.graphStorage = graphStorage;
    }

    @Override
    public boolean canHandle(String[] input) {
        return input.length > 1 && "follows".equals(input[1]);
    }

    @Override
    public List<Message> handle(String[] input) {
        String followingUser = input[0];
        String followedUser = input[2];

        graphStorage.addFollower(followingUser, followedUser);

        return Collections.emptyList();
    }
}
