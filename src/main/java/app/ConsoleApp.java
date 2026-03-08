package app;

import exceptions.ExitException;
import manager.*;

public class ConsoleApp {
    private final String FILE_PATH = System.getenv("DATA_PATH");
    private final ScannerManager scannerManager;
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final CommandManager commandManager;
    private boolean isRunning = true;

    public ConsoleApp(ScannerManager scannerManager) throws Exception {
        this.scannerManager = scannerManager;
        
        if (FILE_PATH == null || FILE_PATH.trim().isEmpty()) {
            throw new Exception("ERROR: переменная окружения DATA_PATH не установлена.");
        }
        
        this.collectionManager = new CollectionManager();
        this.fileManager = new FileManager();
        
        try {
            collectionManager.setMovies(fileManager.loadFromFile(FILE_PATH));
            System.out.println("> Данные загружены из файла: " + FILE_PATH);
        } catch (Exception e) {
            System.out.println("> Предупреждение: не удалось загрузить данные из файла. Будет создана пустая коллекция.");
            System.out.println("> Причина: " + e.getMessage());
        }
        
        this.commandManager = new CommandManager(collectionManager, fileManager, scannerManager, this);
    }

    public void processCommand(String input) {
        if (input == null || input.trim().isEmpty()) return;

        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0].toLowerCase();

        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1);

        if (commandName.equals("exit")) {
            commandManager.executeCommand(commandName, args);
            isRunning = false;
            return;
        }

        commandManager.executeCommand(commandName, args);
    }

    public void start() {
        System.out.println("> Приложение запущено <");
        System.out.println("> Введите 'help' для списка команд");
        
        while (isRunning) {
            System.out.print("> ");
            String cmd = scannerManager.readLine();
            if (cmd == null) break;
            
            try {
                processCommand(cmd);
            } catch (ExitException e) {
                System.out.println("> " + e.getMessage());
                break;
            }
        }
        
        System.out.println("> Приложение завершено");
        scannerManager.close();
    }

    public static void main(String[] args) {
        try {
            ScannerManager scannerManager = new ScannerManager();
            new ConsoleApp(scannerManager).start();
        } catch (Exception e) {
            System.err.println("> Критическая ошибка при запуске приложения: " + e.getMessage());
            System.exit(1);
        }
    }
}