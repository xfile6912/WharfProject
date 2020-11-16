package com.example.ulsan.repository;

import com.example.ulsan.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Long> {
}
