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
		SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();
		SpringApplication.run(Application.class, args);
		
		// TarefaDAO tarefaDAO = new TarefaDAO();
		// QuestaoDAO questaoDAO = new QuestaoDAO();

		// Questao questao = new Questao("questao d", 2);
		// Tarefa tarefa = new Tarefa(9, 1);
		
		// tarefaDAO.delete(2);
		
		// for(Tarefa t: tarefaDAO.selectAll())
		// {
		// 	Tarefa.printarTarefa(t);
		// }

	}
}