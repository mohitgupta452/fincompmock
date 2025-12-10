package com.fincompmock.model.gst.gstr1;

public record Gstr1FileResponse(
        String status,
        String message,
        Gstr1FileData data
) {}
