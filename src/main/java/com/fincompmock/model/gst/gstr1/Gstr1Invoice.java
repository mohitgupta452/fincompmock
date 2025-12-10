package com.fincompmock.model.gst.gstr1;

import java.util.List;

public record Gstr1Invoice(
        String inum,
        String idt,
        double val,
        String pos,
        String rchrg,
        String inv_typ,
        java.util.List<Gstr1Item> itms
) {}
