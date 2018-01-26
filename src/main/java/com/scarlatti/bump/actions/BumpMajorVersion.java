package com.scarlatti.bump.actions;

import com.google.inject.Inject;
import com.scarlatti.bump.model.Version;

import static com.scarlatti.bump.config.CLIConfig.TWO_COLUMN_FORMAT;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
public class BumpMajorVersion extends Action {

    private DefaultVersionAction defaultVersionAction;

    @Inject
    public BumpMajorVersion(DefaultVersionAction defaultVersionAction) {
        this.defaultVersionAction = defaultVersionAction;
    }


    @Override
    public void perform() {
        Version currentVersion = defaultVersionAction.getCurrentVersion(
            defaultVersionAction.getDefaultVersionFile());

        System.out.printf(TWO_COLUMN_FORMAT, ":Current Version", currentVersion.toSemanticString());

        // bump version
        Version newVersion = new Version(currentVersion);
        newVersion.bumpMajor();

        if (defaultVersionAction.approveNewVersion(currentVersion, newVersion)) {
            defaultVersionAction.setVersion(
                defaultVersionAction.getDefaultVersionFile(),
                newVersion.toSemanticString()
            );
        }
    }
}
