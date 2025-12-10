package com.fincompmock.expectation.gst.ledgers;

import com.fincompmock.model.gst.ledgers.LedgerModels.CashLedgerResponse;
import com.fincompmock.model.gst.ledgers.LedgerModels.CreditLedgerResponse;
import com.fincompmock.model.gst.ledgers.LedgerModels.LiabilityLedgerResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Ledger mocks:
 * 1) GET /mock/gst/ledger/cash
 * 2) GET /mock/gst/ledger/credit
 * 3) GET /mock/gst/ledger/liability
 */
public class LedgerExpectation {

    private static final String CASH_LEDGER_JSON_PATH = "gst/ledgers/cash-ledger-success.json";
    private static final String CREDIT_LEDGER_JSON_PATH = "gst/ledgers/credit-ledger-success.json";
    private static final String LIABILITY_LEDGER_JSON_PATH = "gst/ledgers/liability-ledger-success.json";

    public static void register(MockServerClient client) {
        registerCashLedger(client);
        registerCreditLedger(client);
        registerLiabilityLedger(client);
    }

    private static void registerCashLedger(MockServerClient client) {
        String responseJson = FileUtil.readResource(CASH_LEDGER_JSON_PATH);
        CashLedgerResponse mock = JsonUtil.fromJson(responseJson, CashLedgerResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.LEDGER_CASH_PATH)
                                .withQueryStringParameter("gstin", "27AAPFU0939F1ZV")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    private static void registerCreditLedger(MockServerClient client) {
        String responseJson = FileUtil.readResource(CREDIT_LEDGER_JSON_PATH);
        CreditLedgerResponse mock = JsonUtil.fromJson(responseJson, CreditLedgerResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.LEDGER_CREDIT_PATH)
                                .withQueryStringParameter("gstin", "27AAPFU0939F1ZV")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    private static void registerLiabilityLedger(MockServerClient client) {
        String responseJson = FileUtil.readResource(LIABILITY_LEDGER_JSON_PATH);
        LiabilityLedgerResponse mock = JsonUtil.fromJson(responseJson, LiabilityLedgerResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.LEDGER_LIABILITY_PATH)
                                .withQueryStringParameter("gstin", "27AAPFU0939F1ZV")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static CashLedgerResponse buildCashLedgerFallbackModel() {
        String content = FileUtil.readResource(CASH_LEDGER_JSON_PATH);
        return JsonUtil.fromJson(content, CashLedgerResponse.class);
    }

    public static CreditLedgerResponse buildCreditLedgerFallbackModel() {
        String content = FileUtil.readResource(CREDIT_LEDGER_JSON_PATH);
        return JsonUtil.fromJson(content, CreditLedgerResponse.class);
    }

    public static LiabilityLedgerResponse buildLiabilityLedgerFallbackModel() {
        String content = FileUtil.readResource(LIABILITY_LEDGER_JSON_PATH);
        return JsonUtil.fromJson(content, LiabilityLedgerResponse.class);
    }
}
