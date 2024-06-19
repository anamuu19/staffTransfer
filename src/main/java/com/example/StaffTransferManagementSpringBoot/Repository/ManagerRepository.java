package com.example.StaffTransferManagementSpringBoot.Repository;

import com.example.StaffTransferManagementSpringBoot.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
//  @Query(value = "select p.FirstName, u.Address from Manager p join Institution u on institutionId = p.managerID", nativeQuery = true)
//Optional<Map<String,Object>>getAll();
}
