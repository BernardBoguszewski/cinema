package pl.com.bottega.cinema.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinema.api.factory.ReservationFactory;
import pl.com.bottega.cinema.api.request.CalculatePriceRequest;
import pl.com.bottega.cinema.api.request.CreateReservationRequest;
import pl.com.bottega.cinema.api.request.GetMoviesAtDateRequest;
import pl.com.bottega.cinema.api.response.*;
import pl.com.bottega.cinema.domain.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import static pl.com.bottega.cinema.domain.validators.ObjectValidator.notNullValidate;

/**
 * Created by Sergej Povzaniuk on 23.09.2016.
 */
@AllArgsConstructor
@Service
public class CustomerService {


    private PriceCalculator priceCalculator;
    private CinemaCatalog cinemaCatalog;
    private MovieCatalog movieCatalog;
    private ReservationFactory reservationFactory;
    private ReservationRepository reservationRepository;
    private ShowsRepository showsRepository;

    public ListAllCinemasResponse listAll() {
        return cinemaCatalog.listAll();
    }

    public ListMoviesResponse findMoviesInCinemaByDate(GetMoviesAtDateRequest request) {
        request.validate();
        Collection<Movie> movies = movieCatalog.findMoviesInCinemaByDate(request.getCinemaId(), request.getDate());
        return new ListMoviesResponse(movies);
    }

    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
        request.validate();
        return priceCalculator.calculatePrice(request);
    }

    public ReservationResponse createReservation(CreateReservationRequest request) {
        request.validate();
        Reservation reservation = reservationFactory.createReservation(request);
        return new ReservationResponse(reservation);
    }

    @Transactional
    public SeatsResponse getSeatsByShowId(Long showId) {
        Show show = getExistShow(showId);
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        return cinemaHall.getSeatsResponse();
    }

    private Show getExistShow(Long showId) {
        Show show =  showsRepository.load(showId);
        notNullValidate(show, "Show not exist!");
        return show;
    }
}