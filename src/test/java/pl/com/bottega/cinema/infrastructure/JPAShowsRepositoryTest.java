package pl.com.bottega.cinema.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.com.bottega.cinema.domain.ShowsRepository;
import pl.com.bottega.cinema.api.ShowDto;

import java.time.LocalDate;
import java.util.Collection;

import static java.time.Month.SEPTEMBER;

/**
 * Created by Sergej Povzaniuk on 16.09.2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml", "/mock-auth-context.xml"})
@TestPropertySource({"/jdbc.properties", "/hibernate.properties"})
@WebAppConfiguration
public class JPAShowsRepositoryTest {

    @Autowired
    private ShowsRepository showsRepository;


    @Test
    public void save() throws Exception {

    }



}