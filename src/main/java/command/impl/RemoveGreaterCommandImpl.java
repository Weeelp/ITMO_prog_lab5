package command.impl;

import manager.CollectionManager;
import command.Command;

public class RemoveGreaterCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public RemoveGreaterCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("> Коллекция пуста. Нечего удалять.");
            return;
        }

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
            if (id <= 0) {
                System.out.println("> Ошибка: id должен быть положительным числом.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("> Ошибка: id должен быть целым числом.");
            return;
        }

        int oldSize = collectionManager.size();
        boolean removed = collectionManager.removeGreaterThan(id);
        
        int newSize = collectionManager.size();
        int removedCount = oldSize - newSize;

        if (removed && removedCount > 0) {
            System.out.println("> Удалено фильмов с id больше " + id + ": " + removedCount);
            System.out.println("> В коллекции осталось фильмов: " + newSize);
        } else {
            System.out.println("> Фильмов с id больше " + id + " не найдено.");
        }
    }
}