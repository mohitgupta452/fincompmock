package com.fincompmock.model.gst.gstin;

import java.time.LocalDate;

public record ReturnStatusResponse(
        String gstin,
        String period,
        String returnType,
        String status,
        String arn,
        LocalDate filedDate,
        String mode,
        String message,
        String source
) {}
