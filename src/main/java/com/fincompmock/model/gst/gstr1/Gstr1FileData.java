package com.fincompmock.model.gst.gstr1;

public record Gstr1FileData(
        String gstin,
        String fp,
        String ret_period,
        String ack_no,
        String ack_dt,
        String status
) {}
