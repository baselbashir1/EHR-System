package com.ehr.userservice.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "doctors")
public class Doctor extends BaseModel {

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "clinic_id")
    private Long clinicId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
