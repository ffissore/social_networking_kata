package org.fissore.kata.socialnetwork;

import org.fissore.kata.socialnetwork.commands.FollowCommand;
import org.fissore.kata.socialnetwork.commands.PostingCommand;
import org.fissore.kata.socialnetwork.commands.ReadingCommand;
import org.fissore.kata.socialnetwork.commands.WallCommand;
import org.fissore.kata.socialnetwork.storage.MemoryGraphStorage;
import org.fissore.kata.socialnetwork.storage.MemoryMessageStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CLITest {

    private MemoryMessageStorage messageStorage;
    private MemoryGraphStorage graphStorage;
    private PostingCommand postingCommand;
    private ReadingCommand readingCommand;
    private FollowCommand followCommand;
    private WallCommand wallCommand;

    @Before
    public void setUp() {
        messageStorage = new MemoryMessageStorage();
        graphStorage = new MemoryGraphStorage();
        postingCommand = new PostingCommand(messageStorage);
        readingCommand = new ReadingCommand(messageStorage);
        followCommand = new FollowCommand(graphStorage);
        wallCommand = new WallCommand(messageStorage, graphStorage);
    }

    @Test
    public void cli() {
        CLI cli = new CLI(postingCommand, readingCommand, followCommand, wallCommand);

        List<String> output;

        output = cli.handle("Alice -> I love the weather today");
        assertEquals(0, output.size());
        output = cli.handle("Bob -> Damn! We lost!");
        assertEquals(0, output.size());
        output = cli.handle("Bob -> Good game though.");
        assertEquals(0, output.size());

        output = cli.handle("Alice");
        assertEquals(1, output.size());
        assertEquals("I love the weather today (moments ago)", output.get(0));

        output = cli.handle("Bob");
        assertEquals(2, output.size());
        assertEquals("Good game though. (moments ago)", output.get(0));
        assertEquals("Damn! We lost! (moments ago)", output.get(1));

        output = cli.handle("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        assertEquals(0, output.size());
        output = cli.handle("Charlie follows Alice");
        assertEquals(0, output.size());

        output = cli.handle("Charlie wall");
        assertEquals(2, output.size());
        assertEquals("Charlie - I'm in New York today! Anyone wants to have a coffee? (moments ago)", output.get(0));
        assertEquals("Alice - I love the weather today (moments ago)", output.get(1));

        output = cli.handle("Charlie follows Bob");
        assertEquals(0, output.size());

        output = cli.handle("Charlie wall");
        assertEquals(4, output.size());
        assertEquals("Charlie - I'm in New York today! Anyone wants to have a coffee? (moments ago)", output.get(0));
        assertEquals("Bob - Good game though. (moments ago)", output.get(1));
        assertEquals("Bob - Damn! We lost! (moments ago)", output.get(2));
        assertEquals("Alice - I love the weather today (moments ago)", output.get(3));
    }
}
