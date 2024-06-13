package com.literlaura.alura_challenge.repository;

import com.literlaura.alura_challenge.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {
    Optional<Authors> findByName(String name);
}
