package com.example.ulsan.Repository;

import com.example.ulsan.Model.Entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
}
