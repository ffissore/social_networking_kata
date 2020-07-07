package org.fissore.kata.socialnetwork;

import java.util.List;

public interface Command {

    boolean canHandle(String input);

    List<String> handle(String input);

}
