package com.fincompmock.model.gst.gstin;

import java.time.LocalDate;
import java.util.List;

public record PanGstinMappingResponse(
        String pan,
        List<GstinInfo> gstins,
        int count,
        String message,
        String source
) {
    public record GstinInfo(
            String gstin,
            String stateCode,
            String tradeName,
            LocalDate registrationDate,
            String status
    ) {}
}
