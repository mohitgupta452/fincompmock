package com.fincompmock.model.gst.gstr1;

public record Gstr1SubmitData(
        String gstin,
        String fp,
        String ret_period,
        String submit_ref_id
) {}
