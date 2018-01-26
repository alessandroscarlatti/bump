package com.scarlatti.bump;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.scarlatti.bump.actions.Action;
import com.scarlatti.bump.config.ActionsConfig;
import com.scarlatti.bump.cli.ActionSelector;
import com.scarlatti.bump.config.CLIConfig;
import com.scarlatti.bump.util.BannerDisplay;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
public class App {

    private ActionSelector actionSelector;
    private BannerDisplay bannerDisplay;

    @Inject
    public App(ActionSelector actionSelector, BannerDisplay bannerDisplay) {
        this.actionSelector = actionSelector;
        this.bannerDisplay = bannerDisplay;
    }

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new CLIConfig(), new ActionsConfig());
        injector.getInstance(App.class).run(args);
    }

    public void run(String[] args) {
        bannerDisplay.displayBanner();
        Action action = actionSelector.chooseAction(args);
        action.perform();
    }
}
