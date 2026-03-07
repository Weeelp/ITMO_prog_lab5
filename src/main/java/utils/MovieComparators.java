package utils;

import java.util.Comparator;
import model.movie.Movie;

public class MovieComparators {
    
    public static Comparator<Movie> byIdAscending() {
        return Comparator.comparingLong(Movie::getId);
    }
    
    public static Comparator<Movie> byIdDescending() {
        return Comparator.comparingLong(Movie::getId).reversed();
    }
}