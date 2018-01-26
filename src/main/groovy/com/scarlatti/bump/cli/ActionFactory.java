package com.scarlatti.bump.cli;

import com.scarlatti.bump.actions.BumpMajorVersion;
import com.scarlatti.bump.actions.BumpMinorVersion;
import com.scarlatti.bump.actions.BumpPatchVersion;
import org.apache.commons.cli.CommandLine;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
public class ActionFactory {

    public static class BumpMajorVersion {

    }

    public static BumpMajorVersion bumpMajorVersion(CommandLine cli) {
        return new BumpMajorVersion();
    }

    public static BumpMinorVersion bumpMinorVersion(CommandLine cli) {
        return new BumpMinorVersion();
    }

    public static BumpPatchVersion bumpPatchVersion(CommandLine cli) {
        return new BumpPatchVersion();
    }
}
