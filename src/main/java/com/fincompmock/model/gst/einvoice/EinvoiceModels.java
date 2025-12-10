package com.fincompmock.model.gst.einvoice;

import java.time.LocalDate;

public final class EinvoiceModels {

    private EinvoiceModels() {
    }

    // POST /mock/einv/irn/generate
    public record GenerateIrnRequest(
            String gstin,
            String docType,         // e.g. "INV"
            String docNo,
            LocalDate docDate,
            String buyerGstin,
            double totalValue
            // plus full einvoice payload if required
    ) { }

    public record GenerateIrnResponse(
            String irn,
            String ackNo,
            String ackDt,
            String signedInvoice,
            String status,
            String message
    ) { }

    // POST /mock/einv/irn/cancel
    public record CancelIrnRequest(
            String irn,
            String cancelReason,
            String cancelRemark
    ) { }

    public record CancelIrnResponse(
            String irn,
            String cnlDt,
            String status,
            String message
    ) { }

    // GET /mock/einv/irn/details?irn=...
    public record GetIrnDetailsResponse(
            String irn,
            String gstin,
            String docType,
            String docNo,
            LocalDate docDate,
            String buyerGstin,
            double totalValue,
            String status
    ) { }
}
