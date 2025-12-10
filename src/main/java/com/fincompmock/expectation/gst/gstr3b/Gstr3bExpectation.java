package com.fincompmock.expectation.gst.gstr3b;

import com.fincompmock.model.gst.gstr3b.Gstr3bModels.Gstr3bFileResponse;
import com.fincompmock.model.gst.gstr3b.Gstr3bModels.Gstr3bSummaryResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

/**
 * GSTR-3B mocks:
 * 1) POST /mock/gst/gstr3b/file
 * 2) GET  /mock/gst/gstr3b/summary
 */
public class Gstr3bExpectation {

    private static final String FILE_RESPONSE_JSON_PATH = "gst/gstr3b/gstr3b-file-success.json";
    private static final String SUMMARY_RESPONSE_JSON_PATH = "gst/gstr3b/gstr3b-summary-success.json";

    public static void register(MockServerClient client) {
        registerGstr3bFile(client);
        registerGstr3bSummary(client);
    }

    private static void registerGstr3bFile(MockServerClient client) {
        String responseJson = FileUtil.readResource(FILE_RESPONSE_JSON_PATH);
        Gstr3bFileResponse mock = JsonUtil.fromJson(responseJson, Gstr3bFileResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.GSTR3B_FILE_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "gstin": "27AAPFU0939F1ZV",
                                                  "fp": "042024",
                                                  "ret_period": "042024"
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

    private static void registerGstr3bSummary(MockServerClient client) {
        String responseJson = FileUtil.readResource(SUMMARY_RESPONSE_JSON_PATH);
        Gstr3bSummaryResponse mock = JsonUtil.fromJson(responseJson, Gstr3bSummaryResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.GSTR3B_SUMMARY_PATH)
                                .withQueryStringParameter("gstin", "27AAPFU0939F1ZV")
                                .withQueryStringParameter("ret_period", "042024")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static Gstr3bFileResponse buildFileFallbackModel() {
        String content = FileUtil.readResource(FILE_RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(content, Gstr3bFileResponse.class);
    }

    public static Gstr3bSummaryResponse buildSummaryFallbackModel() {
        String content = FileUtil.readResource(SUMMARY_RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(content, Gstr3bSummaryResponse.class);
    }
}
