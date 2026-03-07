package command.impl;

import manager.CollectionManager;
import command.Command;

public class ClearCommandImpl implements Command {

    private final CollectionManager collectionManager;

    public ClearCommandImpl(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.clear();
        System.out.println("> Коллекция очищена.");
    }
}