package com.fincompmock.expectation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fincompmock.model.User;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Loads all mock user JSON files and delegates to individual expectation classes.
 */
public class FinCompEntry {

    private final MockServerClient client;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Path usersDir;

    public FinCompEntry(String hostAndPort) {
        String[] parts = hostAndPort.split(":");
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);
        this.client = new MockServerClient(host, port);

        String dir = System.getProperty("mock.users.dir", "src/main/resources/mock-users");
        this.usersDir = Paths.get(dir);
        System.out.println("Using mock users directory -> " + usersDir.toAbsolutePath());
    }

    public void registerAll() {
        try (Stream<Path> stream = Files.list(usersDir)) {
            List<Path> files = stream
                    .filter(p -> p.toString().endsWith(".json"))
                    .toList();

            for (Path p : files) {
                String fileName = p.getFileName().toString();        // example-user-01.json
                String baseName = fileName.substring(0, fileName.lastIndexOf('.')); // example-user-01

                try {
                    String body = Files.readString(p);
                    User user = mapper.readValue(body, User.class);

                    // 1) main user endpoint from mock-users JSON
                    registerRawUserEndpoint(user, body);

                    // 2) delegate to other expectations, which will load their own JSON
                    new WASIdentityAssertionExpectation(client, user, baseName).register();
                    new WASInvolvedPartyExpectation(client, user, baseName).register();
                    new WASAccountsExpectation(client, user, baseName).register();

                    System.out.println("Registered expectations for user: " +
                            user.getUserId() + " from file: " + fileName);
                } catch (IOException e) {
                    System.err.println("Failed to read/parse user file: " + p + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to list mock users directory: " + usersDir + " -> " + e.getMessage());
        }
    }

    /**
     * GET /mock/user/{userId} -> full mock-users JSON
     */
    private void registerRawUserEndpoint(User user, String body) {
        String path = "/mock/user/" + user.getUserId();
        System.out.println("Registering raw user endpoint at: " + path);

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
}