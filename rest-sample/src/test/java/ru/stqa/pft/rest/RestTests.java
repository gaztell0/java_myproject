package ru.stqa.pft.rest;

import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    try {
      skipIfNotFixed(1764);
    } catch (SkipException e) {
     e.printStackTrace();
    }

    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue TS").withDescription("New test issue TS");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

}
