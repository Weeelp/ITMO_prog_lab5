package manager;

import io.FileReader;
import io.FileWriter;
import model.movie.Movie;
import java.io.IOException;
import java.util.LinkedList;

public class FileManager {
    private FileReader fileReader;
    private FileWriter fileWriter;
    private String currentFilePath;
    
    public FileManager() {
        this.fileReader = new FileReader();
        this.fileWriter = new FileWriter();
    }
    
    public LinkedList<Movie> loadFromFile(String filePath) throws Exception {
        this.currentFilePath = filePath;
        LinkedList<Movie> movies = fileReader.loadFromFile(filePath);
        System.out.println("Загружено фильмов: " + movies.size());
        return movies;
    }
    
    public void saveToFile(LinkedList<Movie> movies) throws IOException {
        if (currentFilePath == null) {
            throw new IOException("Путь к файлу не указан");
        }
        fileWriter.saveToFile(currentFilePath, movies);
        System.out.println("Сохранено фильмов: " + movies.size());
    }
    
    public void saveToFile(String filePath, LinkedList<Movie> movies) throws IOException {
        this.currentFilePath = filePath;
        fileWriter.saveToFile(filePath, movies);
        System.out.println("Сохранено фильмов: " + movies.size());
    }
    
    public String getCurrentFilePath() {
        return currentFilePath;
    }
}