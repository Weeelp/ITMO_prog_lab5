package manager;

import app.ConsoleApp;
import manager.CollectionManager;
import manager.FileManager;
import manager.ScannerManager;
import command.Command;
import command.impl.*;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands;
    
    public CommandManager(CollectionManager cm, FileManager fm, ScannerManager sm, ConsoleApp app) {
        commands = new HashMap<>();
        
        commands.put("add", new AddCommandImpl(cm, sm));
        commands.put("show", new ShowCommandImpl(cm));
        commands.put("info", new InfoCommandImpl(cm));
        commands.put("update", new UpdateFileCommandImpl(cm, fm));
        commands.put("update_by_id", new UpdateByIdCommandImpl(cm, sm));
        commands.put("remove_by_id", new RemovedByIdCommandImpl(cm));
        commands.put("remove_last", new RemoveLastCommandImpl(cm));
        commands.put("remove_greater", new RemoveGreaterCommandImpl(cm));
        commands.put("execute_script", new ExecuteScriptCommandImpl(app, sm));
        commands.put("clear", new ClearCommandImpl(cm));
        commands.put("save", new SaveCommandImpl(cm, fm));
        commands.put("max_by_oscars_count", new MaxByOscarsCountCommandImpl(cm));
        commands.put("print_ascending", new PrintAscendingCommandImpl(cm));
        commands.put("print_descending", new PrintDescendingCommandImpl(cm));
        commands.put("help", new HelpCommandImpl());
        commands.put("exit", new ExitCommandImpl());
    }
    
    public void executeCommand(String commandName, String[] args) {
        Command cmd = commands.get(commandName);
        if (cmd != null) {
            cmd.execute(args);
        } else {
            System.out.println("Неизвестная команда: " + commandName);
        }
    }
}