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
 * Identity-related expectations.
 * <p>
 * Endpoints:
 * GET  /mock/identity/{userId}        -> identity JSON
 * POST /mock/identity/assert          -> assert result JSON
 * <p>
 * JSON is read from:
 * src/main/resources/identity/{baseName}.json          (GET)
 * src/main/resources/identity/{baseName}-assert.json   (POST)
 */
public class WASIdentityAssertionExpectation {

    private final MockServerClient client;
    private final User user;
    private final String baseName;
    private final Path identityDir;

    public WASIdentityAssertionExpectation(MockServerClient client, User user, String baseName) {
        this.client = client;
        this.user = user;
        this.baseName = baseName;

        String dir = System.getProperty("mock.identity.dir", "src/main/resources/identity");
        this.identityDir = Paths.get(dir);
    }

    public void register() {
        registerIdentityLookup();
        registerIdentityAssert();
    }

    private void registerIdentityLookup() {
        String path = "/mock/identity/" + user.getUserId();
        Path jsonFile = identityDir.resolve(baseName + ".json");

        System.out.println("Registering identity lookup at: " + path +
                " using file: " + jsonFile.toAbsolutePath());

        String body = readFileSafely(jsonFile,
                "{ \"userId\": \"" + user.getUserId() + "\", \"name\": \"" + user.getName() + "\" }");

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

    private void registerIdentityAssert() {
        String path = "/mock/identity/assert";
        Path jsonFile = identityDir.resolve(baseName + "-assert.json");

        System.out.println("Registering identity assert at: " + path +
                " using file: " + jsonFile.toAbsolutePath());

        String fallback = "{ \"status\": \"ASSERTED\", \"userId\": \"" + user.getUserId() + "\" }";
        String body = readFileSafely(jsonFile, fallback);

        client.when(
                HttpRequest.request()
                        .withMethod("POST")
                        .withPath(path)
                        .withHeader("Content-Type", "application/json")
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)
        );
    }

    private String readFileSafely(Path file, String fallback) {
        try {
            if (Files.exists(file)) {
                return Files.readString(file);
            }
        } catch (IOException e) {
            System.err.println("Failed to read identity file: " + file + " -> " + e.getMessage());
        }
        return fallback;
    }
}
