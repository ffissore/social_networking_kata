package org.fissore.kata.socialnetwork;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostingCommandTest {

    @Test
    public void posting() {
        MessageStorage messageStorage = new MessageStorage();
        PostingCommand postingCommand = new PostingCommand(messageStorage);
        String[] inputParts = new CommandLineParser().parse("federico -> hello world!");
        postingCommand.handle(inputParts);

        List<Message> messages = messageStorage.list("federico");
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertEquals("federico", message.getUser());
        assertEquals("hello world!", message.getMessage());
        assertNotNull(message.getDate());
    }
}
