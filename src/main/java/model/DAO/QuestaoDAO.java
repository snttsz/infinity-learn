package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SQLiteConnectionManager;
import model.system.Questao;

public class QuestaoDAO extends DAO<Questao>{

    @Override
    public void delete(int id) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM " + QuestaoDAO.nomeTabela + " WHERE id = ?");
            
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

    @Override
    public void insert(Questao questao) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");   
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   
            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + QuestaoDAO.nomeTabela +  
            "(" + 
            Questao.Coluna.TEXTO.getNomeColuna() + "," +
            Questao.Coluna.ID_TAREFA.getNomeColuna() +
            ")" +
            "VALUES(?,?);";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);      
                  
            stmt.setString(1, questao.getTexto());
            stmt.setInt(2, questao.getIdTarefa());
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("Erro em inserir Questão: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
    }

    @Override
    public ArrayList<Questao> selectAll() 
    {
        ResultSet rs = null;
        ArrayList<Questao> questoes = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);
    
            String instrucao = "SELECT * FROM " + QuestaoDAO.nomeTabela; 
    
            stmt = conexao.prepareStatement(instrucao);
            rs = stmt.executeQuery();
    
            while(rs.next()) 
            {
                Questao questao = new Questao();

                questao.setId(rs.getInt(Questao.Coluna.ID.getNomeColuna()));
                questao.setTexto(rs.getString(Questao.Coluna.ID_TAREFA.getNomeColuna()));
                questao.setIdTarefa(rs.getInt(Questao.Coluna.ID_TAREFA.getNomeColuna()));
                
                questoes.add(questao);
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Erro ao selecionar todas as questões: " + e.getMessage());
        } 
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return questoes;
    }
    
    @Override
    public Questao selectById(int id) 
    {
    
        Questao questao = new Questao();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try 
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM" + QuestaoDAO.nomeTabela + "WHERE id = ?";

            stmt = conexao.prepareStatement(instrucao);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while(rs.next()) 
            {
                questao.setId(rs.getInt(Questao.Coluna.ID.getNomeColuna()));
                questao.setTexto(rs.getString(Questao.Coluna.TEXTO.getNomeColuna()));
                questao.setIdTarefa(rs.getInt(Questao.Coluna.ID_TAREFA.getNomeColuna()));
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
        
        return questao;
    }

    /*   
     * Método responsável por retornar todas as questões de uma tarefa
     */
    public ArrayList<Questao> selectAllByIdTarefas(int idTarefa) 
    {
        ArrayList<Questao> questoes = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try 
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM " + QuestaoDAO.nomeTabela + " WHERE " + Questao.Coluna.ID_TAREFA.getNomeColuna() + " = ?";

            stmt = conexao.prepareStatement(instrucao);
            stmt.setInt(1, idTarefa);
            rs = stmt.executeQuery();

            while(rs.next()) 
            {
                Questao questao = new Questao();

                questao.setId(rs.getInt(Questao.Coluna.ID.getNomeColuna()));
                questao.setTexto(rs.getString(Questao.Coluna.TEXTO.getNomeColuna()));
                questao.setIdTarefa(rs.getInt(Questao.Coluna.ID_TAREFA.getNomeColuna()));

                questoes.add(questao);
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
        
        return questoes;
    }



    @Override
    public void update(Questao questao) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "UPDATE " + QuestaoDAO.nomeTabela + " SET " +
            Questao.Coluna.TEXTO.getNomeColuna() + " = ?, " +
            Questao.Coluna.ID_TAREFA.getNomeColuna() + " = ? " +
            "WHERE id = ?";
            
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, questao.getTexto());
            stmt.setInt(2, questao.getIdTarefa());
            stmt.setInt(3, questao.getId());


            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro em atualizar a questao: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }        
    }

    private static final String nomeTabela = "questao";

}
