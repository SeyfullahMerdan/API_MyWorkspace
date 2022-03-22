package pojos;

public class TodosPojo {


    // 1- Degişkenleri privat yapıyoruz.

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // 2- Bu degerlere ulaşabilmek için public getter ve setter methodlar oluşturalım


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    // 3- Bir tane parametreli, bir tane de parametresiz constructor oluşturacağım


    public TodosPojo() {

    }


    public TodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }



   // 4- ToString methodu oluşturacağız.


    @Override
    public String toString() {
        return "TodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }




    // Yapacağımız işlemler sadece bunlardır. Bu class sayesinde istedigim değerleri girip
    // üzerinde çalışabilirim...



    /*
                              OOP Consept burada devreye giriyor
    Encapsulation ile datalarımızı gizliyoruz burada
    Inheritance testBase classlarına extends yaparak Parentteki spec objelerini kulladımç
    Abstraction  -- Oluşturdugum classdaki methodların alt classlarda kullanılmasını istiyorsam
               -- yaparım...
    polymorpysim  -- Driver=new ChromeDriver gibi
                     list =new ArrayList gibi....

     */







}
