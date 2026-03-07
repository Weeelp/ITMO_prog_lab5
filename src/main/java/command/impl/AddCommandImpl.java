package command.impl;

import manager.CollectionManager;
import manager.ScannerManager;
import command.BaseUploadCommand;

import java.time.LocalDate;

import model.movie.*;
import model.person.Person;

public class AddCommandImpl extends BaseUploadCommand {
    private final CollectionManager collectionManager;
    private final ScannerManager sc;
    
    public AddCommandImpl(CollectionManager collectionManager, ScannerManager sc) {
        super(collectionManager);
        this.collectionManager = collectionManager;
        this.sc = sc;
    }

    @Override
    public void execute(String[] args) {
        
        try {
            String name = readNonEmptyString(sc, ">> Введите имя фильма:", "имя");
            
            LocalDate creationDate = LocalDate.now();
            System.out.println(">> Дата создания установлена автоматически: " + creationDate);
            
            int oscarsCount = readPositiveInt(sc, ">> Введите количество Оскаров:");
            
            long x = readLong(sc, ">> Введите координату X:");
            float y = readFloat(sc, ">> Введите координату Y:");
            Coordinates coordinates = new Coordinates(x, y);
            
            double totalBoxOffice = readPositiveDouble(sc, ">> Введите общий сбор:");
            long usaBoxOffice = readPositiveLong(sc, ">> Введите сборы в США:");
            
            Genre genre = readEnum(sc, 
                ">> Введите жанр (WESTERN, COMEDY, TRAGEDY, SCIENCE_FICTION):", 
                Genre.class);
            
            Person screenWriter = readPerson(sc);
            
            long id = collectionManager.generateId();
            Movie movie = new Movie(
                id,
                name,
                coordinates,
                creationDate,
                oscarsCount,
                totalBoxOffice,
                usaBoxOffice,
                genre,
                screenWriter
            );
            
            collectionManager.add(movie);
            System.out.println(">> Фильм успешно добавлен! ID: " + id);
            System.out.println(">> Всего фильмов в коллекции: " + collectionManager.size());
            
        } catch (IllegalArgumentException e) {
            System.out.println(">> Ошибка в данных: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Непредвиденная ошибка при добавлении фильма: " + e.getMessage());
        }
    }
}
