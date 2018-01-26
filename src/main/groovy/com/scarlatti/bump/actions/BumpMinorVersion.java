package com.scarlatti.bump.actions;

import com.google.inject.Inject;
import com.scarlatti.bump.version.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
public class BumpMinorVersion extends Action {

    private DefaultVersionAction defaultVersionAction;
    private final static Logger log = LoggerFactory.getLogger(Action.class);

    @Inject
    public BumpMinorVersion(DefaultVersionAction defaultVersionAction) {
        this.defaultVersionAction = defaultVersionAction;
    }

    @Override
    public void perform() {
        Version currentVersion = defaultVersionAction.getCurrentVersion(
            defaultVersionAction.getDefaultVersionFile());

        log.info("Current version is: " + currentVersion.toSemanticString());

        // bump version
        Version newVersion = new Version(currentVersion);
        newVersion.bumpMinor();

        log.info("Saving...");
        defaultVersionAction.setVersion(
            defaultVersionAction.getDefaultVersionFile(),
            newVersion.toSemanticString()
        );
    }
}
