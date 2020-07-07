package org.fissore.kata.socialnetwork;

import org.fissore.kata.socialnetwork.commands.*;
import org.fissore.kata.socialnetwork.format.MessageFormatter;
import org.fissore.kata.socialnetwork.format.SimpleMessageFormatter;
import org.fissore.kata.socialnetwork.format.WithUserMessageFormatter;
import org.fissore.kata.socialnetwork.storage.GraphStorage;
import org.fissore.kata.socialnetwork.storage.MessageStorage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CLI {

    private final Dispatcher dispatcher;
    private final CLIParser cliParser;
    private final SimpleMessageFormatter simpleMessageFormatter;
    private final WithUserMessageFormatter withUserMessageFormatter;

    public CLI(Command... commands) {
        dispatcher = new Dispatcher(commands);
        cliParser = new CLIParser();
        simpleMessageFormatter = new SimpleMessageFormatter();
        withUserMessageFormatter = new WithUserMessageFormatter();
    }

    public List<String> handle(String input) {
        String[] parts = cliParser.parse(input);

        Optional<Command> maybeCommand = dispatcher.findCommandFor(parts);
        if (maybeCommand.isEmpty()) {
            return Collections.emptyList();
        }

        Command command = maybeCommand.get();
        List<Message> messages = command.handle(parts);
        Set<String> users = messages.stream()
            .map(Message::getUser)
            .collect(Collectors.toSet());

        MessageFormatter formatter = pickFormatter(users);

        return messages.stream()
            .map(formatter::format)
            .collect(Collectors.toList());
    }

    private MessageFormatter pickFormatter(Set<String> users) {
        if (users.size() > 1) {
            return withUserMessageFormatter;
        }
        return simpleMessageFormatter;
    }

    public static void main(String[] args) throws Exception {
        MessageStorage messageStorage = new MessageStorage();
        GraphStorage graphStorage = new GraphStorage();
        PostingCommand postingCommand = new PostingCommand(messageStorage);
        ReadingCommand readingCommand = new ReadingCommand(messageStorage);
        FollowCommand followCommand = new FollowCommand(graphStorage);
        WallCommand wallCommand = new WallCommand(messageStorage, graphStorage);

        CLI cli = new CLI(postingCommand, readingCommand, followCommand, wallCommand);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cli.handle(line).forEach(System.out::println);
            }
        }
    }
}
