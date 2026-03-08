package command.impl;

import manager.CollectionManager;
import manager.ScannerManager;
import command.Command;
import exceptions.*;
import utils.MovieInputReader;
import utils.MovieInputReader.MovieData;
import model.movie.Movie;

public class AddIfMaxCommandImpl implements Command {
    private final CollectionManager collectionManager;
    private final MovieInputReader inputReader;

    public AddIfMaxCommandImpl(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.inputReader = new MovieInputReader(scannerManager);
    }

    @Override
    public void execute(String[] args) {
        Movie maxMovie = collectionManager.getMaxByOscarsCount();
        int maxOscars = (maxMovie == null) ? 0 : maxMovie.getOscarsCount();
        System.out.println(">> Текущее максимальное количество Оскаров в коллекции: " + maxOscars);

        try {
            MovieData data = inputReader.readMovieData();

            if (data.oscarsCount > maxOscars) {
                Movie movie = new Movie(
                        collectionManager.generateId(),
                        data.name,
                        data.coordinates,
                        data.creationDate,
                        data.oscarsCount,
                        data.totalBoxOffice,
                        data.usaBoxOffice,
                        data.genre,
                        data.screenWriter
                );
                collectionManager.add(movie);
                System.out.println(">> Фильм успешно добавлен! ID: " + movie.getId());
            } else {
                System.out.println(">> Фильм НЕ добавлен: количество Оскаров (" + data.oscarsCount +
                        ") не превышает текущий максимум (" + maxOscars + ").");
            }
         } catch (StopInputException e) {
            System.out.println(">> Операция прервана пользователем.");
        } catch (ValidationException e) {
            System.out.println(">> Ошибка в данных: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Непредвиденная ошибка: " + e.getMessage());
        }
    }
}