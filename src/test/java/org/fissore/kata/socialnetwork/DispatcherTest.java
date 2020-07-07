package org.fissore.kata.socialnetwork;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DispatcherTest {

    @Test
    public void unknownCommand() {
        Dispatcher dispatcher = new Dispatcher();
        Optional<Command> command = dispatcher.findCommandFor(new String[0]);
        assertFalse(command.isPresent());
    }

    @Test
    public void postingCommand() {
        Dispatcher dispatcher = new Dispatcher(new PostingCommand(null));
        String[] inputParts = new CommandLineParser().parse("federico -> hello world!");
        Optional<Command> command = dispatcher.findCommandFor(inputParts);
        assertTrue(command.isPresent());
        assertTrue(command.get() instanceof PostingCommand);
    }
}
