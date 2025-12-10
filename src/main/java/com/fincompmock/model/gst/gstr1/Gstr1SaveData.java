package com.fincompmock.model.gst.gstr1;

import java.util.List;

public record Gstr1SaveData(
        java.util.List<Gstr1B2B> b2b,
        java.util.List<Gstr1B2CS> b2cs
) {}
