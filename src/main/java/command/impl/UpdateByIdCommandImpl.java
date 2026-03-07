package command.impl;

import java.time.LocalDate;
import manager.ScannerManager;
import manager.CollectionManager;
import command.BaseUploadCommand;
import model.movie.Coordinates;
import model.movie.Genre;
import model.movie.Movie;
import model.person.Person;

public class UpdateByIdCommandImpl extends BaseUploadCommand {

    private final ScannerManager scannerManager;
    private final CollectionManager collectionManager;

    public UpdateByIdCommandImpl(CollectionManager collectionManager, ScannerManager scannerManager) {
        super(collectionManager);
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("> Не указано id фильма!");
            return;
        }

        long id;
        try {
            id = Long.parseLong(args[0]);
            if (id <= 0) {
                System.out.println("> Ошибка: id должен быть положительным числом.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("> Ошибка: id должен быть целым числом.");
            return;
        }

        Movie movie = collectionManager.findById(id);
        if (movie == null) {
            System.out.println("> Фильм с id " + id + " не найден.");
            return;
        }
        
        System.out.println("> Редактирование фильма с ID: " + id);
        System.out.println("> Текущее название: " + movie.getName());
        System.out.println("> (Для отмены введите 'stop' в любом поле)");
        
        try {
            String name = readNonEmptyString(scannerManager, ">> Введите новое имя фильма:", "имя");
            movie.setName(name);
            
            LocalDate creationDate = LocalDate.now();
            movie.setCreationDate(creationDate);
            System.out.println(">> Дата создания обновлена автоматически: " + creationDate);
            
            int oscarsCount = readPositiveInt(scannerManager, ">> Введите новое количество Оскаров:");
            movie.setOscarsCount(oscarsCount);
            System.out.println(">> Введите новые координаты:");
            long x = readLong(scannerManager, ">> X:");
            float y = readFloat(scannerManager, ">> Y:");
            movie.setCoordinates(new Coordinates(x, y));
            
            double totalBoxOffice = readPositiveDouble(scannerManager, ">> Введите новый общий сбор:");
            movie.setTotalBoxOffice(totalBoxOffice);
            
            long usaBoxOffice = readPositiveLong(scannerManager, ">> Введите новые сборы в США:");
            movie.setUsaBoxOffice(usaBoxOffice);
            
            Genre genre = readEnum(scannerManager, 
                ">> Введите новый жанр (WESTERN, COMEDY, TRAGEDY, SCIENCE_FICTION):", 
                Genre.class);
            movie.setGenre(genre);
            
            Person screenWriter = readPerson(scannerManager);
            movie.setScreenWriter(screenWriter);
            
            System.out.println(">> Фильм с ID " + id + " успешно обновлён!");
            System.out.println(">> Название: " + movie.getName());
            System.out.println(">> Жанр: " + movie.getGenre() + ", Оскаров: " + movie.getOscarsCount());
            
        } catch (Exception e) {
            System.out.println(">> Ошибка при обновлении фильма: " + e.getMessage());
        }
    }
    
}