package com.fincompmock.expectation.gst.gstr1;

import com.fincompmock.model.gst.gstr1.Gstr1StatusResponse;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class Gstr1StatusExpectation {

    private static final String RESPONSE_JSON_PATH = "gst/gstr1/gstr1-status-success.json";

    public static void register(MockServerClient client) {

        String responseJson = FileUtil.readResource(RESPONSE_JSON_PATH);
        Gstr1StatusResponse mock = JsonUtil.fromJson(responseJson, Gstr1StatusResponse.class);

        client
            .when(
                request()
                    .withMethod("POST")
                    .withPath(RequestPathUtil.GSTR1_STATUS_PATH)
                    .withHeader("Content-Type", "application/json")
                    .withBody(
                        json(
                            """
                            {
                              "gstin": "27AAPFU0939F1ZV",
                              "fp": "042024",
                              "ret_period": "042024",
                              "ack_no": "ACKGSTR1202404PQR567"
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

    public static Gstr1StatusResponse buildFallbackModel() {
        String fileContent = FileUtil.readResource(RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(fileContent, Gstr1StatusResponse.class);
    }
}
