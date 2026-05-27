package com.gitstats.core;

import org.eclipse.jgit.revwalk.RevCommit;
import java.util.ArrayList;
import java.util.List;

public class AuthorStats {
    private final String name;
    private int commitCount = 0;
    private final List<String> commitMessages = new ArrayList<>();

    public AuthorStats(String name) {
        this.name = name;
    }

    public void addCommit(RevCommit commit) {
        commitCount++;
        commitMessages.add(commit.getShortMessage());
    }

    public String getName() { return name; }
    public int getCommitCount() { return commitCount; }
    public List<String> getCommitMessages() { return commitMessages; }
}
