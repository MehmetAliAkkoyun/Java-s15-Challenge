import model.Library;
import ui.ConsoleUI;
import util.DataInitializer;

public class Main {
    public static void main(String[] args) {

        Library library = new Library(
                "Universite Merkez Kutuphanesi",
                "Universite Cad. No:1, Ankara");

        DataInitializer.populate(library);

        ConsoleUI ui = new ConsoleUI(library);
        ui.start();
    }
}
