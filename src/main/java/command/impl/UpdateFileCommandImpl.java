package command.impl;

import manager.CollectionManager;
import manager.FileManager;
import command.Command;

public class UpdateFileCommandImpl implements Command {

    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public UpdateFileCommandImpl(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            collectionManager.setMovies(fileManager.loadFromFile(fileManager.getCurrentFilePath()));
            System.out.println("> Файл обновлен успешно.");
        } catch (Exception e) {
            System.out.println("> Ошибка загрузки файла!");
        }
    }
}