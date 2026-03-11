package command.impl;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import app.ConsoleApp; 
import command.Command;
import manager.ScannerManager;

public class ExecuteScriptCommandImpl implements Command {

    private final ConsoleApp invoker;
    private final ScannerManager scannerManager;
    private static Set<String> executingScripts = new HashSet<>();

    public ExecuteScriptCommandImpl(ConsoleApp invoker, ScannerManager scannerManager) {
        this.invoker = invoker;
        this.scannerManager = scannerManager;
    }

    @Override
    public void execute(String[] args) {

        if (args.length == 0) {
            System.out.println("> Укажите имя файла.");
            return;
        }

        if (args.length > 1) {
            System.out.println("> Слишком много аргументов, неверный ввод");
            return;
        } 

        String filename = args[0];
        
        if (executingScripts.contains(filename)) {
            System.out.println("> Ошибка: рекурсивный вызов скрипта '" + filename + "' запрещен!");
            System.out.println("> Скрипт уже выполняется.");
            return;
        }
        
        try {
            executingScripts.add(filename);
            
            scannerManager.pushFileSource(filename);
            
            System.out.println("> Выполнение скрипта: " + filename);
            int lineCount = 0;
            
            while (scannerManager.hasNextLine()) {
                String line = scannerManager.readLine().trim();
                
                if (line.isEmpty()) continue;
                
                lineCount++;
                System.out.println("> [" + lineCount + "] executing: " + line);
                
                invoker.processCommand(line);
            }
            
            System.out.println("> Скрипт выполнен: " + filename + " (выполнено строк: " + lineCount + ")");
            
        } catch (FileNotFoundException e) {
            System.out.println("> Файл не найден: " + filename);
        } finally {
            executingScripts.remove(filename);
            scannerManager.popSource();
        }
    }
}