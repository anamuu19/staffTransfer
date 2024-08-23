package com.example.StaffTransferManagementSpringBoot.Repository;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
    Optional<Institution> findByName(String name);
}

