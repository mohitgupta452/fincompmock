package com.fincompmock.expectation.gst.gstr2a2b;

import com.fincompmock.model.gst.gstr2a2b.Gstr2Models;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

/**
 * GSTR-2A / 2B mocks:
 * 1) POST /mock/gst/gstr2a/summary
 * 2) POST /mock/gst/gstr2b/summary
 */
public class Gstr2Expectation {

    private static final String GSTR2A_SUMMARY_JSON_PATH = "gst/gstr2a2b/gstr2a-summary-success.json";
    private static final String GSTR2B_SUMMARY_JSON_PATH = "gst/gstr2a2b/gstr2b-summary-success.json";

    public static void register(MockServerClient client) {
        registerGstr2aSummary(client);
        registerGstr2bSummary(client);
    }

    private static void registerGstr2aSummary(MockServerClient client) {
        String responseJson = FileUtil.readResource(GSTR2A_SUMMARY_JSON_PATH);
        Gstr2Models.Gstr2SummaryResponse mock = JsonUtil.fromJson(responseJson, Gstr2Models.Gstr2SummaryResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.GSTR2A_SUMMARY_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "gstin": "27AAPFU0939F1ZV",
                                                  "fp": "042024"
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

    private static void registerGstr2bSummary(MockServerClient client) {
        String responseJson = FileUtil.readResource(GSTR2B_SUMMARY_JSON_PATH);
        Gstr2Models.Gstr2SummaryResponse mock = JsonUtil.fromJson(responseJson, Gstr2Models.Gstr2SummaryResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.GSTR2B_SUMMARY_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "gstin": "27AAPFU0939F1ZV",
                                                  "fp": "042024"
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

    public static Gstr2Models.Gstr2SummaryResponse buildGstr2aFallbackModel() {
        String content = FileUtil.readResource(GSTR2A_SUMMARY_JSON_PATH);
        return JsonUtil.fromJson(content, Gstr2Models.Gstr2SummaryResponse.class);
    }

    public static Gstr2Models.Gstr2SummaryResponse buildGstr2bFallbackModel() {
        String content = FileUtil.readResource(GSTR2B_SUMMARY_JSON_PATH);
        return JsonUtil.fromJson(content, Gstr2Models.Gstr2SummaryResponse.class);
    }
}
