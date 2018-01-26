package com.scarlatti.bump

import com.google.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
@Singleton
class BannerDisplay {

    private static final Logger log = LoggerFactory.getLogger(App.class)

    void displayBanner() {
        this.class.getResourceAsStream("/banner.txt").withStream {
            log.info("\n" + it.readLines().join("\n"))
        }
    }
}
