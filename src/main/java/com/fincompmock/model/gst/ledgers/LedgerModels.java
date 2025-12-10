package com.fincompmock.model.gst.ledgers;

import java.math.BigDecimal;
import java.util.List;

public final class LedgerModels {

    private LedgerModels() {
    }

    public record LedgerEntry(
            String desc,
            String date,
            BigDecimal debit,
            BigDecimal credit,
            BigDecimal balance
    ) { }

    // GET /mock/gst/ledger/cash
    public record CashLedgerResponse(
            String gstin,
            List<LedgerEntry> entries
    ) { }

    // GET /mock/gst/ledger/credit
    public record CreditLedgerResponse(
            String gstin,
            List<LedgerEntry> entries
    ) { }

    // GET /mock/gst/ledger/liability
    public record LiabilityLedgerResponse(
            String gstin,
            List<LedgerEntry> entries
    ) { }
}
