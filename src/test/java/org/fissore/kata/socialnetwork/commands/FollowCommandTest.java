package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.CLIParser;
import org.fissore.kata.socialnetwork.storage.MemoryGraphStorage;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FollowCommandTest {

    @Test
    public void follow() {
        MemoryGraphStorage graphStorage = new MemoryGraphStorage();
        FollowCommand followCommand = new FollowCommand(graphStorage);
        String[] input = new CLIParser().parse("federico follows vittoria");
        followCommand.handle(input);

        Set<String> users = graphStorage.listFollowedUsers("federico");
        assertEquals(1, users.size());
        assertEquals("vittoria", users.stream().findFirst().get());
    }
}
