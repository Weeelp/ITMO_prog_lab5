package command.impl;

import java.io.IOException;

import manager.CollectionManager;
import manager.FileManager;
import command.Command;

public class SaveCommandImpl implements Command {

    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public SaveCommandImpl(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (collectionManager.isEmpty()) {
                System.out.println("> Коллекция пуста. Нечего сохранять.");
                return;
            }
            collectionManager.sortByIdAscending();
            fileManager.saveToFile(collectionManager.getAll());
            System.out.println("> Коллекция успешно сохранена. Всего фильмов: " + collectionManager.size());
            
        } catch (IOException e) {
            System.out.println("> Ошибка сохранения файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("> Непредвиденная ошибка при сохранении: " + e.getMessage());
        }
    }
}