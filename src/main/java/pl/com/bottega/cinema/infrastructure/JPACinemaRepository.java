package pl.com.bottega.cinema.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinema.api.InvalidRequestException;
import pl.com.bottega.cinema.domain.Cinema;
import pl.com.bottega.cinema.domain.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by bernard.boguszewski on 04.09.2016.
 */
@Repository
public class JPACinemaRepository implements CinemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public Cinema load(String name, String city) {
        List<Cinema> cinemas = entityManager.createNamedQuery("Cinema.findByNameAndCity", Cinema.class)
                .setParameter("name", name)
                .setParameter("city", city)
                .getResultList();
        return cinemas.size() == 0 ? null : cinemas.get(0);
    }

    @Override
    public Cinema load(Long cinemaId) {
        return entityManager.find(Cinema.class, cinemaId);
    }


}
