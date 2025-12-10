package com.fincompmock.expectation.gst.otp;

import com.fincompmock.model.gst.otp.OtpModels;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

/**
 * OTP mocks:
 * 1) POST /mock/otp/send
 * 2) POST /mock/otp/verify
 */
public class OtpExpectation {

    private static final String SEND_RESPONSE_JSON_PATH = "gst/otp/otp-send-success.json";
    private static final String VERIFY_RESPONSE_JSON_PATH = "gst/otp/otp-verify-success.json";

    public static void register(MockServerClient client) {
        registerSend(client);
        registerVerify(client);
    }

    private static void registerSend(MockServerClient client) {
        String responseJson = FileUtil.readResource(SEND_RESPONSE_JSON_PATH);
        OtpModels.OtpSendResponse mock = JsonUtil.fromJson(responseJson, OtpModels.OtpSendResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.OTP_SEND_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "mobile": "9999999999",
                                                  "purpose": "AUTH"
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

    private static void registerVerify(MockServerClient client) {
        String responseJson = FileUtil.readResource(VERIFY_RESPONSE_JSON_PATH);
        OtpModels.OtpVerifyResponse mock = JsonUtil.fromJson(responseJson, OtpModels.OtpVerifyResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.OTP_VERIFY_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "txnId": "TXN123456",
                                                  "otp": "123456"
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

    public static OtpModels.OtpSendResponse buildSendFallbackModel() {
        String content = FileUtil.readResource(SEND_RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(content, OtpModels.OtpSendResponse.class);
    }

    public static OtpModels.OtpVerifyResponse buildVerifyFallbackModel() {
        String content = FileUtil.readResource(VERIFY_RESPONSE_JSON_PATH);
        return JsonUtil.fromJson(content, OtpModels.OtpVerifyResponse.class);
    }
}
