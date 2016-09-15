package pl.com.bottega.cinema.ui;/* Created by Sergej on 04.09.2016. Bottega IT Solutions */

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinema.api.AdminPanel;
import pl.com.bottega.cinema.api.CreateMovieRequest;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {

    private AdminPanel adminPanel;

    @PutMapping
    public void create(@RequestBody CreateMovieRequest createMovieRequest) {
        createMovieRequest.validate();
        adminPanel.createMovie(createMovieRequest);
    }
}
