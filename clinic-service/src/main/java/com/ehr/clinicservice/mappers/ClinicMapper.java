package com.ehr.clinicservice.mappers;

import com.ehr.clinicservice.dto.requests.ClinicRequest;
import com.ehr.clinicservice.dto.responses.ClinicResponse;
import com.ehr.clinicservice.models.Clinic;

public interface ClinicMapper {

    Clinic mapToClinic(ClinicRequest clinicRequest);

    Clinic mapToClinic(Clinic clinic, ClinicRequest clinicRequest);

    ClinicResponse mapToClinicResponse(Clinic clinic);

}
