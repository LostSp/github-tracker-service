package com.nishath.github_commit_tracker.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubWebhookPayload {

    private Repository repository;
    private List<CommitData> commits;

    public Repository getRepository() { return repository; }
    public void setRepository(Repository repository) { this.repository = repository; }

    public List<CommitData> getCommits() { return commits; }
    public void setCommits(List<CommitData> commits) { this.commits = commits; }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Repository {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CommitData {
        private String id;
        private String message;
        private String timestamp;
        private Author author;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

        public Author getAuthor() { return author; }
        public void setAuthor(Author author) { this.author = author; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
