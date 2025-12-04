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
 * Account-related expectations.
 * <p>
 * Endpoints:
 * GET  /mock/accounts/{userId}
 * POST /mock/accounts/{userId}/update
 * <p>
 * JSON is read from:
 * src/main/resources/accounts/{baseName}.json          (GET)
 * src/main/resources/accounts/{baseName}-update.json   (POST)
 */
public class WASAccountsExpectation {

    private final MockServerClient client;
    private final User user;
    private final String baseName;
    private final Path accountsDir;

    public WASAccountsExpectation(MockServerClient client, User user, String baseName) {
        this.client = client;
        this.user = user;
        this.baseName = baseName;

        String dir = System.getProperty("mock.accounts.dir", "src/main/resources/accounts");
        this.accountsDir = Paths.get(dir);
    }

    public void register() {
        registerAccountsLookup();
        registerAccountsUpdate();
    }

    private void registerAccountsLookup() {
        String path = "/mock/accounts/" + user.getUserId();
        Path jsonFile = accountsDir.resolve(baseName + ".json");

        System.out.println("Registering accounts lookup at: " + path +
                " using file: " + jsonFile.toAbsolutePath());

        String fallback = "[]";  // in case no file exists
        String body = readFileSafely(jsonFile, fallback);

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

    private void registerAccountsUpdate() {
        String path = "/mock/accounts/" + user.getUserId() + "/update";
        Path jsonFile = accountsDir.resolve(baseName + "-update.json");

        System.out.println("Registering accounts update at: " + path +
                " using file: " + jsonFile.toAbsolutePath());

        String fallback = "{ \"status\": \"UPDATED\", \"userId\": \"" + user.getUserId() + "\" }";
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
            System.err.println("Failed to read accounts file: " + file + " -> " + e.getMessage());
        }
        return fallback;
    }
}