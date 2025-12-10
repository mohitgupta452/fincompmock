package com.fincompmock.model.gst.gstr3b;

import java.util.List;
import java.math.BigDecimal;

public final class Gstr3bModels {

    private Gstr3bModels() {
    }

    // POST /mock/gst/gstr3b/file
    public record Gstr3bFileRequest(
            String gstin,
            String fp,          // filing period e.g. 042024
            String ret_period,  // often same as fp
            String gt,          // gross turnover
            String cur_gt,      // current gross turnover
            Object data         // keep generic, or define detail models later
    ) { }

    public record Gstr3bFileResponse(
            String status,          // e.g. "SUCCESS"
            String ackNo,
            String ackDt,
            String reference_id,
            String message
    ) { }

    // GET /mock/gst/gstr3b/summary?gstin=...&ret_period=...
    public record Gstr3bSummaryResponse(
            String gstin,
            String ret_period,
            String status,              // e.g. "FILED"
            List<SectionSummary> sections
    ) {

        public record SectionSummary(
                String sec_cd,          // section code
                BigDecimal txval,
                BigDecimal igst,
                BigDecimal cgst,
                BigDecimal sgst,
                BigDecimal cess
        ) { }
    }
}
