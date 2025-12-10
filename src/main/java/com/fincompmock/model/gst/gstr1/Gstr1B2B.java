package com.fincompmock.model.gst.gstr1;

import java.util.List;

public record Gstr1B2B(
        String ctin,
        java.util.List<Gstr1Invoice> inv
) {}
