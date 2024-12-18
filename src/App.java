import controller.Controller;
import model.School;
import view.View;

public class App {
    
    public static void main(String[] args) throws Exception {

        School school = new School();
        View view = new View();

        Controller main = new Controller(school, view);

        main.start();


    }

}
