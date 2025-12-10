package com.fincompmock.expectation.gst.authentication;

import com.fincompmock.model.gst.authentication.AuthModels;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

/**
 * Authentication mocks:
 * 1) POST /mock/auth/token
 * 2) POST /mock/auth/refresh
 */
public class AuthExpectation {

    private static final String AUTH_TOKEN_RESPONSE_JSON_PATH = "gst/auth/auth-token-success.json";
    private static final String REFRESH_TOKEN_RESPONSE_JSON_PATH = "gst/auth/refresh-token-success.json";

    public static void register(MockServerClient client) {
        registerAuthToken(client);
        registerRefreshToken(client);
    }

    private static void registerAuthToken(MockServerClient client) {
        String responseJson = FileUtil.readResource(AUTH_TOKEN_RESPONSE_JSON_PATH);
        AuthModels.AuthTokenResponse mock = JsonUtil.fromJson(responseJson, AuthModels.AuthTokenResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.AUTH_TOKEN_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "username": "sandbox-user",
                                                  "password": "password"
                                                }
                                                """,
                                                JsonBody.DEFAULT_MATCH_TYPE.ONLY_MATCHING_FIELDS
                                        )
                                )
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    private static void registerRefreshToken(MockServerClient client) {
        String responseJson = FileUtil.readResource(REFRESH_TOKEN_RESPONSE_JSON_PATH);
        AuthModels.RefreshTokenResponse mock = JsonUtil.fromJson(responseJson, AuthModels.RefreshTokenResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.AUTH_REFRESH_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "refresh_token": "REFRESH_TOKEN_SAMPLE"
                                                }
                                                """,
                                                JsonBody.DEFAULT_MATCH_TYPE.ONLY_MATCHING_FIELDS
                                        )
                                )
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static AuthModels.AuthTokenResponse buildAuthTokenFallbackModel() {
        String fileContent = FileUtil.readResource(AUTH_TOKEN_RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, AuthModels.AuthTokenResponse.class);
    }

    public static AuthModels.RefreshTokenResponse buildRefreshTokenFallbackModel() {
        String fileContent = FileUtil.readResource(REFRESH_TOKEN_RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, AuthModels.RefreshTokenResponse.class);
    }
}
