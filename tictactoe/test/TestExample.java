import org.junit.Test;
import Model.Model;
import View.View;
import Controller.Controller;
import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    @Test
    public void testNewGame() {
        View game = new View();
        assertEquals (1, game.player);
        assertEquals (9, game.movesLeft);
    }

    @Test
    public void testModel() {
        Model model = new Model();
    }

    @Test
    public void testController() {
        Controller controller = new Controller();
    }

    @Test
    public void testView() {
        View view = new View();
    }
}