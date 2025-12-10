package com.fincompmock.expectation.gst.ewaybill;

import com.fincompmock.model.gst.ewaybill.EwayBillModels;
import com.fincompmock.util.FileUtil;
import com.fincompmock.util.JsonUtil;
import com.fincompmock.util.RequestPathUtil;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.JsonBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

/**
 * E-Way Bill mocks:
 * 1) POST /mock/ewaybill/generate
 * 2) POST /mock/ewaybill/cancel
 * 3) POST /mock/ewaybill/update-vehicle
 * 4) GET  /mock/ewaybill/details
 */
public class EwayBillExpectation {

    private static final String GENERATE_JSON_PATH = "gst/ewaybill/ewb-generate-success.json";
    private static final String CANCEL_JSON_PATH = "gst/ewaybill/ewb-cancel-success.json";
    private static final String UPDATE_VEHICLE_JSON_PATH = "gst/ewaybill/ewb-update-vehicle-success.json";
    private static final String GET_DETAILS_JSON_PATH = "gst/ewaybill/ewb-get-details-success.json";

    public static void register(MockServerClient client) {
        registerGenerate(client);
        registerCancel(client);
        registerUpdateVehicle(client);
        registerGetDetails(client);
    }

    private static void registerGenerate(MockServerClient client) {
        String responseJson = FileUtil.readResource(GENERATE_JSON_PATH);
        EwayBillModels.GenerateEwbResponse mock = JsonUtil.fromJson(responseJson, EwayBillModels.GenerateEwbResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.EWB_GENERATE_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "fromGstin": "27AAPFU0939F1ZV",
                                                  "toGstin": "27ABCDE1234F1Z2",
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

    private static void registerCancel(MockServerClient client) {
        String responseJson = FileUtil.readResource(CANCEL_JSON_PATH);
        EwayBillModels.CancelEwbResponse mock = JsonUtil.fromJson(responseJson, EwayBillModels.CancelEwbResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.EWB_CANCEL_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "ewbNo": 111122223333,
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

    private static void registerUpdateVehicle(MockServerClient client) {
        String responseJson = FileUtil.readResource(UPDATE_VEHICLE_JSON_PATH);
        EwayBillModels.UpdateVehicleResponse mock = JsonUtil.fromJson(responseJson, EwayBillModels.UpdateVehicleResponse.class);

        client
                .when(
                        request()
                                .withMethod("POST")
                                .withPath(RequestPathUtil.EWB_UPDATE_VEHICLE_PATH)
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        json("""
                                                {
                                                  "ewbNo": 111122223333,
                                                  "vehicleNo": "MH12AB1234"
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

    private static void registerGetDetails(MockServerClient client) {
        String responseJson = FileUtil.readResource(GET_DETAILS_JSON_PATH);
        EwayBillModels.GetEwbDetailsResponse mock = JsonUtil.fromJson(responseJson, EwayBillModels.GetEwbDetailsResponse.class);

        client
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(RequestPathUtil.EWB_GET_BY_NO_PATH)
                                .withQueryStringParameter("ewbNo", "111122223333")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(JsonUtil.toJson(mock))
                );
    }

    public static EwayBillModels.GenerateEwbResponse buildGenerateFallbackModel() {
        String content = FileUtil.readResource(GENERATE_JSON_PATH);
        return JsonUtil.fromJson(content, EwayBillModels.GenerateEwbResponse.class);
    }

    public static EwayBillModels.CancelEwbResponse buildCancelFallbackModel() {
        String content = FileUtil.readResource(CANCEL_JSON_PATH);
        return JsonUtil.fromJson(content, EwayBillModels.CancelEwbResponse.class);
    }

    public static EwayBillModels.UpdateVehicleResponse buildUpdateVehicleFallbackModel() {
        String content = FileUtil.readResource(UPDATE_VEHICLE_JSON_PATH);
        return JsonUtil.fromJson(content, EwayBillModels.UpdateVehicleResponse.class);
    }

    public static EwayBillModels.GetEwbDetailsResponse buildGetDetailsFallbackModel() {
        String content = FileUtil.readResource(GET_DETAILS_JSON_PATH);
        return JsonUtil.fromJson(content, EwayBillModels.GetEwbDetailsResponse.class);
    }
}
