package org.fissore.kata.socialnetwork;

import org.fissore.kata.socialnetwork.commands.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Dispatcher {

    private final List<Command> commands;

    public Dispatcher(Command... commands) {
        this.commands = Arrays.asList(commands);
    }

    public Optional<Command> findCommandFor(String[] input) {
        return commands.stream()
            .filter(command -> command.canHandle(input))
            .findFirst();
    }
}
