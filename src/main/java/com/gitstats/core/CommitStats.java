package com.gitstats.core;

import org.eclipse.jgit.revwalk.RevCommit;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Represents statistics for a single commit.
 * Currently used for future extensions (e.g., line insertions/deletions).
 */
public class CommitStats {
    private final String hash;
    private final String author;
    private final LocalDateTime date;
    private final String message;
    private final int insertions;
    private final int deletions;

    public CommitStats(RevCommit commit, int insertions, int deletions) {
        this.hash = commit.getName().substring(0, 7);
        this.author = commit.getAuthorIdent().getName();
        this.date = LocalDateTime.ofInstant(commit.getAuthorIdent().getWhen().toInstant(), ZoneId.systemDefault());
        this.message = commit.getShortMessage();
        this.insertions = insertions;
        this.deletions = deletions;
    }

    public String getHash() { return hash; }
    public String getAuthor() { return author; }
    public LocalDateTime getDate() { return date; }
    public String getMessage() { return message; }
    public int getInsertions() { return insertions; }
    public int getDeletions() { return deletions; }
    public int getNetChanges() { return insertions - deletions; }
}
