package com.fincompmock.model.gst.gstr1;

public record Gstr1SaveResponseData(
        String ref_id,
        String gstin,
        String fp,
        String ret_period,
        Boolean error_report_available
) {}
