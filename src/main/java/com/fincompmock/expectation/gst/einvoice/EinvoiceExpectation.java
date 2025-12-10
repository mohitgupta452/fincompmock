package com.fincompmock.expectation.gst.einvoice;

import com.fincompmock.model.gst.einvoice.EinvoiceModels;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

/**
 * E-Invoice mocks:
 * 1) POST /mock/einv/irn/generate
 * 2) POST /mock/einv/irn/cancel
 * 3) GET  /mock/einv/irn/details
 */
public class EinvoiceExpectation {

    private static final String GENERATE_IRN_JSON_PATH = "gst/einvoice/einv-generate-irn-success.json";
    private static final String CANCEL_IRN_JSON_PATH = "gst/einvoice/einv-cancel-irn-success.json";
    private static final String GET_IRN_JSON_PATH = "gst/einvoice/einv-get-irn-success.json";

    public static void register(MockServerClient client) {
        registerGenerateIrn(client);
        registerCancelIrn(client);
        registerGetIrnDetails(client);
    }

    private static void registerGenerateIrn(MockServerClient client) {
        String responseJson = FileUtil.readResource(GENERATE_IRN_JSON_PATH);
        EinvoiceModels.GenerateIrnResponse mock = JsonUtil.fromJson(responseJson, EinvoiceModels.GenerateIrnResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.EINVOICE_GENERATE_IRN_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "gstin": "27AAPFU0939F1ZV",
                                                  "docType": "INV",
                                                  "docNo": "INV-001"
                                                }
                                                """,
                                                JsonBody.DEFAULT_MATCH_TYPE.ONLY_MATCHING_FIELDS
                                        )
                                )
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    private static void registerCancelIrn(MockServerClient client) {
        String responseJson = FileUtil.readResource(CANCEL_IRN_JSON_PATH);
        EinvoiceModels.CancelIrnResponse mock = JsonUtil.fromJson(responseJson, EinvoiceModels.CancelIrnResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.EINVOICE_CANCEL_IRN_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "irn": "IRN_SAMPLE",
                                                  "cancelReason": "1"
                                                }
                                                """,
                                                JsonBody.DEFAULT_MATCH_TYPE.ONLY_MATCHING_FIELDS
                                        )
                                )
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    private static void registerGetIrnDetails(MockServerClient client) {
        String responseJson = FileUtil.readResource(GET_IRN_JSON_PATH);
        EinvoiceModels.GetIrnDetailsResponse mock = JsonUtil.fromJson(responseJson, EinvoiceModels.GetIrnDetailsResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.EINVOICE_GET_IRN_PATH)
                                .withQueryStringParameter("irn", "IRN_SAMPLE")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static EinvoiceModels.GenerateIrnResponse buildGenerateIrnFallbackModel() {
        String content = FileUtil.readResource(GENERATE_IRN_JSON_PATH);
        return JsonUtil.fromJson(content, EinvoiceModels.GenerateIrnResponse.class);
    }

    public static EinvoiceModels.CancelIrnResponse buildCancelIrnFallbackModel() {
        String content = FileUtil.readResource(CANCEL_IRN_JSON_PATH);
        return JsonUtil.fromJson(content, EinvoiceModels.CancelIrnResponse.class);
    }

    public static EinvoiceModels.GetIrnDetailsResponse buildGetIrnFallbackModel() {
        String content = FileUtil.readResource(GET_IRN_JSON_PATH);
        return JsonUtil.fromJson(content, EinvoiceModels.GetIrnDetailsResponse.class);
    }
}
