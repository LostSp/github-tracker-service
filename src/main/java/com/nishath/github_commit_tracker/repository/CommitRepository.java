package com.nishath.github_commit_tracker.repository;

import com.nishath.github_commit_tracker.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit,Long> {
}
