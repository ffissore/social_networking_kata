package org.fissore.kata.socialnetwork.storage;

import org.fissore.kata.socialnetwork.Message;

import java.util.List;
import java.util.Set;

public interface MessageStorage {

    List<Message> list(Set<String> users);

    void add(String user, String message);

}
