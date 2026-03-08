package command.impl;

import manager.CollectionManager;
import manager.ScannerManager;
import command.Command;
import utils.MovieInputReader;
import utils.MovieInputReader.MovieData;
import model.movie.Movie;

public class UpdateByIdCommandImpl implements Command {
    private final CollectionManager collectionManager;
    private final MovieInputReader inputReader;
    
    public UpdateByIdCommandImpl(CollectionManager collectionManager, ScannerManager scannerManager) {
        this.collectionManager = collectionManager;
        this.inputReader = new MovieInputReader(scannerManager);
    }
    
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("> Укажите id фильма");
            return;
        }
        
        long id;
        try {
            id = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("> id должен быть числом");
            return;
        }
        
        Movie movie = collectionManager.findById(id);
        if (movie == null) {
            System.out.println("> Фильм с id " + id + " не найден");
            return;
        }
        
        try {
            System.out.println("> Редактирование фильма ID " + id);
            System.out.println("> Текущее название: " + movie.getName());
            
            MovieData data = inputReader.readMovieData();
            
            movie.setName(data.name);
            movie.setCoordinates(data.coordinates);
            movie.setCreationDate(data.creationDate);
            movie.setOscarsCount(data.oscarsCount);
            movie.setTotalBoxOffice(data.totalBoxOffice);
            movie.setUsaBoxOffice(data.usaBoxOffice);
            movie.setGenre(data.genre);
            movie.setScreenWriter(data.screenWriter);
            
            System.out.println(">> Фильм с ID " + id + " успешно обновлён!");
            
        } catch (Exception e) {
            System.out.println(">> Непредвиденная ошибка: " + e.getMessage());
        }
    }
}