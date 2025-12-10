package com.fincompmock.expectation.gst.gstin;

import com.fincompmock.model.gst.gstin.GstinVerificationResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * GET /mock/gst/gstin/verify?gstin=27AAPFU0939F1ZV
 */
public class GstinVerificationExpectation {

    private static final String RESPONSE_JSON_PATH = "gst/gstin/gstinverification.json";

    public static void register(MockServerClient client) {

        String responseJson = FileUtil.readResource(RESPONSE_JSON_PATH);
        GstinVerificationResponse mock = JsonUtil.fromJson(responseJson, GstinVerificationResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.GSTIN_VERIFICATION_PATH)
                                .withQueryStringParameter("gstin", "27AAPFU0939F1ZV")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static GstinVerificationResponse buildFallbackModel() {
        String fileContent = FileUtil.readResource(RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, GstinVerificationResponse.class);
    }
}