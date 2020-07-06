package org.fissore.kata.socialnetwork;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DispatcherTest {

    @Test
    public void unknownCommand() {
        Dispatcher dispatcher = new Dispatcher();
        Optional<Command> command = dispatcher.findCommandFor("whatever");
        assertFalse(command.isPresent());
    }

    @Test
    public void postingCommand() {
        Dispatcher dispatcher = new Dispatcher(new PostingCommand());
        Optional<Command> command = dispatcher.findCommandFor("federico -> hello world!");
        assertTrue(command.isPresent());
        assertTrue(command.get() instanceof PostingCommand);
    }
}
