package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.CLIParser;
import org.fissore.kata.socialnetwork.Message;
import org.fissore.kata.socialnetwork.storage.MemoryMessageStorage;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostingCommandTest {

    @Test
    public void posting() {
        MemoryMessageStorage messageStorage = new MemoryMessageStorage();
        PostingCommand postingCommand = new PostingCommand(messageStorage);
        String[] inputParts = new CLIParser().parse("federico -> hello world!");
        postingCommand.handle(inputParts);

        List<Message> messages = messageStorage.list(Collections.singleton("federico"));
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertEquals("federico", message.getUser());
        assertEquals("hello world!", message.getMessage());
        assertNotNull(message.getDate());
    }
}
