package com.fincompmock.util;

public interface RequestPathUtil {
    String GSTR1_SAVE_PATH = "/api/gst/gstr1/save";
    String GSTR1_SUBMIT_PATH = "/api/gst/gstr1/submit";
    String GSTR1_FILE_PATH = "/api/gst/gstr1/file";
    String GSTR1_SUMMARY_PATH = "/api/gst/gstr1/summary";
    String GSTR1_STATUS_PATH = "/api/gst/gstr1/status";

    String GSTIN_VERIFICATION_PATH = "/api/gst/gstin";
    String PAN_GSTIN_MAPPING_PATH = "/api/gst/pan";
    String RETURN_STATUS_PATH = "/api/gst/returns/status";
    String TAXPAYER_PROFILE_PATH = "/api/gst/gstin/profile";


    // ================= AUTH =================
    /**
     * POST /mock/auth/token
     */
    public static final String AUTH_TOKEN_PATH = "/mock/auth/token";

    /**
     * POST /mock/auth/refresh
     */
    public static final String AUTH_REFRESH_PATH = "/mock/auth/refresh";

    // ================= GSTR-3B =================
    /**
     * POST /mock/gst/gstr3b/file
     */
    public static final String GSTR3B_FILE_PATH = "/mock/gst/gstr3b/file";

    /**
     * GET /mock/gst/gstr3b/summary?gstin=...&ret_period=MMYYYY
     */
    public static final String GSTR3B_SUMMARY_PATH = "/mock/gst/gstr3b/summary";

    // ================= GSTR-2A / 2B =================
    /**
     * POST /mock/gst/gstr2a/summary
     */
    public static final String GSTR2A_SUMMARY_PATH = "/mock/gst/gstr2a/summary";

    /**
     * POST /mock/gst/gstr2b/summary
     */
    public static final String GSTR2B_SUMMARY_PATH = "/mock/gst/gstr2b/summary";

    // ================= OTP APIs =================
    /**
     * POST /mock/otp/send
     */
    public static final String OTP_SEND_PATH = "/mock/otp/send";

    /**
     * POST /mock/otp/verify
     */
    public static final String OTP_VERIFY_PATH = "/mock/otp/verify";

    // ================= E-INVOICE =================
    /**
     * POST /mock/einv/irn/generate
     */
    public static final String EINVOICE_GENERATE_IRN_PATH = "/mock/einv/irn/generate";

    /**
     * POST /mock/einv/irn/cancel
     */
    public static final String EINVOICE_CANCEL_IRN_PATH = "/mock/einv/irn/cancel";

    /**
     * GET /mock/einv/irn/details?irn=...
     */
    public static final String EINVOICE_GET_IRN_PATH = "/mock/einv/irn/details";

    // ================= E-WAY BILL =================
    /**
     * POST /mock/ewaybill/generate
     */
    public static final String EWB_GENERATE_PATH = "/mock/ewaybill/generate";

    /**
     * POST /mock/ewaybill/cancel
     */
    public static final String EWB_CANCEL_PATH = "/mock/ewaybill/cancel";

    /**
     * POST /mock/ewaybill/update-vehicle
     */
    public static final String EWB_UPDATE_VEHICLE_PATH = "/mock/ewaybill/update-vehicle";

    /**
     * GET /mock/ewaybill/details?ewbNo=...
     */
    public static final String EWB_GET_BY_NO_PATH = "/mock/ewaybill/details";

    // ================= LEDGERS =================
    /**
     * GET /mock/gst/ledger/cash?gstin=...
     */
    public static final String LEDGER_CASH_PATH = "/mock/gst/ledger/cash";

    /**
     * GET /mock/gst/ledger/credit?gstin=...
     */
    public static final String LEDGER_CREDIT_PATH = "/mock/gst/ledger/credit";

    /**
     * GET /mock/gst/ledger/liability?gstin=...
     */
    public static final String LEDGER_LIABILITY_PATH = "/mock/gst/ledger/liability";

}
