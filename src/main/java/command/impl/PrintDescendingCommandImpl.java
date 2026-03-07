package command.impl;

import java.util.LinkedList;

import manager.CollectionManager;
import command.Command;
import model.movie.Movie;

public class PrintDescendingCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public PrintDescendingCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("> Коллекция пуста. Нечего выводить.");
            return;
        }

        collectionManager.sortByIdDescending();

        LinkedList<Movie> movies = collectionManager.getAll();

        System.out.println("> Фильмы в порядке убывания ID:");
        System.out.println("  Всего фильмов: " + movies.size());
        System.out.println("  " + "-".repeat(50));
        
        int count = 1;
        for (Movie movie : movies) {
            System.out.println("  " + count + ". " + movie);
            count++;
        }
        
        System.out.println("  " + "-".repeat(50));
    }
}