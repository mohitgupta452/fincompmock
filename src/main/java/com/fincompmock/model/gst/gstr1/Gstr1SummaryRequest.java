package com.fincompmock.model.gst.gstr1;

public record Gstr1SummaryRequest(
        String gstin,
        String fp,
        String ret_period
) {}
