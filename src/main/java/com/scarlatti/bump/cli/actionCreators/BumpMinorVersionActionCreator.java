package com.scarlatti.bump.cli.actionCreators;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.scarlatti.bump.actions.Action;
import com.scarlatti.bump.actions.BumpMajorVersion;
import com.scarlatti.bump.actions.BumpMinorVersion;
import org.apache.commons.cli.CommandLine;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
@Singleton
public class BumpMinorVersionActionCreator implements ActionCreator {

    private Provider<BumpMinorVersion> bumpMinorVersionProvider;
    private final String NAME = "Bump Minor Version";

    @Inject
    public BumpMinorVersionActionCreator(Provider<BumpMinorVersion> bumpMinorVersionProvider) {
        this.bumpMinorVersionProvider = bumpMinorVersionProvider;
    }

    @Override
    public BumpMinorVersion create(CommandLine cli) {
        return bumpMinorVersionProvider.get();
    }

    @Override
    public String name() {
        return NAME;
    }
}
