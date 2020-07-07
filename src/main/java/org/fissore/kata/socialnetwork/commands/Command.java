package org.fissore.kata.socialnetwork.commands;

import org.fissore.kata.socialnetwork.Message;

import java.util.List;

public interface Command {

    boolean canHandle(String[] input);

    List<Message> handle(String[] input);

}
