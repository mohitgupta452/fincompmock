package com.fincompmock.model.gst.gstr1;

import java.util.List;

public record Gstr1SaveResponse(
        String status,
        String message,
        Gstr1SaveResponseData data,
        String error_code,
        java.util.List<Gstr1ErrorDetail> errors
) {}
