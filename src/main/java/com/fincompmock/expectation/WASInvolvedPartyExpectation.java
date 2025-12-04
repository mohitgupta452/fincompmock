package com.fincompmock.expectation;

import com.fincompmock.model.User;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Involved party expectations for a given user.
 * <p>
 * Endpoint:
 * GET /mock/involved-party/{userId}
 * <p>
 * JSON is read from: src/main/resources/involved-party/{baseName}.json
 * where baseName comes from the main user file (e.g. example-user-01).
 */
public class WASInvolvedPartyExpectation {

    private final MockServerClient client;
    private final User user;
    private final String baseName;
    private final Path involvedPartyDir;

    public WASInvolvedPartyExpectation(MockServerClient client, User user, String baseName) {
        this.client = client;
        this.user = user;
        this.baseName = baseName;

        String dir = System.getProperty("mock.involved.party.dir", "src/main/resources/involved-party");
        this.involvedPartyDir = Paths.get(dir);
    }

    public void register() {
        String path = "/mock/involved-party/" + user.getUserId();
        Path jsonFile = involvedPartyDir.resolve(baseName + ".json");

        System.out.println("Registering involved-party endpoint at: " + path +
                " using file: " + jsonFile.toAbsolutePath());

        String body = readFileSafely(jsonFile);

        client.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath(path)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)
        );
    }

    private String readFileSafely(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException e) {
            System.err.println("Failed to read involved-party file: " + file + " -> " + e.getMessage());
            // Fallback empty structure to avoid 500s
            return "{ \"userId\": \"" + user.getUserId() + "\", \"parties\": [] }";
        }
    }
}
