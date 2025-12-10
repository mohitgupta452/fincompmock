package com.fincompmock.model.gst.gstr2a2b;

import java.math.BigDecimal;
import java.util.List;

public final class Gstr2Models {

    private Gstr2Models() {
    }

    // POST /mock/gst/gstr2a/summary
    public record Gstr2aSummaryRequest(
            String gstin,
            String fp
    ) { }

    // POST /mock/gst/gstr2b/summary
    public record Gstr2bSummaryRequest(
            String gstin,
            String fp
    ) { }

    public record Gstr2SummaryResponse(
            String gstin,
            String fp,
            String type,                 // "2A" or "2B"
            List<CounterPartySummary> cp
    ) {

        public record CounterPartySummary(
                String ctin,            // supplier GSTIN
                BigDecimal txval,
                BigDecimal igst,
                BigDecimal cgst,
                BigDecimal sgst,
                BigDecimal cess,
                int inv_count
        ) { }
    }
}
