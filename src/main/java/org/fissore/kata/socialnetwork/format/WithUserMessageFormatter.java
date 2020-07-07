package org.fissore.kata.socialnetwork.format;

import org.fissore.kata.socialnetwork.Message;

public class WithUserMessageFormatter implements MessageFormatter {

    private final SimpleMessageFormatter simpleMessageFormatter;

    public WithUserMessageFormatter() {
        simpleMessageFormatter = new SimpleMessageFormatter();
    }

    @Override
    public String format(Message message) {
        return String.format("%s - %s", message.getUser(), simpleMessageFormatter.format(message));
    }
}
