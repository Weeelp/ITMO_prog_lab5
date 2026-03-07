package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerManager {
    private Scanner consoleScanner;
    private Scanner fileScanner;
    private Scanner currentScanner;
    private boolean isFileMode;

    public ScannerManager() {
        this.consoleScanner = new Scanner(System.in);
        this.currentScanner = consoleScanner;
        this.isFileMode = false;
    }

    public void setFileSource(String filePath) throws FileNotFoundException {
        this.fileScanner = new Scanner(new File(filePath));
        this.currentScanner = fileScanner;
        this.isFileMode = true;
    }

    public void setConsoleSource() {
        if (fileScanner != null) {
            fileScanner.close();
            fileScanner = null;
        }
        this.currentScanner = consoleScanner;
        this.isFileMode = false;
    }

    public String readLine() {
        return currentScanner.nextLine();
    }

    public boolean hasNextLine() {
        return currentScanner.hasNextLine();
    }

    public boolean isFileMode() {
        return isFileMode;
    }

    public void close() {
        if (fileScanner != null) {
            fileScanner.close();
        }
        consoleScanner.close();
    }
}