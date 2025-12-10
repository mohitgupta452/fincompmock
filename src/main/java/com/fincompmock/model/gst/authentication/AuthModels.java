package com.fincompmock.model.gst.authentication;

import java.time.Instant;

public final class AuthModels {

    private AuthModels() {
    }

    // POST /mock/auth/token
    public record AuthTokenRequest(
            String username,
            String password
    ) { }

    public record AuthTokenResponse(
            String access_token,
            String refresh_token,
            long expires_in,      // seconds
            Instant issued_at
    ) { }

    // POST /mock/auth/refresh
    public record RefreshTokenRequest(
            String refresh_token
    ) { }

    public record RefreshTokenResponse(
            String access_token,
            long expires_in,
            Instant issued_at
    ) { }
}
