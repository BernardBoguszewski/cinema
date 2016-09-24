package pl.com.bottega.cinema.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bernard.boguszewski on 18.09.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Entity
@NamedQueries({
        @NamedQuery(name = "TicketPrice.findByShowId", query = "SELECT DISTINCT t FROM TicketPrice t " +
                "JOIN FETCH t.movie tm " +
                "JOIN FETCH tm.shows s " +
                "WHERE s.id = :showId")
})
public class TicketPrice implements Serializable {

    private static final long serialVersionUID = 4771799729423045009L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private BigDecimal price;

    @ManyToOne
    private Movie movie;

    public TicketPrice(String type, BigDecimal price, Movie movie) {
        this(null, type, price, movie);
    }
}
