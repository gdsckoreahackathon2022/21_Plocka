package com.jupging.jupgingServer.plogging.repository;

import com.jupging.jupgingServer.plogging.domain.Plogging;
import com.jupging.jupgingServer.plogging.dto.RankInfoProjection;
import com.jupging.jupgingServer.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PloggingRepository extends JpaRepository<Plogging, Long> {

    @Query(value = "select user_id as userId, count(*) as cnt, sum(distance) as distance from plogging where (date_format(created_date, '%Y-%m') = ?1) group by userId",
            countQuery  = "select user_id as userId, count(*) as cnt, sum(distance) as distance from plogging group by userId",
            nativeQuery = true)
    List<RankInfoProjection> findPloggingRankByCreatedDateWithPagination(String yearMonth, Pageable pageable);

    List<Plogging> findByUserId(Long userId);
}
