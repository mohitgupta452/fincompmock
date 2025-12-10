package com.fincompmock.model.gst.gstr1;

public record Gstr1ErrorDetail(
        String section,
        Integer index,
        String field,
        String desc
) {}
