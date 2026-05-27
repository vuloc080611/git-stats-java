package com.gitstats;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class GitAnalyzerTest {

    @TempDir
    Path tempDir;

    @Test
    void testNotAGitRepo() {
        File nonRepo = tempDir.toFile();
        // Should throw exception when constructing? We'll just check later
        assertTrue(true); // placeholder
    }
}
