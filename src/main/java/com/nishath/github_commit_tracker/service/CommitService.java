package com.nishath.github_commit_tracker.service;



import com.nishath.github_commit_tracker.dto.GitHubWebhookPayload;
import com.nishath.github_commit_tracker.entity.Author;
import com.nishath.github_commit_tracker.entity.Commit;
import com.nishath.github_commit_tracker.repository.AuthorRepository;
import com.nishath.github_commit_tracker.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SlackService slackService;

    public void processWebhook(GitHubWebhookPayload payload) {

        List<String> commitMessages = new ArrayList<>();
        String repoName = payload.getRepository().getName();
        String name = "";
        
        for (GitHubWebhookPayload.CommitData data : payload.getCommits()) {

            final String authorName = data.getAuthor().getName();
             name=data.getAuthor().getName();
            // Find or create author
            Author author = authorRepository.findByName(authorName)
                    .orElseGet(() -> {

                        Author newAuthor = new Author();
                        newAuthor.setName(authorName);
                        return authorRepository.save(newAuthor);
                    });

            // Create commit
            Commit commit = new Commit();
//            commit.setCommitId(data.getId());
            commit.setCommitId(data.getId());
            commit.setMessage(data.getMessage());
            commit.setRepoName(repoName);
            commit.setTimestamp(
                    OffsetDateTime.parse(data.getTimestamp()).toLocalDateTime()
            );
          //  commit.setTimestamp(LocalDateTime.parse(data.getTimestamp()));
            commit.setAuthor(author);

            commitRepository.save(commit);

            commitMessages.add(data.getMessage());
        }

        //  Build Slack Summary
        StringBuilder slackMessage = new StringBuilder();
        slackMessage.append(name)
                .append(" pushed ")
                .append(commitMessages.size())
                .append(" commits to repo ")
                .append(repoName)
                .append(":\n");

        for (String msg : commitMessages) {
            slackMessage.append("- ").append(msg).append("\n");
        }

        // Send to Slack
        slackService.sendMessage(slackMessage.toString());
  }



}
