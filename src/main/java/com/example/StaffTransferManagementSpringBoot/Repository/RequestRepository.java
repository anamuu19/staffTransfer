package com.example.StaffTransferManagementSpringBoot.Repository;

import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Integer> {
    List<Request> findByEmail(String email);
    List<Request> findByStatus(String status);
}
