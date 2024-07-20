package com.ehr.clinicservice.mappers;

import com.ehr.clinicservice.dto.requests.ClinicRequest;
import com.ehr.clinicservice.dto.responses.ClinicResponse;
import com.ehr.clinicservice.models.Clinic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicMapper {

    public Clinic mapToClinic(ClinicRequest clinicRequest) {
        return Clinic.builder()
                .name(clinicRequest.name())
                .address(clinicRequest.address())
                .phoneNumber(clinicRequest.phoneNumber())
                .build();
    }

    public Clinic mapToClinic(Clinic clinic, ClinicRequest clinicRequest) {
        clinic.setName(clinicRequest.name());
        clinic.setAddress(clinicRequest.address());
        clinic.setPhoneNumber(clinicRequest.phoneNumber());
        return clinic;
    }

    public ClinicResponse mapToClinicResponse(Clinic clinic) {
        return ClinicResponse.builder()
                .clinicId(clinic.getId())
                .name(clinic.getName())
                .address(clinic.getAddress())
                .phoneNumber(clinic.getPhoneNumber())
                .build();
    }

}
