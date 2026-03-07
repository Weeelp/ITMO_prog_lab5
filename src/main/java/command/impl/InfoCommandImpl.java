package command.impl;

import java.util.LinkedList;

import manager.CollectionManager;
import command.Command;
import model.movie.Movie;

public class InfoCommandImpl implements Command {

    private final CollectionManager collectionManager;  // с маленькой буквы

    public InfoCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {

        LinkedList<Movie> movies = collectionManager.getAll();

        System.out.println("> Информация о коллекции:");
        System.out.println("  Дата инициализации: " + collectionManager.getInitializationDate());
        System.out.println("  Тип коллекции: " + movies.getClass().getSimpleName());
        System.out.println("  Количество элементов: " + movies.size());
        
        if (movies.isEmpty()) {
            System.out.println("  Коллекция пуста.");
        } else {
            System.out.println("  Минимальный ID: " + movies.getFirst().getId());
            System.out.println("  Максимальный ID: " + movies.getLast().getId());
        }
    }
}