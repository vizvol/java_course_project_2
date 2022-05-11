public class noteMake<T> extends Note {
    int priority;

    public noteMake(String name, String text, User author, T type) {
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


    @Override
    public String name() {
        return "make";
    }



    public String getAuthor(){
        return this.getAuthor() ;
    }

    @Override
    public String toString() {
        return "Заметка Список дел{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public int getIntType() {
        return 3;
    }
}
