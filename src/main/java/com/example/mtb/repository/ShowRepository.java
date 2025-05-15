package com.example.mtb.repository;

import com.example.mtb.dto.TheaterShowProjection;
import com.example.mtb.entity.Show;
import com.example.mtb.enums.ScreenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface ShowRepository extends JpaRepository<Show, String> {

//    @Query(value = """
//            SELECT
//                t.theater_id AS theaterId,
//                t.name AS theaterName,
//                t.landmark AS landmark,
//                t.city AS city,
//                sc.screen_id AS screenId,
//                sc.screen_type AS screenType,
//                s.show_id AS showId,
//                s.starts_at AS showStartsAt,
//                s.ends_at AS showEndsAt,
//                s.price AS price
//            FROM movie_show s
//            JOIN screen sc ON s.screen_id = sc.screen_id
//            JOIN theater t ON s.theater_id = t.theater_id
//            WHERE s.movie_id = :movieId
//              AND s.starts_at BETWEEN :start AND :end
//              AND sc.screen_type = :screenType
//              AND t.theater_id = :theaterId
//            """, nativeQuery = true)
//    List<TheaterShowProjection> fetchTheaterShowDetails(@Param("movieId") String movieId,
//                                                        @Param("start") Instant start,
//                                                        @Param("end") Instant end,
//                                                        @Param("screenType") String screenType,
//                                                        @Param("theaterId") String theaterId);


    public interface TheaterId {
        String getTheater_TheaterId();     // exactly matches the property name
    }

    // add "Distinct" so it uses a DISTINCT in SQL
    Set<TheaterId> findDistinctByStartsAtBetweenAndMovie_MovieIdAndScreen_ScreenType(
            Instant start,
            Instant end,
            String movieId,
            ScreenType screenType
    );

}
