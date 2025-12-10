package com.fincompmock.expectation.gst.gstin;

import com.fincompmock.model.gst.gstin.ReturnStatusResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * GET /mock/gst/returns/status?gstin=27AAACB2230M1Z5&period=202501
 */
public class ReturnStatusExpectation {

    private static final String RESPONSE_JSON_PATH = "gst/gstin/gstinreturnfillingstatus.json";

    public static void register(MockServerClient client) {

        String responseJson = FileUtil.readResource(RESPONSE_JSON_PATH);
        ReturnStatusResponse mock = JsonUtil.fromJson(responseJson, ReturnStatusResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.RETURN_STATUS_PATH)
                                .withQueryStringParameter("gstin", "27AAACB2230M1Z5")
                                .withQueryStringParameter("period", "202501")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static ReturnStatusResponse buildFallbackModel() {
        String fileContent = FileUtil.readResource(RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, ReturnStatusResponse.class);
    }
}