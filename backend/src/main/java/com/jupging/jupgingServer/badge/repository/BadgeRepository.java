package com.jupging.jupgingServer.badge.repository;

import com.jupging.jupgingServer.badge.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    // https://pythonq.com/so/spring/661883
    // 해당 id의 user가 가진 모든 badge 검색
    List<Badge> findByUserId(Long userId);
}
