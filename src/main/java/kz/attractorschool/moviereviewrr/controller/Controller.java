package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.repository.MovieRepository;
import kz.attractorschool.moviereviewrr.repository.ReviewRepository;
import kz.attractorschool.moviereviewrr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    MovieRepository mr;

    @Autowired
    ReviewRepository rr;

    @Autowired
    UserRepository ur;

    @GetMapping("/movie")
    public Iterable<Movie> getMovie() {
        Sort s = Sort.by(Sort.Order.asc("title"));

        return mr.findAll(s);
    }

    @GetMapping("/movienew/{year}")
    public Iterable<Movie> getMovie(@PathVariable("year") int year) {
        Sort s = Sort.by(Sort.Order.asc("title"));

        return mr.findAllByReleaseYearGreaterThanEqual(year, s);
    }

    @GetMapping("/movienew/{year}/{year2}")
    public Iterable<Movie> getMovie(@PathVariable("year") int year,
                                    @PathVariable("year2") int year2) {
        Sort s = Sort.by(Sort.Order.asc("title"));

        return mr.findAllByReleaseYearBetween(year, year2, s);
    }

    @GetMapping("/moviebetween/{year}/{year2}")
    public Iterable<Movie> getMovieBetween(@PathVariable("year") int year,
                                           @PathVariable("year2") int year2) {
        Sort s = Sort.by(Sort.Order.asc("title"));

        return mr.getMoviesBetween(year, year2, s);
    }

    @GetMapping("/movierating/{rating}/{year}")
    public Iterable<Movie> getMovieWithRaitAndYear(@PathVariable("rating") double rating,
                                                   @PathVariable("year") int year) {
        return mr.findByRatingGreaterThanAndReleaseYear(rating, year);
    }

    @GetMapping("/movietitle/{title}")
    public Iterable<Movie> getMovieTitleContains(@PathVariable("title") String title) {
        return mr.findAllByTitleContains(title);
    }

    @GetMapping("movies/{director}")
    public Iterable<Movie> getDirectorMovies(@PathVariable("director") String director) {
        return mr.findAllByDirectorsContains(director);
    }
}
