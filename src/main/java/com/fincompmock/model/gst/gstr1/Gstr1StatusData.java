package com.fincompmock.model.gst.gstr1;

public record Gstr1StatusData(
        String ack_no,
        String gstin,
        String fp,
        String ret_period,
        String current_status,
        String last_updated
) {}
