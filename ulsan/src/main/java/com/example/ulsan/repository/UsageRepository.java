package com.example.ulsan.repository;

import com.example.ulsan.model.entity.Usages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageRepository extends JpaRepository<Usages, Long> {
}
