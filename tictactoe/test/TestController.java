import org.junit.Test;
import Controller.Controller;
import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestController {

    @Test
    public void testController() {
        Controller controller = new Controller();

        //test piece placement
        assertEquals(true, controller.placePiece("X", 0, 0));
        //tests for same grid space placement
        assertEquals(false, controller.placePiece("X", 0, 0));
        //tests for new player move
        assertEquals(true, controller.placePiece("O", 0, 1));
        //tests for invalid player move
        assertEquals(false, controller.placePiece("O", 0, 2));
    }
}
