package com.example.mtb.repository;

import com.example.mtb.entity.Theater;
import com.example.mtb.enums.ScreenType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface TheaterRepository extends JpaRepository<Theater, String> {

//    @Query(value = """
//            SELECT DISTINCT s.theater_id
//            FROM movie_show s
//            JOIN screen sc ON s.screen_id = sc.screen_id
//            WHERE s.movie_id = :movieId
//              AND s.starts_at BETWEEN :start AND :end
//              AND sc.screen_type = :screenType
//            """,
//            countQuery = """
//                    SELECT COUNT(DISTINCT s.theater_id)
//                    FROM movie_show s
//                    JOIN screen sc ON s.screen_id = sc.screen_id
//                    WHERE s.movie_id = :movieId
//                      AND s.starts_at BETWEEN :start AND :end
//                      AND sc.screen_type = :screenType
//                    """,
//            nativeQuery = true)
//    Page<String> findTheaterIdsWithMatchingShows(@Param("movieId") String movieId,
//                                                 @Param("start") Instant start,
//                                                 @Param("end") Instant end,
//                                                 @Param("screenType") ScreenType screenType,
//                                                 Pageable pageable);


    public static interface TheaterIds {
        String getTheaterId();
    }

    Page<TheaterIds> findByScreens_ScreenTypeAndShows_Movie_MovieIdAndShows_StartsAtBetween(
            ScreenType screenType, String movieId, Instant start, Instant end, Pageable pageable
    );
}

