package com.fincompmock.model.gst.gstr1;

public record Gstr1SubmitResponse(
        String status,
        String message,
        Gstr1SubmitData data
) {}
