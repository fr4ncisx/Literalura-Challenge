package com.literlaura.alura_challenge.repository;

import com.literlaura.alura_challenge.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {
}
