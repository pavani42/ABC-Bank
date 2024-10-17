package com.bankingapplication.repository;

import com.bankingapplication.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    // Add custom methods for beneficiary-related operations if needed
}