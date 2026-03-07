package  command.impl;

import command.Command;

public class HelpCommandImpl implements Command {

    @Override
    public void execute (String[] args){
        System.out.println("Справка по командам:");
        System.out.println("help - показать справку");
        System.out.println("exit - выход из программы");
        System.out.println("info - информация о коллекции тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} - добавить новый элемент в коллекцию");
        System.out.println("update - обновить файл");
        System.out.println("update_by_id {id} - обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id {id} - удалить элемент из коллекции по его id");
        System.out.println("clear - очистить коллекцию");
        System.out.println("save - сохранить коллекцию в файл");
        System.out.println("execute_script {file_name} - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("remove_last - удалить последний элемент из коллекции");
        System.out.println("add_if_max {element} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("remove_greater {element} - удалить из коллекции все элементы, превышающие заданный");
        System.out.println("max_by_oscars_count - вывести любой объект из коллекции, значение поля oscarsCount которого является максимальным");
        System.out.println("print_ascending - вывести элементы коллекции в порядке возрастания");
        System.out.println("print_descending - вывести элементы коллекции в порядке убывания");
    }
} 