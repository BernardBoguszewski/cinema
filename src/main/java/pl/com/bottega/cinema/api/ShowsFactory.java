package pl.com.bottega.cinema.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinema.domain.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Sergej Povzaniuk on 14.09.2016.
 */

@Component
@AllArgsConstructor
public class ShowsFactory {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    Collection<Show> createShow(Long cinemaId, CreateShowsRequest request) {
        ShowsDto showsDto = request.getShows();
        CalendarDto calendarDto = showsDto.getCalendar();

        if (calendarDto == null) {
            showsDto.validate();
            return createShow(getCinema(cinemaId), getMovie(showsDto), showsDto);
        } else {
            calendarDto.validate();
            return createShow(getCinema(cinemaId), getMovie(showsDto), calendarDto);
        }
    }

    private Collection<Show> createShow(Cinema cinema, Movie movie, ShowsDto showsDto) {
        return showsDto.getDates().stream().map(date -> new Show(cinema, movie, date)).collect(Collectors.toList());
    }

    private Collection<Show> createShow(Cinema cinema, Movie movie, CalendarDto calendarDto) {
        Set<Show> resultShows = new HashSet<>();
        for(DayOfWeek dayOfWeek : calendarDto.getWeekDays()){
            LocalDateTime fromDate = getNearestDateForDay(calendarDto, dayOfWeek);
            while (haveNextDay(calendarDto, fromDate)) {
                for (LocalTime timeOfShow : calendarDto.getHours()) {
                    LocalDateTime dateOfShow = getDateOfShow(fromDate, timeOfShow);
                    if (haveNextDay(calendarDto, dateOfShow))
                        resultShows.add(new Show(cinema, movie, dateOfShow));
                }
                fromDate = getNextDateForActualDay(fromDate);
            }
        }
        return resultShows;
    }

    private LocalDateTime getNextDateForActualDay(LocalDateTime fromDate) {
        return fromDate.plusWeeks(1);
    }

    private LocalDateTime getDateOfShow(LocalDateTime fromDate, LocalTime timeOfShow) {
        return LocalDateTime.of(fromDate.getYear(), fromDate.getMonth(), fromDate.getDayOfMonth(), timeOfShow.getHour(), timeOfShow.getMinute());
    }

    private boolean haveNextDay(CalendarDto calendarDto, LocalDateTime fromDate) {
        return fromDate.isBefore(calendarDto.getUntilDate());
    }

    private LocalDateTime getNearestDateForDay(CalendarDto calendarDto, DayOfWeek day) {
        return getDateOfPeriodStart(calendarDto.getFromDate(), getNearestDateOfDay(calendarDto.getFromDate(), day));
    }

    private LocalDateTime getNearestDateOfDay(LocalDateTime from, DayOfWeek day) {
        return from.with(day);
    }

    private LocalDateTime getDateOfPeriodStart(LocalDateTime from, LocalDateTime thisDayOfWeek) {
        return from.isAfter(thisDayOfWeek) ? getNextDateForActualDay(thisDayOfWeek) : thisDayOfWeek;
    }

    private Movie getMovie(ShowsDto showsDto) {
        Movie movie = movieRepository.load(showsDto.getMovieId());
        validate(movie);
        return movie;
    }

    private Cinema getCinema(Long cinemaId) {
        Cinema cinema = cinemaRepository.load(cinemaId);
        validate(cinema);
        return cinema;
    }

    private void validate(Movie movie) {
        if (movie == null)
            throw new InvalidRequestException("Movie is absent");
    }

    private void validate(Cinema cinema) {
        if (cinema == null)
            throw new InvalidRequestException("Cinema is absent");
    }
}
