package manager;

import io.FileReader;
import io.FileWriter;
import model.movie.Movie;
import java.io.FileNotFoundException;
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
    
    public LinkedList<Movie> loadFromFile(String filePath) {
        this.currentFilePath = filePath;
        try {
            LinkedList<Movie> movies = fileReader.loadFromFile(filePath);
            System.out.println("> Загружено фильмов: " + movies.size());
            return movies;
        } catch (FileNotFoundException e) {
            System.out.println("> Файл не найден. Будет создан новый файл: " + currentFilePath);
            createEmptyFile(currentFilePath);
            return new LinkedList<>();
        } catch (Exception e) {
            int dotIndex = filePath.lastIndexOf('.');
            if (dotIndex > 0) {
                this.currentFilePath = filePath.substring(0, dotIndex) + "_new.xml";
            } else { this.currentFilePath = filePath + "_new.xml"; }

            System.out.println("> Файл повреждён или имеет неверный формат. Будет создан новый файл: " + currentFilePath);
            createEmptyFile(currentFilePath);
            return new LinkedList<>();
        }
    }
    
    private void createEmptyFile(String filePath) {
        try {
            fileWriter.saveToFile(filePath, new LinkedList<>());
        } catch (IOException ex) {
            System.err.println("> Не удалось создать пустой файл: " + ex.getMessage());
        }
    }
    
    public void saveToFile(LinkedList<Movie> movies) throws IOException {
        if (currentFilePath == null) {
            throw new IOException("Путь к файлу не указан");
        }
        fileWriter.saveToFile(currentFilePath, movies);
        System.out.println("> Сохранено фильмов: " + movies.size());
    }
    
    public void saveToFile(String filePath, LinkedList<Movie> movies) throws IOException {
        this.currentFilePath = filePath;
        fileWriter.saveToFile(filePath, movies);
        System.out.println("> Сохранено фильмов: " + movies.size());
    }
    
    public String getCurrentFilePath() {
        return currentFilePath;
    }
}