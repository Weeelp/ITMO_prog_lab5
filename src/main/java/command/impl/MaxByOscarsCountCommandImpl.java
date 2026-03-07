package command.impl;

import manager.CollectionManager;
import command.Command;
import model.movie.Movie;

public class MaxByOscarsCountCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public MaxByOscarsCountCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("> Коллекция пуста. Нет фильмов для анализа.");
            return;
        }

        Movie movie = collectionManager.getMaxByOscarsCount();

        System.out.println("> Фильм с максимальным количеством Оскаров:");
        System.out.println("  ID: " + movie.getId());
        System.out.println("  Название: " + movie.getName());
        System.out.println("  Количество Оскаров: " + movie.getOscarsCount());
        System.out.println("  Жанр: " + movie.getGenre());
        
        if (movie.getScreenWriter() != null) {
            System.out.println("  Сценарист: " + movie.getScreenWriter().getName());
        }
    }
}