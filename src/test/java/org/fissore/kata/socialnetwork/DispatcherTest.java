package org.fissore.kata.socialnetwork;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DispatcherTest {

    private Dispatcher dispatcher;

    @Before
    public void setUp() {
        dispatcher = new Dispatcher(new PostingCommand(null), new ReadingCommand(null));
    }

    @Test
    public void unknownCommand() {
        Optional<Command> command = dispatcher.findCommandFor(new String[0]);
        assertFalse(command.isPresent());
    }

    @Test
    public void postingCommand() {
        String[] inputParts = new CommandLineParser().parse("federico -> hello world!");
        Optional<Command> command = dispatcher.findCommandFor(inputParts);
        assertTrue(command.isPresent());
        assertTrue(command.get() instanceof PostingCommand);
    }

    @Test
    public void readingCommand() {
        String[] inputParts = new CommandLineParser().parse("federico");
        Optional<Command> command = dispatcher.findCommandFor(inputParts);
        assertTrue(command.isPresent());
        assertTrue(command.get() instanceof ReadingCommand);
    }
}
