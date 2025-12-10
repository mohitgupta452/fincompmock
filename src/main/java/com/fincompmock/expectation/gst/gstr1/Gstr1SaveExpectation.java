package com.fincompmock.expectation.gst.gstr1;

import com.fincompmock.model.gst.gstr1.Gstr1SaveResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class Gstr1SaveExpectation {

    private static final String RESPONSE_JSON_PATH = "gst/gstr1/gstr1-save-success.json";

    public static void register(MockServerClient client) {

        // Load response from mock JSON (still fully controlled by file)
        String responseJson = FileUtil.readResource(RESPONSE_JSON_PATH);
        Gstr1SaveResponse mockResponse = JsonUtil.fromJson(responseJson, Gstr1SaveResponse.class);

        client
            .when(
                request()
                    .withMethod("POST")
                    .withPath(RequestPathUtil.GSTR1_SAVE_PATH)
                    .withHeader("Content-Type", "application/json")
                    // 👇 Only match key fields, ignore rest of body
                    .withBody(
                        json(
                            """
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
                    .withBody(JsonUtil.toJson(mockResponse))
            );
    }

    public static Gstr1SaveResponse buildFallbackModel() {
        String fileContent = FileUtil.readResource(RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, Gstr1SaveResponse.class);
    }
}
