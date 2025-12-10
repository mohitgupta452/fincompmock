package com.fincompmock.expectation.gst.gstin;

import com.fincompmock.model.gst.gstin.PanGstinMappingResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * GET /mock/gst/pan/mapping?pan=AAACB2230M
 */
public class PanGstinMappingExpectation {

    private static final String RESPONSE_JSON_PATH = "gst/gstin/gstinpanmapping.json";

    public static void register(MockServerClient client) {

        String responseJson = FileUtil.readResource(RESPONSE_JSON_PATH);
        PanGstinMappingResponse mock = JsonUtil.fromJson(responseJson, PanGstinMappingResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.PAN_GSTIN_MAPPING_PATH)
                                .withQueryStringParameter("pan", "AAACB2230M")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static PanGstinMappingResponse buildFallbackModel() {
        String fileContent = FileUtil.readResource(RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, PanGstinMappingResponse.class);
    }
}