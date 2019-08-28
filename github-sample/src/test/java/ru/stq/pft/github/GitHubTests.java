package ru.stq.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommints() throws IOException {
    Github github = new RtGithub("3c60f93a4283ad7cd8fcf5b7236d7907389b713a");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("gaztell0", "java_myproject")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }

}
