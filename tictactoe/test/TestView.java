import org.junit.Test;
import static org.junit.Assert.*;
import View.View;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestView {

    @Test
    public void testView() {
        View view = new View();
        view.createBoard();
        //view.gui.setVisible(true);
    }
}
