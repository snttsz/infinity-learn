package spring;

import org.springframework.context.annotation.ComponentScan;

import model.SQLiteConnectionManager;
import model.SQLiteTableManager;
import model.DAO.QuestaoDAO;
import model.DAO.TarefaDAO;
import model.DAO.UsuarioDAO;
import model.system.Questao;
import model.system.Tarefa;
import model.system.Usuario;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.controller", "spring.config"})
public class Application 
{
	public static void main(String[] args) 
	{
		//SpringApplication.run(Application.class, args);

		SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();
		
		
		Usuario user = new Usuario(" kalvin","123", "kalvin@","kalvin", "kalvin/","aluno");
		UsuarioDAO ud = new UsuarioDAO();

		System.out.println("Usuário antes do update:");
		Usuario.printarUsuario(user);

		user.setNome("Glenda");
		user.setSenha("gcrs");
		user.setEmail("glenda123@");
		user.setApelido("glendinha");
		user.setLinkFoto("glenda/home/");
		user.setTipo("otaria");

		ud.update(user);
		System.out.println("\nUsuário depois do update: ");
		Usuario.printarUsuario(user);






	}
}