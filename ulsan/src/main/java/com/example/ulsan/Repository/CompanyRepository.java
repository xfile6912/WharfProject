package com.example.ulsan.Repository;

import com.example.ulsan.Model.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Long> {
}
