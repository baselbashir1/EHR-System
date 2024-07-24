package com.ehr.clinicservice.services;

import com.ehr.clinicservice.dto.requests.ClinicRequest;
import com.ehr.clinicservice.dto.responses.ClinicResponse;

import java.util.List;

public interface ClinicService {

    ClinicResponse getClinicByName(String name);

    List<ClinicResponse> getAllClinics();

    void addClinic(ClinicRequest clinicRequest);

    void editClinic(ClinicRequest clinicRequest, Long clinicId);

    void deleteClinic(Long clinicId);

}
