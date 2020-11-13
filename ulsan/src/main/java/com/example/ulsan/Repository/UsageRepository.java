package com.example.ulsan.Repository;

import com.example.ulsan.Model.Entity.Usages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageRepository extends JpaRepository<Usages, Long> {
}
