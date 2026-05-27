package com.gitstats.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.gitstats.core.GitAnalyzer;
import com.gitstats.core.AuthorStats;
import com.gitstats.core.CommitStats;
import com.gitstats.output.TableFormatter;
import com.gitstats.output.JsonExporter;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "git-stats", 
         description = "Analyze Git repository statistics",
         mixinStandardHelpOptions = true,
         version = "1.0.0")
public class GitStatsCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "Path to Git repository", defaultValue = ".")
    private File repoPath;

    @Option(names = {"--authors"}, description = "Show commit count per author")
    private boolean showAuthors;

    @Option(names = {"--daily"}, description = "Show commits per day (last 30 days)")
    private boolean showDaily;

    @Option(names = {"--files"}, description = "Show most changed files")
    private boolean showFiles;

    @Option(names = {"--json"}, description = "Export as JSON")
    private boolean jsonOutput;

    @Option(names = {"--since"}, description = "Start date (YYYY-MM-DD)")
    private String since;

    @Option(names = {"--until"}, description = "End date (YYYY-MM-DD)")
    private String until;

    @Override
    public Integer call() throws Exception {
        if (!repoPath.exists() || !new File(repoPath, ".git").exists()) {
            System.err.println("Error: Not a valid Git repository: " + repoPath.getAbsolutePath());
            return 1;
        }

        GitAnalyzer analyzer = new GitAnalyzer(repoPath);
        if (since != null) analyzer.setSince(since);
        if (until != null) analyzer.setUntil(until);

        if (showAuthors) {
            Map<String, AuthorStats> stats = analyzer.getAuthorStats();
            if (jsonOutput) {
                System.out.println(JsonExporter.toJson(stats));
            } else {
                TableFormatter.printAuthorTable(stats);
            }
        } else if (showDaily) {
            Map<String, Integer> daily = analyzer.getDailyCommitCount(30);
            if (jsonOutput) {
                System.out.println(JsonExporter.toJson(daily));
            } else {
                TableFormatter.printDailyTable(daily);
            }
        } else if (showFiles) {
            Map<String, Integer> fileChanges = analyzer.getMostChangedFiles(10);
            if (jsonOutput) {
                System.out.println(JsonExporter.toJson(fileChanges));
            } else {
                TableFormatter.printFileTable(fileChanges);
            }
        } else {
            // Default: show summary
            var summary = analyzer.getSummary();
            if (jsonOutput) {
                System.out.println(JsonExporter.toJson(summary));
            } else {
                TableFormatter.printSummary(summary);
            }
        }
        return 0;
    }
}
