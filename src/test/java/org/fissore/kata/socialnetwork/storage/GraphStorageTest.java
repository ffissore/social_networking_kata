package org.fissore.kata.socialnetwork.storage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphStorageTest {

    @Test
    public void followingTheSameUserTwiceIsNOOP() {
        GraphStorage graphStorage = new GraphStorage();
        graphStorage.addFollower("federico", "vittoria");
        assertEquals(1, graphStorage.listFollowedUsers("federico").size());

        graphStorage.addFollower("federico", "vittoria");
        assertEquals(1, graphStorage.listFollowedUsers("federico").size());
    }

    @Test
    public void followingYourselfIsNotAllowed() {
        GraphStorage graphStorage = new GraphStorage();
        graphStorage.addFollower("federico", "federico");
        assertEquals(0, graphStorage.listFollowedUsers("federico").size());
    }
}