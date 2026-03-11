package command.impl;

import manager.CollectionManager;
import command.Command;

public class RemovedByIdCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public RemovedByIdCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("> Не указано id фильма!");
            return;
        }

        if (args.length > 1) {
            System.out.println("> Слишком много аргументов, неверный ввод");
            return;
        } 
 
        long id;

        try {
            id = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("> Ошибка: id должен быть целым числом.");
            return;
        }

        if (collectionManager.findById(id) == null) {
            System.out.println("> Фильм с id " + id + " не найден.");
            return;
        }

        boolean removed = collectionManager.remove(id);

        if (removed) {
            System.out.println("> Фильм с id " + id + " успешно удалён.");
            System.out.println("> В коллекции осталось фильмов: " + collectionManager.size());
        } else {
            System.out.println("> Не удалось удалить фильм с id " + id + ".");
        }
    }
}