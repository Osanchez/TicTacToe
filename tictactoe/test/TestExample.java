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
    public void testModel() {
        Model model = new Model();
    }

    @Test
    public void testView() {
        View view = new View();
    }

    @Test
    public void testController() {
        Controller controller = new Controller();
    }

}
