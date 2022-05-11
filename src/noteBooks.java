public class noteBooks<T> extends Note {
    String subjectArea;

    public noteBooks(String name, String text, User author, T type) {
        this.name = name;
        this.text = text;
        this.author = author;
        this.type = type;
    }



    public void setName (String name)
    {
        try{
            if (this.author.getRole() == Role.MODERATOR || this.author.getRole() == Role.ADMIN)
                this.name = name;
            else
                System.out.println("Изменение заметок невозможно!");
        }catch (Exception e){
            System.out.println("Изменение заметки у которой нет автора невозможно");
        }
    }

    public void replaceText(String text)
    {
        try{
        if (this.author.getRole().equals(Role.MODERATOR) || this.author.getRole().equals(Role.ADMIN))
            this.text = text;
        else
            System.out.println("Изменение заметок невозможно!");
        }catch (Exception e){
            System.out.println("Изменение заметки у которой нет автора невозможно");
        }
    }

    public void replaceWord(String text, String oldWord, String newWord)
    {
        try{

        if (this.author.getRole() == Role.MODERATOR || this.author.getRole() == Role.ADMIN)
        {
            for (String word : text.split(" "))
            {
                if  ( oldWord.equals(word) )
                    this.text = newWord + " ";
                else
                    this.text = word + " ";
            }
        }
        else
            System.out.println("Изменение заметок невозможно!");
    }catch (Exception e){
        System.out.println("Изменение заметки у которой нет автора невозможно");
    }
    }




    public String getAuthor(){
        return this.getAuthor() ;
    }

    @Override
    public String toString() {
        String str;
        try{
            str = "Заметка Книги{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author.getLogin() + '\'' +
                '}';
    }catch (NullPointerException npe)
        {
            str = "Заметка Книги{" +
                    "name='" + name + '\'' +
                    ", text='" + text + '\'' +
                    ", author='" + "Народ" + '\'' +
                    '}';
               }
        return str;
    }

    @Override
    public String name() {
        return "books";
    }

    @Override
    public int getIntType() {
        return 1;
    }


}
