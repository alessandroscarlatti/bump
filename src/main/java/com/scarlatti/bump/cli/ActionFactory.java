package com.scarlatti.bump.cli;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.scarlatti.bump.actions.BumpMajorVersion;
import com.scarlatti.bump.actions.BumpMinorVersion;
import com.scarlatti.bump.actions.BumpPatchVersion;
import com.scarlatti.bump.cli.actionCreators.BumpMajorVersionActionCreator;
import com.scarlatti.bump.cli.actionCreators.BumpMinorVersionActionCreator;
import com.scarlatti.bump.cli.actionCreators.BumpPatchVersionActionCreator;
import org.apache.commons.cli.CommandLine;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
@Singleton
public class ActionFactory {

    private BumpMajorVersionActionCreator bumpMajorVersion;
    private BumpMinorVersionActionCreator bumpMinorVersion;
    private BumpPatchVersionActionCreator bumpPatchVersion;

    @Inject
    public ActionFactory(
        BumpMajorVersionActionCreator bumpMajorVersion,
        BumpMinorVersionActionCreator bumpMinorVersion,
        BumpPatchVersionActionCreator bumpPatchVersion
    ) {
        this.bumpMajorVersion = bumpMajorVersion;
        this.bumpMinorVersion = bumpMinorVersion;
        this.bumpPatchVersion = bumpPatchVersion;
    }

    public BumpMajorVersionActionCreator getBumpMajorVersion() {
        return bumpMajorVersion;
    }

    public BumpMinorVersionActionCreator getBumpMinorVersion() {
        return bumpMinorVersion;
    }

    public BumpPatchVersionActionCreator getBumpPatchVersion() {
        return bumpPatchVersion;
    }
}
