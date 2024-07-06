package spring;

import org.springframework.context.annotation.ComponentScan;

import model.SQLiteConnectionManager;
import model.SQLiteTableManager;
import model.DAO.UsuarioDAO;
import model.system.Usuario;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.controller", "spring.config"})
public class Application 
{
	public static void main(String[] args) 
	{
		//SpringApplication.run(Application.class, args);

		SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();
		
		
		UsuarioDAO ud = new UsuarioDAO();

		ud.delete(1);

		ArrayList<Usuario> array = ud.selectAll();

		for(Usuario u : array)
		{
			Usuario.printarUsuario(u);
		}

	}
}