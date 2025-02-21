package org.example.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface teamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findByName(String name);

}
