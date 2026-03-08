package utils;

import manager.ScannerManager;
import model.movie.*;
import model.person.*;
import validator.MovieValidator;
import exceptions.ValidationException;

import java.time.LocalDate;

public class MovieInputReader {
    private final ScannerManager scannerManager;
    
    public MovieInputReader(ScannerManager scannerManager) {
        this.scannerManager = scannerManager;
    }
    
    public MovieData readMovieData() { 
        String name = readName();
        Coordinates coordinates = readCoordinates();
        int oscarsCount = readOscarsCount();
        double totalBoxOffice = readTotalBoxOffice();
        long usaBoxOffice = readUsaBoxOffice();
        Genre genre = readGenre();
        Person screenWriter = readPerson();
        LocalDate creationDate = LocalDate.now();
        
        return new MovieData(name, coordinates, creationDate, oscarsCount,
                totalBoxOffice, usaBoxOffice, genre, screenWriter);
    }
    
    private String readName() {
        while (true) {
            System.out.println(">> Введите имя фильма:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateName(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private Coordinates readCoordinates() {
        long x = readX();
        float y = readY();
        return new Coordinates(x, y);
    }
    
    private long readX() {
        while (true) {
            System.out.println(">> Введите координату X:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateX(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private float readY() {
        while (true) {
            System.out.println(">> Введите координату Y:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateY(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private int readOscarsCount() {
        while (true) {
            System.out.println(">> Введите количество Оскаров:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateOscarsCount(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private double readTotalBoxOffice() {
        while (true) {
            System.out.println(">> Введите общий сбор:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateTotalBoxOffice(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private long readUsaBoxOffice() {
        while (true) {
            System.out.println(">> Введите сборы в США:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateUsaBoxOffice(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private Genre readGenre() {
        while (true) {
            System.out.println(">> Введите жанр (WESTERN, COMEDY, TRAGEDY, SCIENCE_FICTION):");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateEnum(input, Genre.class,
                        "WESTERN, COMEDY, TRAGEDY, SCIENCE_FICTION");
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private Person readPerson() {
        String name = readPersonName();
        int height = readPersonHeight();
        EyeColor eyeColor = readEyeColor();
        HairColor hairColor = readHairColor();
        Country nationality = readCountry();
        return new Person(name, height, eyeColor, hairColor, nationality);
    }
    
    private String readPersonName() {
        while (true) {
            System.out.println(">> Введите имя сценариста:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validatePersonName(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private int readPersonHeight() {
        while (true) {
            System.out.println(">> Введите рост сценариста:");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validatePersonHeight(input);
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private EyeColor readEyeColor() {
        while (true) {
            System.out.println(">> Введите цвет глаз (BLACK, BLUE, WHITE, BROWN):");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateEnum(input, EyeColor.class,
                        "BLACK, BLUE, WHITE, BROWN");
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private HairColor readHairColor() {
        while (true) {
            System.out.println(">> Введите цвет волос (GREEN, RED, ORANGE, WHITE):");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateEnum(input, HairColor.class,
                        "GREEN, RED, ORANGE, WHITE");
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    private Country readCountry() {
        while (true) {
            System.out.println(">> Введите национальность (RUSSIA, UNITED_KINGDOM, GERMANY, ITALY, JAPAN, AMERICA):");
            String input = scannerManager.readLine().trim();
            try {
                return MovieValidator.validateEnum(input, Country.class,
                        "RUSSIA, UNITED_KINGDOM, GERMANY, ITALY, JAPAN, AMERICA");
            } catch (ValidationException e) {
                System.out.println(">> Ошибка: " + e.getMessage());
            }
        }
    }
    
    public static class MovieData {
        public final String name;
        public final Coordinates coordinates;
        public final LocalDate creationDate;
        public final int oscarsCount;
        public final double totalBoxOffice;
        public final long usaBoxOffice;
        public final Genre genre;
        public final Person screenWriter;
        
        public MovieData(String name, Coordinates coordinates, LocalDate creationDate,
                         int oscarsCount, double totalBoxOffice, long usaBoxOffice,
                         Genre genre, Person screenWriter) {
            this.name = name;
            this.coordinates = coordinates;
            this.creationDate = creationDate;
            this.oscarsCount = oscarsCount;
            this.totalBoxOffice = totalBoxOffice;
            this.usaBoxOffice = usaBoxOffice;
            this.genre = genre;
            this.screenWriter = screenWriter;
        }
    }
}