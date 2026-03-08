package command.impl;

import manager.CollectionManager;
import command.Command;

public class RemoveLastCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public RemoveLastCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("> Коллекция пуста. Нечего удалять.");
            return;
        }
        
        boolean removed = collectionManager.removeLast();
        
        if (removed) {
            System.out.println("> Последний фильм успешно удалён.");

        } else {
            System.out.println("> Не удалось удалить последний фильм.");
        }
    }
}