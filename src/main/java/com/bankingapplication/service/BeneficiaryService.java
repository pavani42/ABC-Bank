package com.bankingapplication.service;

import com.bankingapplication.model.Beneficiary;
import com.bankingapplication.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public Beneficiary addBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    public Beneficiary updateBeneficiary(Long id, Beneficiary beneficiary) {
        Optional<Beneficiary> existingBeneficiary = beneficiaryRepository.findById(id);
        if (existingBeneficiary.isPresent()) {
            Beneficiary updatedBeneficiary = existingBeneficiary.get();
            updatedBeneficiary.setName(beneficiary.getName());
            updatedBeneficiary.setAccountNumber(beneficiary.getAccountNumber());
            updatedBeneficiary.setBankName(beneficiary.getBankName());
            updatedBeneficiary.setIfscCode(beneficiary.getIfscCode());
            return beneficiaryRepository.save(updatedBeneficiary);
        } else {
            throw new RuntimeException("Beneficiary not found");
        }
    }

    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}