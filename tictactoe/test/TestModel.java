import org.junit.Test;
import Model.Model;
import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestModel {

    @Test
    public void testModel() {
        Model model = new Model();

        //test for initial state
        model.initializeBoard();

        assertEquals("", model.getWinner());
        assertEquals(false, model.getWinState());
        assertEquals("X", model.getPlayerTurn());
        assertEquals(9, model.getTurnsLeft());
    }
}
