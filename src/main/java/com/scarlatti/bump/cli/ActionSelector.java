package com.scarlatti.bump.cli;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.scarlatti.bump.actions.Action;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Arrays;

import static com.scarlatti.bump.config.CLIConfig.TWO_COLUMN_FORMAT;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
@Singleton
public class ActionSelector {

    private CommandLineParser parser;
    private Options options;
    private ActionFactory actionFactory;

    @Inject
    public ActionSelector(CommandLineParser parser, Options options, ActionFactory actionFactory) {
        this.parser = parser;
        this.options = options;
        this.actionFactory = actionFactory;
    }

    /**
     * Select which action to perform.
     * There will be a priority placed on the first item listed,
     * whether as an "option" or as an arg.
     *
     * Running the program requires an argument, otherwise defaults to minor.
     *
     *
     * @param args the raw command line arguments
     * @return the action to perform
     */
    public Action chooseAction(String[] args) {
        try {
            System.out.printf(TWO_COLUMN_FORMAT, ":Received arguments", Arrays.toString(args));
            CommandLine cli = parser.parse(options, args);

            String[] cliArgs = cli.getArgs();

            if (cliArgs.length > 0) {
                if (cliArgs[0].equals(Option.BUMP_MAJOR.getShortName()) ||
                    cliArgs[0].equals(Option.BUMP_MAJOR.getLongName())) {
                    System.out.printf(TWO_COLUMN_FORMAT, ":Selecting action", actionFactory.getBumpMajorVersion().name());
                    return actionFactory.getBumpMajorVersion().create(cli);
                }

                if (cliArgs[0].equals(Option.BUMP_PATCH.getShortName()) ||
                    cliArgs[0].equals(Option.BUMP_PATCH.getLongName())) {
                    System.out.printf(TWO_COLUMN_FORMAT, ":Selecting action", actionFactory.getBumpPatchVersion().name());
                    return actionFactory.getBumpPatchVersion().create(cli);
                }

                if (cliArgs[0].equals(Option.BUMP_MINOR.getShortName()) ||
                    cliArgs[0].equals(Option.BUMP_MINOR.getLongName())) {
                    System.out.printf(TWO_COLUMN_FORMAT, ":Selecting action", actionFactory.getBumpMinorVersion().name());
                    return actionFactory.getBumpMinorVersion().create(cli);
                }

                throw new IllegalArgumentException("Unrecognized arguments: " + Arrays.toString(args));
            } else {
                System.out.printf(TWO_COLUMN_FORMAT, ":Selecting action", actionFactory.getBumpMinorVersion().name());
                return actionFactory.getBumpMinorVersion().create(cli);
            }
        } catch (ParseException e) {
            throw new IllegalStateException("Unable to parse command line args: " + Arrays.toString(args));
        }
    }
}
