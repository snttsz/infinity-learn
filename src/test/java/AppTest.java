import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.SQLiteTableManager;
import model.DAO.UsuarioDAO;
import model.system.Usuario;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() 
    {
        // System.out.println("Hello world!");
        SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        // assertTrue(true);
    }
}