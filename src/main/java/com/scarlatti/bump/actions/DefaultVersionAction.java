package com.scarlatti.bump.actions;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.scarlatti.bump.version.Version;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.scarlatti.bump.cli.CLIConfig.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 1/25/2018
 */
public class DefaultVersionAction {

    private String defaultVersionFile;
    private String workingDir;

    @Inject
    public DefaultVersionAction(
        @Named("defaultVersionFile") String defaultVersionFile,
        @Named("workingDir") String workingDir) {
        this.defaultVersionFile = defaultVersionFile;
        this.workingDir = workingDir;
    }

    /**
     * Get the current version from a version file.
     */
    public Version getCurrentVersion(String versionFile) {
        Path path = Paths.get(workingDir, versionFile);
        try {
            String file = new String(Files.readAllBytes(path));
            String versionLine = findVersionLine(file);

            // now parse the version from the line
            Pattern versionRegex = Pattern.compile("\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}");
            Matcher versionRegexMatcher = versionRegex.matcher(versionLine);

            if (versionRegexMatcher.find()) {
                String version = versionRegexMatcher.group(0);
                String[] parts = version.split("\\.");

                if (parts.length != 3) {
                    throw new RuntimeException("Unable to parse version: " + version);
                }

                List<Integer> nums = Arrays.stream(parts).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

                return new Version(nums.get(0), nums.get(1), nums.get(2));
            } else {
                throw new RuntimeException("Unable to get current version from line: " + versionLine);
            }


        } catch (Exception e) {
            throw new RuntimeException("Error reading file " + versionFile + " in " + workingDir, e);
        }
    }

    public void setVersion(String versionFile, String newVersion) {
        Path path = Paths.get(workingDir, versionFile);

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
            String versionLine = findVersionLine(String.join("\n", lines));

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(versionLine)) {
                    // now replace version line
                    lines.set(i, replaceVersionLine(lines.get(i), newVersion));
                    break;
                }
            }

            // now write the file
            Files.write(path, lines);

            System.out.printf(String.format(TWO_COLUMN_FORMAT, ":Saving", "Saved").replace(" ", "."));

        } catch (IOException e) {

            System.out.printf(String.format(TWO_COLUMN_FORMAT, ":Saving", "Failed").replace(" ", "."));

            System.out.printf("Original file:%n%s%n", String.join("", lines));
            throw new RuntimeException("Failed to save file!");
        }
    }

    public boolean approveNewVersion(Version currentVersion, Version newVersion) {
        System.out.printf(TWO_COLUMN_FORMAT, ":New Version OK?", newVersion.toSemanticString());
        System.out.printf(FIRST_HALF_FORMAT, "(Enter to approve)");

        Scanner scanner = new Scanner(System.in);

        String accepted = scanner.nextLine();
        // this line only reached if program not aborted
        // but just in case no is entered...
        if ("yes".startsWith(accepted)) {
            System.out.printf(TWO_COLUMN_FORMAT, "", "Approved");
            return true;
        } else {
            System.out.printf(TWO_COLUMN_FORMAT, "", "Declined");
            return false;
        }
    }

    public String getDefaultVersionFile() {
        return defaultVersionFile;
    }

    private String findVersionLine(String file) {
        Pattern versionLineRegex = Pattern.compile("version[^\\n]*(?=.*dependencies)", Pattern.DOTALL);
        Matcher regexMatcher = versionLineRegex.matcher(file);

        if (regexMatcher.find()) {
            return regexMatcher.group(0);
        } else {
            throw new RuntimeException("Unable to get current version.");
        }
    }

    private String replaceVersionLine(String versionLine, String newVersion) {
        return versionLine.replaceFirst("\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}", newVersion);
    }
}
