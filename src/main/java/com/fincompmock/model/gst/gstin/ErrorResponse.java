package com.fincompmock.model.gst.gstin;

import java.time.OffsetDateTime;

public record ErrorResponse(
        boolean success,
        String errorCode,
        String errorMessage,
        OffsetDateTime timestamp,
        String correlationId
) {}
