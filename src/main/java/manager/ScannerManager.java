package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class ScannerManager {
    private Scanner consoleScanner;
    private Deque<Scanner> scannerStack = new ArrayDeque<>();
    private Scanner currentScanner;
    private boolean isFileMode;
    
    public ScannerManager() {
        this.consoleScanner = new Scanner(System.in);
        this.currentScanner = consoleScanner;
        this.isFileMode = false;
    }public void pushFileSource(String filePath) throws FileNotFoundException {
        scannerStack.push(currentScanner);            
        currentScanner = new Scanner(new File(filePath));
        isFileMode = true;
    }

    public void popSource() {
        if (!scannerStack.isEmpty()) {
            if (currentScanner != consoleScanner) {
                currentScanner.close();
            }
            currentScanner = scannerStack.pop();
            isFileMode = (currentScanner != consoleScanner);
        } else {
            setConsoleSource();
        }
    }

    public void setFileSource(String filePath) throws FileNotFoundException {
        scannerStack.clear();
        if (currentScanner != consoleScanner) {
            currentScanner.close();
        }
        currentScanner = new Scanner(new File(filePath));
        this.isFileMode = true;
    }
    
    public void setConsoleSource() {
        scannerStack.clear();
        if (currentScanner != consoleScanner) {
            currentScanner.close();
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
        while (!scannerStack.isEmpty()) {
            Scanner sc = scannerStack.pop();
            if (sc != consoleScanner) {
                sc.close();
            }
        }
        if (currentScanner != consoleScanner) {
            currentScanner.close();
        }
        consoleScanner.close();
    }
}