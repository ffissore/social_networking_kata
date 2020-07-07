package org.fissore.kata.socialnetwork.storage;

import java.util.Set;

public interface GraphStorage {

    Set<String> listFollowedUsers(String followingUser);

    void addFollower(String followingUser, String followedUser);

}
