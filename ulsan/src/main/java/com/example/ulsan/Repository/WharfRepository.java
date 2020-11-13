package com.example.ulsan.Repository;

import com.example.ulsan.Model.Entity.Wharf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WharfRepository extends JpaRepository<Wharf, Long> {
}
