package org.fissore.kata.socialnetwork;

import org.fissore.kata.socialnetwork.commands.FollowCommand;
import org.fissore.kata.socialnetwork.storage.GraphStorage;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FollowCommandTest {

    @Test
    public void follow() {
        GraphStorage graphStorage = new GraphStorage();
        FollowCommand followCommand = new FollowCommand(graphStorage);
        String[] input = new CLIParser().parse("federico follows vittoria");
        followCommand.handle(input);

        List<String> users = graphStorage.listFollowedUsers("federico");
        assertEquals(1, users.size());
        assertEquals("vittoria", users.get(0));
    }
}
