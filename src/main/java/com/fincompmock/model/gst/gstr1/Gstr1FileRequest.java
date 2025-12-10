package com.fincompmock.model.gst.gstr1;

public record Gstr1FileRequest(
        String gstin,
        String fp,
        String ret_period,
        String auth_mode,
        String otp,
        String submit_ref_id
) {}
