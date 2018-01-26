package com.scarlatti.bump;

import com.google.inject.Singleton;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
@Singleton
public class BannerDisplay {
    public void displayBanner() {
        try (BufferedReader reader =
                 new BufferedReader(
                     new InputStreamReader(
                         this.getClass().getResourceAsStream("/banner.txt")))) {

            reader.lines().forEach(s -> {
                System.out.println(s);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
