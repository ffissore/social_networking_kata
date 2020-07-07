package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.CLIParser;
import org.fissore.kata.socialnetwork.Message;
import org.fissore.kata.socialnetwork.storage.MemoryMessageStorage;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReadingCommandTest {

    @Test
    public void reading() {
        MemoryMessageStorage messageStorage = new MemoryMessageStorage();
        ReadingCommand readingCommand = new ReadingCommand(messageStorage);
        String[] inputParts = new CLIParser().parse("federico");
        List<Message> messages = readingCommand.handle(inputParts);
        assertEquals(0, messages.size());

        messageStorage.add("federico", "good morning vietnam!");

        messages = readingCommand.handle(inputParts);
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertEquals("federico", message.getUser());
        assertEquals("good morning vietnam!", message.getMessage());
        assertNotNull(message.getDate());
    }
}
