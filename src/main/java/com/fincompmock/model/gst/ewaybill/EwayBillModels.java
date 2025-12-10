package com.fincompmock.model.gst.ewaybill;

import java.time.LocalDateTime;

public final class EwayBillModels {

    private EwayBillModels() {
    }

    // POST /mock/ewaybill/generate
    public record GenerateEwbRequest(
            String supplyType,
            String subSupplyType,
            String docType,
            String docNo,
            String docDate,
            String fromGstin,
            String toGstin,
            double totalValue
            // etc: items, vehicle details
    ) { }

    public record GenerateEwbResponse(
            long ewbNo,
            String ewbDt,
            String validUpto,
            String status,
            String message
    ) { }

    // POST /mock/ewaybill/cancel
    public record CancelEwbRequest(
            long ewbNo,
            String cancelReason,
            String cancelRemark
    ) { }

    public record CancelEwbResponse(
            long ewbNo,
            String cnlDt,
            String status,
            String message
    ) { }

    // POST /mock/ewaybill/update-vehicle
    public record UpdateVehicleRequest(
            long ewbNo,
            String vehicleNo,
            String fromPlace,
            String fromState,
            LocalDateTime transDocDate
    ) { }

    public record UpdateVehicleResponse(
            long ewbNo,
            String status,
            String message
    ) { }

    // GET /mock/ewaybill/details?ewbNo=...
    public record GetEwbDetailsResponse(
            long ewbNo,
            String ewbDt,
            String validUpto,
            String fromGstin,
            String toGstin,
            double totalValue,
            String status
    ) { }
}
