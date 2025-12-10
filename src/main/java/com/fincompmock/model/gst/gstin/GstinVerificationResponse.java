package com.fincompmock.model.gst.gstin;

import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.util.List;

public record GstinVerificationResponse(
        String gstin,
        boolean valid,
        String tradeName,
        String legalName,
        String stateCode,
        LocalDate registrationDate,
        String taxpayerType,
        String status,
        List<String> natureOfBusiness,
        Address address,
        OffsetDateTime lastUpdated,
        String source
) {
    public record Address(
            String buildingName,
            String floorNo,
            String street,
            String locality,
            String city,
            String state,
            String pincode
    ) {}
}
