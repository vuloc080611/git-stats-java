package com.gitstats.output;

import de.vandermeer.asciitable.AsciiTable;
import com.gitstats.core.AuthorStats;

import java.util.Map;
import java.util.List;

public class TableFormatter {

    public static void printAuthorTable(Map<String, AuthorStats> stats) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Author", "Commits");
        at.addRule();
        for (AuthorStats s : stats.values()) {
            at.addRow(s.getName(), s.getCommitCount());
            at.addRule();
        }
        System.out.println(at.render());
    }

    public static void printDailyTable(Map<String, Integer> daily) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Date", "Commits");
        at.addRule();
        for (Map.Entry<String, Integer> entry : daily.entrySet()) {
            at.addRow(entry.getKey(), entry.getValue());
            at.addRule();
        }
        System.out.println(at.render());
    }

    public static void printFileTable(Map<String, Integer> files) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("File", "Changes");
        at.addRule();
        for (Map.Entry<String, Integer> entry : files.entrySet()) {
            at.addRow(entry.getKey(), entry.getValue());
            at.addRule();
        }
        System.out.println(at.render());
    }

    public static void printSummary(Map<String, Object> summary) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Metric", "Value");
        at.addRule();
        at.addRow("Total commits", summary.get("total_commits"));
        at.addRow("Total authors", summary.get("total_authors"));
        at.addRow("Authors", summary.get("authors").toString());
        at.addRule();
        System.out.println(at.render());
    }
}
