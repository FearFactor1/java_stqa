package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.annotation.concurrent.Immutable;
import javax.sound.midi.Soundbank;
import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("FearFactor1", "java_stqa")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
