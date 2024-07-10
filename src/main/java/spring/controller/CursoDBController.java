package spring.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import model.DAO.CursoDAO;
import model.DAO.QuestaoDAO;
import model.DAO.TarefaDAO;
import model.system.Curso;
import model.system.Questao;
import model.system.Tarefa;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/database/curso/")
public class CursoDBController 
{
    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    TarefaDAO tarefaDAO;

    @Autowired
    QuestaoDAO questaoDAO;

    @PutMapping("/createCourse")
    public ResponseEntity<String> handleCreateCourse(@RequestParam String titulo, int user_id)
    {
        Curso novoCurso = new Curso(titulo, user_id);

        try
        {
            this.cursoDAO.insert(novoCurso);

            return ResponseEntity.ok("1");
        }
        catch (Exception e)
        {
            System.out.println("Erro -> " + e.getMessage());
            return ResponseEntity.ok("0");
        }
    }

    @PutMapping("/createAssignment")
    public ResponseEntity<String> handleCreateClass(@RequestParam float notaMaxima, @RequestParam int idCurso)
    {
        Tarefa novaTarefa = new Tarefa(notaMaxima, idCurso);

        try
        {
            this.tarefaDAO.insert(novaTarefa);

            return ResponseEntity.ok("1");
        }
        catch (Exception e)
        {
            return ResponseEntity.ok("0");
        }
    }

    @PutMapping("/createQuestion")
    public ResponseEntity<String> handleCreateClass(@RequestParam String texto, @RequestParam int idTarefa)
    {
        Questao novaQuestao = new Questao(texto, idTarefa);

        try
        {
            this.questaoDAO.insert(novaQuestao);

            return ResponseEntity.ok("1");
        }
        catch (Exception e)
        {
            return ResponseEntity.ok("0");
        }
    }

    @PutMapping("/getAllCoursesFromProfessor")
    public ResponseEntity<String> handleGetAllCourses(@RequestParam int user_id)
    {
        try
        {
            ArrayList<Curso> cursos = this.cursoDAO.getAllFromProfessor(user_id);

            ObjectMapper objectMapper = new ObjectMapper();
            String cursosJson = objectMapper.writeValueAsString(cursos);

            return ResponseEntity.ok(cursosJson);
        }
        catch (Exception e)
        {
            return ResponseEntity.ok("null");
        }
    }
    
}
