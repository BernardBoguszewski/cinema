package pl.com.bottega.cinema.api.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

import static pl.com.bottega.cinema.domain.validators.CollectionValidator.collectionValidate;
import static pl.com.bottega.cinema.domain.validators.NumberValidator.entityIdValidate;

/**
 * Created by Admin on 25.09.2016.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReservationDto {
    private Long showId;
    private Set<TicketDto> tickets;
    private Set<SeatDto> seats;
    private CustomerDto customer;

    public void validate() {
        entityIdValidate(showId, "Show id is required");
        collectionValidate(tickets, "Tickets are required");
        collectionValidate(seats, "Seats are required");
        customer.validate();
    }

}
