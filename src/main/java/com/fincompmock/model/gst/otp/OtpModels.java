package com.fincompmock.model.gst.otp;

public final class OtpModels {

    private OtpModels() {
    }

    // POST /mock/otp/send
    public record OtpSendRequest(
            String mobile,
            String email,
            String purpose      // e.g. "AUTH", "EWAYBILL"
    ) { }

    public record OtpSendResponse(
            String status,      // "SUCCESS"
            String txnId,
            String message
    ) { }

    // POST /mock/otp/verify
    public record OtpVerifyRequest(
            String txnId,
            String otp
    ) { }

    public record OtpVerifyResponse(
            String status,      // "VERIFIED", "FAILED"
            String message
    ) { }
}
