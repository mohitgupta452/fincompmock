package com.fincompmock.model.gst.gstr1;

public record Gstr1ItemDetails(
        double txval,
        double rt,
        double igst,
        double cgst,
        double sgst
) {}
