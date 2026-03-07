// package command.impl;

// import java.time.LocalDate;
// import java.util.Scanner;

// import model.movie.Genre;
// import model.movie.Coordinates;
// import model.person.Person;

// import app.Manager;

// public class AddIfMaxCommand implements Command {

//    private final Manager manager;

//     public AddIfMaxCommand(Manager manager) {
//         this.manager = manager;
//     }

//      @Override
//     public void execute(String[] args) {
//         Scanner sc = new Scanner(System.in);
        
//         try {
//             String name = readNonEmptyString(sc, ">> Введите имя фильма:", "имя");
            
//             LocalDate creationDate = LocalDate.now();
//             System.out.println(">> Дата создания установлена автоматически: " + creationDate);
            
//             int oscarsCount = readPositiveInt(sc, ">> Введите количество Оскаров:");
            
//             long x = readLong(sc, ">> Введите координату X:");
//             float y = readFloat(sc, ">> Введите координату Y:");
//             Coordinates coordinates = new Coordinates(x, y);
            
//             double totalBoxOffice = readPositiveDouble(sc, ">> Введите общий сбор:");
//             long usaBoxOffice = readPositiveLong(sc, ">> Введите сборы в США:");
            
//             Genre genre = readEnum(sc, 
//                 ">> Введите жанр (WESTERN, COMEDY, TRAGEDY, SCIENCE_FICTION):", 
//                 Genre.class);
            
//             Person screenWriter = readPerson(sc);
            
//             long id = manager.generateId();
//             Movie movie = new Movie(
//                 id,
//                 name,
//                 coordinates,
//                 creationDate,
//                 oscarsCount,
//                 totalBoxOffice,
//                 usaBoxOffice,
//                 genre,
//                 screenWriter
//             );
            

//             manager.add(movie);
//             System.out.println(">> Фильм успешно добавлен! ID: " + id);
            
//         } catch (Exception e) {
//             System.out.println(">> Ошибка при добавлении фильма: " + e.getMessage());
//         }
//     }
// }
