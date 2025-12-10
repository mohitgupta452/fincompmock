package com.fincompmock.model.gst.gstr1;

public record Gstr1SubmitRequest(
        String gstin,
        String fp,
        String ret_period,
        String ref_id
) {}
