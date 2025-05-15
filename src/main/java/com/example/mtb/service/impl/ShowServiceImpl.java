package com.example.mtb.service.impl;

import com.example.mtb.dto.MovieShowsRequest;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.entity.Movie;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import com.example.mtb.enums.ScreenType;
import com.example.mtb.exceptions.MovieNotFoundByIdException;
import com.example.mtb.exceptions.ScreenNotFoundByIdException;
import com.example.mtb.exceptions.ShowTimeConflictException;
import com.example.mtb.exceptions.TheaterNotFoundByIdException;
import com.example.mtb.mapper.ShowMapper;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.ShowRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final ShowMapper showMapper;


    @Override
    public ShowResponse addShow(String theaterId, String screenId, String movieId, Long startTime) {

        if (theaterRepository.existsById(theaterId)) {

            if (screenRepository.existsById(screenId)) {

                if (movieRepository.existsById(movieId)) {

                    Screen screen = screenRepository.findById(screenId).get();
                    Set<Show> shows = screen.getShows();

                    Movie movie = movieRepository.findById(movieId).get();

                    Instant instantStartTime = Instant.ofEpochMilli(startTime);


                    for (Show s : shows) {
                        Instant showStartTime = s.getStartsAt();
                        Instant showEndTime = s.getEndsAt();
                        Instant movieCompletionTime = instantStartTime.plus(movie.getRuntime());

                        if (!(movieCompletionTime.isBefore(showStartTime) || instantStartTime.isAfter(showEndTime))) {
                            throw new ShowTimeConflictException("Another Show is been Booked");
                        }
                    }

                    Show show = copy(new Show(), startTime, screen, movie);


                    return showMapper.showResponseMapper(show);

                }

                throw new MovieNotFoundByIdException("Movie Id not found in the database");
            }
            throw new ScreenNotFoundByIdException("Screen Id not found in the database");

        }
        throw new TheaterNotFoundByIdException("Theater Id not found in the database");
    }

    @Override
    public String fetchShows(String movieId, MovieShowsRequest showsRequest) {
        Instant start = showsRequest.date()
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        log.info(start.toString());

        Instant end = showsRequest.date()
                .plusDays(1)
                .atStartOfDay(ZoneOffset.UTC)
                .minusNanos(1)
                .toInstant();

        log.info(end.toString());
        var pageable = (Pageable) PageRequest.of(showsRequest.page() - 1, showsRequest.size());
        log.info(pageable.toString());
//        var pageOfTheaterIds = theaterRepository.findTheaterIdsWithMatchingShows(movieId, start, end, showsRequest.screenType().name(), pageable);
//        var pageOfTheaterIds = theaterRepository.findByScreens_ScreenTypeAndShows_Movie_MovieIdAndShows_StartsAtBetween(
//                showsRequest.screenType(), movieId, start, end, pageable
//        );
//        log.info(String.valueOf(pageOfTheaterIds.getTotalElements()));

        var setOfTheaterIds = showRepository.findDistinctByStartsAtBetweenAndMovie_MovieIdAndScreen_ScreenType(
                start, end, movieId, showsRequest.screenType()
        );
        log.info(setOfTheaterIds.toString());

        return "ok";
    }


    private Show copy(Show show, Long startTime, Screen screen, Movie movie) {
        show.setScreen(screen);
        show.setMovie(movie);
        Instant instantStartTime = Instant.ofEpochMilli(startTime);
        show.setStartsAt(instantStartTime);
        Instant endTime = instantStartTime.plus(movie.getRuntime());
        show.setEndsAt(endTime);
        show.setTheater(screen.getTheater());
        showRepository.save(show);
        return show;
    }
}
