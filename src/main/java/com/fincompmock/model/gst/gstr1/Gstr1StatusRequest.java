package com.fincompmock.model.gst.gstr1;

public record Gstr1StatusRequest(
        String gstin,
        String fp,
        String ret_period,
        String ack_no
) {}
