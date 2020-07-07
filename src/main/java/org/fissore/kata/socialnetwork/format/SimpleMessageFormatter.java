package org.fissore.kata.socialnetwork.format;

import org.fissore.kata.socialnetwork.Message;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Locale;

public class SimpleMessageFormatter implements MessageFormatter {

    @Override
    public String format(Message message) {
        return String.format("%s (%s)", message.getMessage(), new PrettyTime(Locale.ENGLISH).format(message.getDate()));
    }

}
