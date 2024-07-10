package spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.DAO.CursoDAO;
import model.DAO.QuestaoDAO;
import model.DAO.TarefaDAO;
import model.DAO.UsuarioDAO;
import spring.utils.FileStorageService;

@Configuration
public class AppConfig 
{
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Bean
    public FileStorageService fileStorageService() 
    {
        return new FileStorageService();
    }

    @Bean
    public UsuarioDAO usuarioDAO()
    {
        return new UsuarioDAO();
    }

    @Bean
    public CursoDAO cursoDAO()
    {
        return new CursoDAO();
    }

    @Bean
    public TarefaDAO tarefaDAO()
    {
        return new TarefaDAO();
    }

    @Bean QuestaoDAO questaoDAO()
    {
        return new QuestaoDAO();
    }
}
