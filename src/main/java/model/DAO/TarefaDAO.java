package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SQLiteConnectionManager;
import model.system.Questao;
import model.system.Tarefa;

public class TarefaDAO extends DAO<Tarefa> 
{

    @Override
    public Tarefa selectById(int id) 
    {
    
        Tarefa tarefa = new Tarefa();
        QuestaoDAO questaoDAO = new QuestaoDAO();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try 
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM " + TarefaDAO.nomeTabela + " WHERE id = ?";

            stmt = conexao.prepareStatement(instrucao);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while(rs.next()) 
            {
                tarefa.setId(rs.getInt(Tarefa.Coluna.ID.getNomeColuna()));
                tarefa.setNotaMaxima(rs.getDouble(Tarefa.Coluna.NOTAMAXIMA.getNomeColuna()));
                tarefa.setIdAula(rs.getInt(Tarefa.Coluna.AULAID.getNomeColuna()));
                
                /* Pegando todas as questões desta tarefa */
                tarefa.setQuestoes(questaoDAO.selectAllByIdTarefas(id));
                
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Erro ao buscar tarefa por ID: " + e.getMessage());
        } 
        finally 
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return tarefa;
    }

    @Override
    public void insert(Tarefa tarefa) 
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + TarefaDAO.nomeTabela +  
                "(" + 
                Tarefa.Coluna.NOTAMAXIMA.getNomeColuna() +","+
                Tarefa.Coluna.AULAID.getNomeColuna() +
                ")" +
                "VALUES(?,?);";

                PreparedStatement stmt = conexao.prepareStatement(instrucao); 
            
            stmt.setDouble(1, tarefa.getNotaMaxima());
            stmt.setInt(2, tarefa.getIdAula());

            stmt.executeUpdate();
        } 
        catch(Exception e) 
        {
            System.out.println("Erro ao inserir Tarefa: " + e.getMessage());
        } 
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }


    public ArrayList<Tarefa> selectAll() 
    {
        ResultSet rs = null;
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        QuestaoDAO questaoDAO = new QuestaoDAO();
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);
    
            String instrucao = "SELECT * FROM " + TarefaDAO.nomeTabela; 
    
            stmt = conexao.prepareStatement(instrucao);
            rs = stmt.executeQuery();
    
            while (rs.next()) 
            {
                Tarefa tarefa = new Tarefa();
                int idTarefa = rs.getInt(Tarefa.Coluna.ID.getNomeColuna());
                tarefa.setId(idTarefa);
                tarefa.setNotaMaxima(rs.getDouble(Tarefa.Coluna.NOTAMAXIMA.getNomeColuna()));
                tarefa.setIdAula(rs.getInt(Tarefa.Coluna.AULAID.getNomeColuna()));
                tarefa.setQuestoes(questaoDAO.selectAllByIdTarefas(idTarefa));
                tarefas.add(tarefa);
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Erro ao buscar todas as tarefas: " + e.getMessage());
        } 
        finally 
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return tarefas;
    }

      /*   
     * Método responsável por retornar todas as tarefas de uma aula
     */
    public ArrayList<Tarefa> selectAllByIdAula(int idAula) 
    {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        QuestaoDAO questaoDAO = new QuestaoDAO();
    
        try 
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM " + TarefaDAO.nomeTabela + " WHERE " + Tarefa.Coluna.AULAID.getNomeColuna() + " = ?";

            stmt = conexao.prepareStatement(instrucao);
            stmt.setInt(1, idAula);
            rs = stmt.executeQuery();

            while(rs.next()) 
            {
                Tarefa tarefa = new Tarefa();

                tarefa.setId(rs.getInt(Tarefa.Coluna.ID.getNomeColuna()));
                tarefa.setNotaMaxima(rs.getDouble(Tarefa.Coluna.NOTAMAXIMA.getNomeColuna()));
                tarefa.setIdAula(rs.getInt(Tarefa.Coluna.AULAID.getNomeColuna()));


                tarefa.setQuestoes(questaoDAO.selectAllByIdTarefas(tarefa.getId()));

                tarefas.add(tarefa);
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Erro ao selecionar a questão pelo ID: " + e.getMessage());
        } 
        finally 
        {
            SQLiteConnectionManager.desconectar();

        }
        
        return tarefas;
    }


    @Override
    public void update(Tarefa tarefa) 
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "UPDATE " + TarefaDAO.nomeTabela + " SET " +
                Tarefa.Coluna.NOTAMAXIMA.getNomeColuna() + " = ?, " +
                Tarefa.Coluna.AULAID.getNomeColuna() +  " = ? " +
                " WHERE id = ?";
                
                
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setDouble(1, tarefa.getNotaMaxima());
            stmt.setInt(2, tarefa.getIdAula());
            stmt.setInt(3, tarefa.getId());

            stmt.executeUpdate();
        } 
        catch(Exception e)
        {
            System.out.println("Erro ao atualizar Tarefa: " + e.getMessage());
        }
        finally 
        {
            SQLiteConnectionManager.desconectar();
        }
}

    @Override
    public void delete(int id) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            /*Deletando todas as questões associadas à tarefa */
            QuestaoDAO questaoDAO = new QuestaoDAO();
            ArrayList<Questao> questoes = questaoDAO.selectAllByIdTarefas(id);
            for(Questao q: questoes)
            {
                questaoDAO.delete(q.getId());
            }

            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM " + TarefaDAO.nomeTabela + " WHERE id = ?");
            
            stmt.setInt(1, id);

            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro ao deletar Questão: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }
    private static final String nomeTabela = "tarefa";
   

}
