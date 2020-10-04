package com.entra.core.basqueteapi.repository;

import com.entra.core.basqueteapi.model.JogoBasquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJogoBasqueteRepository extends JpaRepository<JogoBasquete, Integer> {

    Optional<JogoBasquete> findFirstByOrderByDataCriacaoDesc();
}
