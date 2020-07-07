package org.fissore.kata.socialnetwork.storage;

import java.util.*;

public class MemoryGraphStorage implements GraphStorage {

    private final Map<String, Set<String>> followerToFollowed;

    public MemoryGraphStorage() {
        this.followerToFollowed = new HashMap<>();
    }

    public Set<String> listFollowedUsers(String followingUser) {
        return new HashSet<>(followerToFollowed.getOrDefault(followingUser, Collections.emptySet()));
    }

    public void addFollower(String followingUser, String followedUser) {
        Objects.requireNonNull(followingUser);
        Objects.requireNonNull(followedUser);

        if (followingUser.equals(followedUser)) {
            return;
        }

        if (!followerToFollowed.containsKey(followingUser)) {
            Set<String> followedUsers = new HashSet<>();
            followedUsers.add(followedUser);
            followerToFollowed.put(followingUser, followedUsers);
            return;
        }

        Set<String> followedUsers = followerToFollowed.get(followingUser);
        followedUsers.add(followedUser);
    }
}
