package manager;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import model.movie.Movie;
import utils.MovieComparators;
import utils.XMLParser;

public class Manager {
    private LinkedList<Movie> movies = new LinkedList<>();
    private ZonedDateTime inicializationDate;

    public void loadFromFile(String fileName) throws Exception {
        XMLParser parser = new XMLParser();
        LinkedList<Movie> parsedMovies = parser.parse(fileName);

        inicializationDate = ZonedDateTime.now();

        movies.clear();

        for (Movie movie : parsedMovies) {
            movies.add(movie);

        }
        movies.sort(MovieComparators.byIdAscending());
    }

    public Movie findById(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public long getMaxId() {
        if (movies.isEmpty()) {
            return 0;
        }

        long maxId = movies.get(0).getId();
        for (Movie movie : movies) {
        if (movie.getId() > maxId) {
            maxId = movie.getId();
        }
    }
    return maxId;
    }
    
    public void sortByIdAscending() {
        movies.sort(MovieComparators.byIdAscending());
    }

    public void sortByIdDescending() {
        movies.sort(MovieComparators.byIdDescending());
    }

    public ZonedDateTime getInicializationDate(){
        return inicializationDate;
    }

    public Movie getMaxByOscarsCount() {
        if (movies.isEmpty()) {
            return null;
        }

        return movies
                .stream()
                .max((m1, m2) -> Integer.compare( m1.getOscarsCount(), m2.getOscarsCount()))
                .orElse(null);
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public LinkedList<Movie> getAll() {
        return movies;
    }

    public boolean remove(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                return true;
            }
        }
        return false;
    }

    public void removeAll() {
        movies = new LinkedList<>();
    }

    public boolean removeLast() {
        movies.removeLast();

        return true;
    }

    public boolean removeGreaterThan(long id) {
        boolean removed = movies.removeIf(movie -> movie.getId() > id);

        return removed;
    }

    public long generateId() {
        if (movies.isEmpty()) {
            return 1L;
        } else {
            return this.getMaxId() + 1;
        }
    }

    public boolean isEmpty(){
        if (movies.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return true;
        }
        return false;
    }
public void saveToFile(String fileName) throws IOException {
    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName, false))) {
        
        StringBuilder xmlContent = new StringBuilder();
        xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlContent.append("<movies>\n");
        
        for (Movie movie : movies) {
            xmlContent.append("  <movie>\n");
            xmlContent.append("    <id>").append(movie.getId()).append("</id>\n");
            xmlContent.append("    <name>").append(movie.getName()).append("</name>\n");
            xmlContent.append("    <creationDate>").append(movie.getCreationDate()).append("</creationDate>\n");
            xmlContent.append("    <coordinates x=\"").append(movie.getCoordinates().getX())
                      .append("\" y=\"").append(movie.getCoordinates().getY()).append("\"></coordinates>\n");
            xmlContent.append("    <oscarsCount>").append(movie.getOscarsCount()).append("</oscarsCount>\n");
            xmlContent.append("    <totalBoxOffice>").append(movie.getTotalBoxOffice()).append("</totalBoxOffice>\n");
            xmlContent.append("    <usaBoxOffice>").append(movie.getUsaBoxOffice()).append("</usaBoxOffice>\n");
            xmlContent.append("    <genre>").append(movie.getGenre()).append("</genre>\n");
            xmlContent.append("    <screenwriter name=\"").append(movie.getScreenWriter().getName())
                      .append("\" height=\"").append(movie.getScreenWriter().getHeight())
                      .append("\" eyeColor=\"").append(movie.getScreenWriter().getEyeColor())
                      .append("\" hairColor=\"").append(movie.getScreenWriter().getHairColor())
                      .append("\" nationality=\"").append(movie.getScreenWriter().getNationality())
                      .append("\"></screenwriter>\n");
            xmlContent.append("  </movie>\n");
        }
        
        xmlContent.append("</movies>\n");
        
        bos.write(xmlContent.toString().getBytes("UTF-8"));
    }
}
}