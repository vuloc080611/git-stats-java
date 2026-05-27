package com.gitstats;

import picocli.CommandLine;
import com.gitstats.cli.GitStatsCommand;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new GitStatsCommand()).execute(args);
        System.exit(exitCode);
    }
}
