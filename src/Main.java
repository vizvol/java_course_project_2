import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User currentUser = null;
        boolean isExit = false;
        String choiceMenu;
        int selectMenu;
        List<User> userList = new ArrayList<>(Arrays.asList(
           new User("Alex", "111", Role.ADMIN),
           new User("Bob", "222", Role.MODERATOR),
           new User("Carl", "333", Role.USER)
        ));
        List<Note> noteList = new ArrayList<Note>();

        while(!isExit) {
            mainMenu();
            System.out.print("Выберите операцию: ");
            choiceMenu = (new Scanner(System.in)).nextLine() ;
            if (!isNumber(choiceMenu)) {
                System.out.println("Не удалось распознать операцию! Повторите выбор:");
                continue;
            }
            selectMenu = Integer.parseInt(choiceMenu);
            Note currentNote;
            switch (selectMenu) {
                case 1: {
                    if (currentUser == null) currentUser = loginUser(userList);
                    else System.out.println("Вы уже в системе под пользователем: " + currentUser.getLogin());
                    continue;
                }
                case 2:
                    currentUser = loginUser(userList);
                    continue;
                    //case 3 : noteList.add(new noteBooks("", text, author)); continue;
                case 3:
                    noteList.add(createNote(currentUser));
                    System.out.println("Заметка создана! ");
                    continue;
                case 4: {
                    boolean isExit2 = false;
                    String choiceMenu2;
                    int selectMenu2;
                    String name;
                    System.out.print("Введите название заметки для поиска: ");
                    name = (new Scanner(System.in)).nextLine();
                    currentNote = searchNote(noteList, name);
                    if ( currentNote == null ) {
                        System.out.println("Заметка не найдена!");
                        continue;
                    }
                    else
                        System.out.println("Заметка найдена!");

                    while(!isExit2) {
                        noteMenu();
                        System.out.print("Выберите операцию: ");
                        choiceMenu2 = (new Scanner(System.in)).nextLine();
                        if (!isNumber(choiceMenu2)) {
                            System.out.println("Не удалось распознать операцию! Повторите выбор:");
                            continue;
                        }
                        selectMenu2 = Integer.parseInt(choiceMenu2);
                        switch (selectMenu2) {
                            case 1 :
                                System.out.println(currentNote);
                                continue;
                            case 2 :
                                System.out.println("Введите новое название заметки");
                                noteList.remove(currentNote);
                                currentNote.setName((new Scanner(System.in)).nextLine());
                                noteList.add(currentNote);
                                break;
                            case 3 :
                                System.out.println("Введите слово которое нужно заменить в заметке: ");
                                String oldWord = (new Scanner(System.in)).nextLine();
                                System.out.println("Введите новое слово: ");
                                String newWord = (new Scanner(System.in)).nextLine();
                                noteList.remove(currentNote);
                                currentNote.replaceWord(currentNote.text, oldWord, newWord);
                                noteList.add(currentNote);
                                break;
                            case 4 :
                                System.out.println("Введите новое тело заметки");
                                noteList.remove(currentNote);
                                currentNote.replaceText ((new Scanner(System.in)).nextLine());
                                noteList.add(currentNote);
                                break;
                            case 5 :
                                System.out.println((currentNote.author==null) ? "У заметки нет автора" : "Автор заметки: " + currentNote.author.getLogin() );
                                continue;
                            case 6 :
                                noteList.remove(currentNote);
                                System.out.println("Заметка удалена!");
                            case 7 : isExit2 = true;
                        }
                    }
                }
                    continue;

                case 5 : isExit = true;
                    System.out.println("До свидания!");
                    continue;
            }
        }
    }
    public static Note createNote (User author){
        boolean isExit = false;
        String name;
        String text;
        String typeString = "";
        int typeInt;
        System.out.println("Введите название для новой заметки: ");
        name = (new Scanner(System.in)).nextLine();
        System.out.println("Введите текст заметки: ");
        text = (new Scanner(System.in)).nextLine();
        while(!isExit) {
            System.out.println("Выберите тип заметки(принадлежность к классу):  \n1. Книги\n2. Покупки\n3. Дела ");
            typeString = (new Scanner(System.in)).nextLine();
            if (!isNumber(typeString)) {
                System.out.println("Не удалось распознать операцию! Повторите выбор:");
                continue;
            }
            isExit = true;
        }
        typeInt = Integer.parseInt(typeString);
        switch (typeInt) {
            case 1: return new noteBooks(name, text, author, "Book");
            case 2: return new noteBuy(name, text, author, 1000);
            case 3: return new noteMake(name, text, author, Role.USER);
        }
        return null;
    }

    public static void searchMenu(List<Note> noteList){

    }

    public static Note searchNote (List<Note> noteList, String name)
    {
        for (Note note : noteList) {
            if ( name.equals(note.name) )
            return note;
        }
        return null;
    }

    public static List<Note> searchNote (List<Note> noteList, int type)
    {
        List<Note> notes =new ArrayList<>();
        for (Note note : noteList) {
            if (type == note.getIntType())
                notes.add(note);
            break;
        }
        return notes;
    }


    public static User loginUser (List<User> userList){
        String login;
        String password;
        System.out.println("Введите логин пользователя: ");
        login = (new Scanner(System.in)).nextLine();
        System.out.println("Введите пароль пользователя: ");
        password = (new Scanner(System.in)).nextLine();
        for (User user : userList) {
            if ( user.login.equals(login) && user.password.equals(password) ) {
                System.out.println("Вы вошли в систему под пользователем: " + user.getLogin());
                return new User(user.getLogin(), user.getPassword(), user.getRole());
            }

        }
        System.out.println("Логин или пароль пользователя неверны!");
        return null;
    }

    public static void mainMenu() {
        System.out.println( "1. Вход под именем пользователя. (логин + пароль)\n" +
                            "2. Выполнить вход под другим пользователем \n" +
                            "3. Создание новой заметки (Название (Строка) – тело (Строка) – тип (принадлежность к классу))  \n" +
                            "4. Поиск заметки по названию \n" +
                            "5. Выход из приложения");

    }
    public static void noteMenu() {
        System.out.println( "1. Вывести заметку в консоль.\n" +
                            "2. Изменения названия заметки (проверка на роль пользователя) \n" +
                            "3. Замена слова в заметке (Реализовать при помощи цикла и метода equals) (проверка на роль пользователя) \n" +
                            "4. Замена тела заметки (проверка на роль пользователя) \n" +
                            "5. Вывести автора заметки \n" +
                            "6. Удалить заметку \n" +
                            "7. Выйти из меню работы с заметками");
    }

    // метод распознавания числа из строки
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
