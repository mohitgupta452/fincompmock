package com.fincompmock.model.gst.gstr1;

public record Gstr1SaveRequest(
        String gstin,
        String fp,
        String ret_period,
        String version,
        Gstr1SaveData data
) {}
