package com.fincompmock.model.gst.gstin;

import java.time.LocalDate;
import java.util.List;

public record TaxpayerProfileResponse(
        String gstin,
        String legalName,
        String tradeName,
        String constitutionOfBusiness,
        LocalDate registrationDate,
        LocalDate cancellationDate,
        String status,
        PrincipalPlaceOfBusiness principalPlaceOfBusiness,
        List<AdditionalPlaceOfBusiness> additionalPlacesOfBusiness,
        List<String> natureOfBusiness,
        Contact contact,
        String source
) {

    public record PrincipalPlaceOfBusiness(
            String address,
            String city,
            String state,
            String pincode
    ) {}

    public record AdditionalPlaceOfBusiness(
            String stateCode,
            String address,
            String pincode
    ) {}

    public record Contact(
            String email,
            String mobile
    ) {}
}
