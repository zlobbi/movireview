package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    public Iterable<Movie> findAll(Sort s);

    public Iterable<Movie> findAllByReleaseYearGreaterThanEqual(int year, Sort s);

    public Iterable<Movie> findAllByReleaseYearBetween(int year, int year2, Sort s);

    @Query("{'releaseYear' : { '$gte' : ?0, '$lte' : ?1 }}")
    public Iterable<Movie> getMoviesBetween(int year, int year2, Sort s);

    public Iterable<Movie> findByRatingGreaterThanAndReleaseYear(double rating, int year);

    Iterable<Movie> findAllByTitleContains(String title);

    @Query("{'directors' : {'$in' : [ ?0 ]}}")
    Iterable<Movie> findAllByDirectorsContains(String director);

}
