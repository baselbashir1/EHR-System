package com.ehr.clinicservice.services;

import com.ehr.clinicservice.dto.requests.ClinicRequest;
import com.ehr.clinicservice.dto.responses.ClinicResponse;
import com.ehr.clinicservice.mappers.ClinicMapper;
import com.ehr.clinicservice.models.Clinic;
import com.ehr.clinicservice.repositories.ClinicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;

    @Override
    public ClinicResponse getClinicByName(String name) {
        Clinic clinic = clinicRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        return clinicMapper.mapToClinicResponse(clinic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClinicResponse> getAllClinics() {
        return clinicRepository.findAll()
                .stream()
                .map(clinicMapper::mapToClinicResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addClinic(ClinicRequest clinicRequest) {
        validateClinic(clinicRequest.name());
        Clinic clinic = clinicMapper.mapToClinic(clinicRequest);
        Clinic addedClinic = clinicRepository.save(clinic);
        log.info("Clinic {} added successfully", addedClinic.getId());
    }

    @Override
    @Transactional
    public void editClinic(ClinicRequest clinicRequest, Long clinicId) {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        validateClinic(clinicRequest.name());
        Clinic updatedClinic = clinicMapper.mapToClinic(clinic, clinicRequest);
        clinicRepository.save(updatedClinic);
        log.info("Clinic {} updated successfully", updatedClinic.getId());
    }

    @Override
    @Transactional
    public void deleteClinic(Long clinicId) {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        clinicRepository.delete(clinic);
        log.info("Clinic {} deleted successfully", clinic.getId());
    }

    public void validateClinic(String name) {
        if (clinicRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Clinic already exists");
        }
    }

}
