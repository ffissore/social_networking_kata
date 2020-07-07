package org.fissore.kata.socialnetwork;

import org.fissore.kata.socialnetwork.commands.WallCommand;
import org.fissore.kata.socialnetwork.storage.GraphStorage;
import org.fissore.kata.socialnetwork.storage.MessageStorage;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class WallCommandTest {

    @Test
    public void wall() {
        MessageStorage messageStorage = new MessageStorage();
        GraphStorage graphStorage = new GraphStorage();
        WallCommand wallCommand = new WallCommand(messageStorage, graphStorage);

        String[] input = new CLIParser().parse("federico wall");
        List<Message> messages = wallCommand.handle(input);

        assertEquals(0, messages.size());

        messageStorage.add("vittoria", "message 1");
        messageStorage.add("gaia", "message 2");
        messageStorage.add("saturnino", "message 3");
        messageStorage.add("federico", "message 4");

        graphStorage.addFollower("federico", "vittoria");
        graphStorage.addFollower("federico", "gaia");

        messages = wallCommand.handle(input);
        assertEquals(3, messages.size());
        assertEquals("message 4", messages.get(0).getMessage());
        assertEquals("federico", messages.get(0).getUser());
        assertEquals("message 2", messages.get(1).getMessage());
        assertEquals("gaia", messages.get(1).getUser());
        assertEquals("message 1", messages.get(2).getMessage());
        assertEquals("vittoria", messages.get(2).getUser());
    }
}
