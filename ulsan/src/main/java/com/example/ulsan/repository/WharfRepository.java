package com.example.ulsan.repository;

import com.example.ulsan.model.entity.Wharf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WharfRepository extends JpaRepository<Wharf, Long> {
}
