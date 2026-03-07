package command.impl;

import java.util.LinkedList;

import manager.CollectionManager;
import command.Command;
import model.movie.Movie;

public class ShowCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public ShowCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("> Коллекция пуста.");
            return;
        }

        LinkedList<Movie> movies = collectionManager.getAll();

        System.out.println("> Содержимое коллекции (всего фильмов: " + movies.size() + "):");
        System.out.println("  " + "-".repeat(60));
        
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.println("  " + (i + 1) + ". " + movie);
        }
        
        System.out.println("  " + "-".repeat(60));
    }
}