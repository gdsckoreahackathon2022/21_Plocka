package com.jupging.jupgingServer.trashcan.repository;

import com.jupging.jupgingServer.trashcan.domain.Trashcan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrashcanRepository extends JpaRepository<Trashcan, Long> {

    List<Trashcan> findByUserId(Long userId);
    Optional<Trashcan> findByLatitudeAndLongitude(Double latitude, Double longitude);
    List<Trashcan> findAll();
}
