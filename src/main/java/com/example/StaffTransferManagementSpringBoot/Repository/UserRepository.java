package com.example.StaffTransferManagementSpringBoot.Repository;

import com.example.StaffTransferManagementSpringBoot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
