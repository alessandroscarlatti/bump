package com.scarlatti.bump.cli.actionCreators;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.scarlatti.bump.actions.Action;
import com.scarlatti.bump.actions.BumpMajorVersion;
import com.scarlatti.bump.actions.BumpPatchVersion;
import org.apache.commons.cli.CommandLine;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
@Singleton
public class BumpPatchVersionActionCreator implements ActionCreator {

    private Provider<BumpPatchVersion> bumpPatchVersionProvider;
    private final String NAME = "Bump Major Version";

    @Inject
    public BumpPatchVersionActionCreator(Provider<BumpPatchVersion> bumpPatchVersionProvider) {
     this.bumpPatchVersionProvider = bumpPatchVersionProvider;
    }

    @Override
    public BumpPatchVersion create(CommandLine cli) {
        return bumpPatchVersionProvider.get();
    }

    @Override
    public String name() {
        return NAME;
    }
}
