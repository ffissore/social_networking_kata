package org.fissore.kata.socialnetwork;

public class PostingCommand implements Command {

    @Override
    public boolean canHandle(String input) {
        return input.contains(" -> ");
    }
}
