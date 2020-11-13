package com.example.ulsan.Repository;

import com.example.ulsan.Model.Entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
}
