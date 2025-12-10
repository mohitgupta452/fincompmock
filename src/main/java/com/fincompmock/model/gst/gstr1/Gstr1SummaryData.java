package com.fincompmock.model.gst.gstr1;

public record Gstr1SummaryData(
        String gstin,
        String fp,
        String ret_period,
        Gstr1SupDetails sup_details,
        Integer ctin_count,
        Integer inv_count
) {}
