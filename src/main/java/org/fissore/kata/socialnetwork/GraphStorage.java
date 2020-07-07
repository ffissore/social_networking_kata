package org.fissore.kata.socialnetwork;

import java.util.*;

public class GraphStorage {

    private final Map<String, List<String>> followerToFollowed;

    public GraphStorage() {
        this.followerToFollowed = new HashMap<>();
    }

    public List<String> listFollowingUsers(String followingUser) {
        return followerToFollowed.getOrDefault(followingUser, Collections.emptyList());
    }

    public void addFollower(String followingUser, String followedUser) {
        if (!followerToFollowed.containsKey(followingUser)) {
            List<String> followedUsers = new ArrayList<>();
            followedUsers.add(followedUser);
            followerToFollowed.put(followingUser, followedUsers);
            return;
        }

        List<String> followedUsers = followerToFollowed.get(followingUser);
        followedUsers.add(followedUser);
    }
}
