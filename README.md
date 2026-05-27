# 📊 git-stats-java

> CLI tool to analyze Git repository – commits per author, daily activity, most changed files, and more.

## Features

- 🔍 Total commits, unique authors
- 👥 Commit count per author
- 📅 Daily commit activity (last N days)
- 📁 Most changed files
- 📄 JSON export for scripting
- 🖥️ ASCII tables for terminal

## Build & Run

```bash
mvn clean package
java -jar target/git-stats-java-1.0.0.jar .
