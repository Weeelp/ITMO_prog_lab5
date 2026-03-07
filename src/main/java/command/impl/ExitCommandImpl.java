package command.impl;

import command.Command;

public class ExitCommandImpl implements Command{

    @Override
    public void execute (String[] args){
        System.out.println("Программа завершена");
    }
    
}