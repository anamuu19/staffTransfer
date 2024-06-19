package com.example.StaffTransferManagementSpringBoot.Repository;

import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
