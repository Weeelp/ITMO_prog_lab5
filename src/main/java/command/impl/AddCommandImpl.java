package command.impl;

import manager.CollectionManager;
import manager.ScannerManager;
import command.Command;
import utils.MovieInputReader;
import utils.MovieInputReader.MovieData;
import model.movie.Movie;

public class AddCommandImpl implements Command {
    private final CollectionManager collectionManager;
    private final MovieInputReader inputReader;
    
    public AddCommandImpl(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.inputReader = new MovieInputReader(scannerManager);
    }
    
    @Override
    public void execute(String[] args) {
        try {
            MovieData data = inputReader.readMovieData();
            
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
            
        } catch (Exception e) {
            System.out.println(">> Непредвиденная ошибка: " + e.getMessage());
        }
    }
}