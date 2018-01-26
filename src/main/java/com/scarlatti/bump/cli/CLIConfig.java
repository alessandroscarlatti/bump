package com.scarlatti.bump.cli;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
public class CLIConfig extends AbstractModule {

    public static final String TWO_COLUMN_FORMAT = "%-60s%s%n";
    public static final String FIRST_HALF_FORMAT = "%-60s";
    public static final String SECOND_HALF_FORMAT = "%s%n";

    @Override
    protected void configure() {
        // I may not need to configure much here!!

    }

    @Provides
    @Singleton
    private Options options() {
        Options options = new Options();
        options.addOption(Option.BUMP_MAJOR.getShortName(), Option.BUMP_MAJOR.getShortName(), false, "bump major version.");
        options.addOption(Option.BUMP_MINOR.getShortName(), Option.BUMP_MINOR.getLongName(), false, "bump minor version.");
        options.addOption(Option.BUMP_PATCH.getShortName(), Option.BUMP_PATCH.getLongName(), false, "bump patch version.");

        return options;
    }


    @Provides
    @Singleton
    private CommandLineParser commandLineParser() {
        return new BasicParser();
    }

    @Provides
    @Singleton
    @Named("workingDir")
    private String workingDir() {
        return System.getProperty("user.dir");
    }
}
