package command;

import manager.CollectionManager;
import manager.ScannerManager;
import model.person.*;

public abstract class BaseUploadCommand implements Command {
    protected final CollectionManager collectionManager;
    
    public BaseUploadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    
    protected String readNonEmptyString(ScannerManager scannerManager, String prompt, String fieldName) {
        while (true) {
            System.out.println(prompt);
            String input = scannerManager.readLine().trim();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена.");
                System.exit(0);
            }
            
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println(">> Ошибка: " + fieldName + " не может быть пустым.");
        }
    }
    
    protected long readLong(ScannerManager scannerManager, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scannerManager.readLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Программа завершена.");
                    System.exit(0);
                }
                
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Ошибка: введите целое число.");
            }
        }
    }
    
    protected float readFloat(ScannerManager scannerManager, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scannerManager.readLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Программа завершена.");
                    System.exit(0);
                }
                
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Ошибка: введите число (можно дробное).");
            }
        }
    }
    
    protected int readPositiveInt(ScannerManager scannerManager, String prompt) {
        while (true) {
            int value = (int) readLong(scannerManager, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println(">> Ошибка: число должно быть положительным.");
        }
    }
    
    protected double readPositiveDouble(ScannerManager scannerManager, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scannerManager.readLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Программа завершена.");
                    System.exit(0);
                }
                
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.println(">> Ошибка: число должно быть положительным.");
            } catch (NumberFormatException e) {
                System.out.println(">> Ошибка: введите число.");
            }
        }
    }
    
    protected long readPositiveLong(ScannerManager scannerManager, String prompt) {
        while (true) {
            long value = readLong(scannerManager, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println(">> Ошибка: число должно быть положительным.");
        }
    }
    
    protected <T extends Enum<T>> T readEnum(ScannerManager scannerManager, String prompt, Class<T> enumClass) {
        while (true) {
            System.out.println(prompt);
            String input = scannerManager.readLine().trim().toUpperCase();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена.");
                System.exit(0);
            }
            
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println(">> Ошибка: введите одно из допустимых значений.");
                // Показываем допустимые значения
                T[] constants = enumClass.getEnumConstants();
                StringBuilder sb = new StringBuilder(">> Допустимые значения: ");
                for (int i = 0; i < constants.length; i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(constants[i]);
                }
                System.out.println(sb.toString());
            }
        }
    }
    
    protected Person readPerson(ScannerManager scannerManager) {
        System.out.println(">> Введите данные сценариста:");
        
        String name = readNonEmptyString(scannerManager, ">> Имя сценариста:", "имя");
        int height = readPositiveInt(scannerManager, ">> Рост сценариста:");
        
        EyeColor eyeColor = readEnum(scannerManager, 
            ">> Цвет глаз (BLACK, BLUE, WHITE, BROWN):", 
            EyeColor.class);
            
        HairColor hairColor = readEnum(scannerManager, 
            ">> Цвет волос (GREEN, RED, ORANGE, WHITE):", 
            HairColor.class);
            
        Country nationality = readEnum(scannerManager, 
            ">> Национальность (RUSSIA, UNITED_KINGDOM, GERMANY, ITALY, JAPAN, AMERICA):", 
            Country.class);
        
        return new Person(name, height, eyeColor, hairColor, nationality);
    }
}