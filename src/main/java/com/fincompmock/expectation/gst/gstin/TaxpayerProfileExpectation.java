package com.fincompmock.expectation.gst.gstin;

import com.fincompmock.model.gst.gstin.TaxpayerProfileResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * GET /mock/gst/gstin/profile?gstin=27AAACB2230M1Z5
 */
public class TaxpayerProfileExpectation {

    private static final String RESPONSE_JSON_PATH = "gst/gstin/gstintaxpayerprofile.json";

    public static void register(MockServerClient client) {

        String responseJson = FileUtil.readResource(RESPONSE_JSON_PATH);
        TaxpayerProfileResponse mock = JsonUtil.fromJson(responseJson, TaxpayerProfileResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.TAXPAYER_PROFILE_PATH)
                                .withQueryStringParameter("gstin", "27AAACB2230M1Z5")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static TaxpayerProfileResponse buildFallbackModel() {
        String fileContent = FileUtil.readResource(RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, TaxpayerProfileResponse.class);
    }
}