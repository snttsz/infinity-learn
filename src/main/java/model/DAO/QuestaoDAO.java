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
    public void insert(Questao curso) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");   
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   
            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + QuestaoDAO.nomeTabela +  
            "(" + 
            Questao.Coluna.TEXTO.getNomeColuna() +
            ")" +
            "VALUES(?);";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);            
            stmt.setString(1, curso.getTexto());
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
    public ArrayList<Questao> selectAll() {
        ResultSet rs = null;
        ArrayList<Questao> cursos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);
    
            String instrucao = "SELECT * FROM " + QuestaoDAO.nomeTabela; // Assumindo que o nome da tabela é "curso"
    
            stmt = conexao.prepareStatement(instrucao);
            rs = stmt.executeQuery();
    
            while (rs.next()) {
                Questao questao = new Questao();
                questao.setId(rs.getInt("id"));
                questao.setTexto(rs.getString("texto"));
                cursos.add(questao);
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
        } 
        finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        
        return cursos;
    }
    
    @Override
    public Questao selectById(int id) {
    
        Questao questao = null;
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM" + QuestaoDAO.nomeTabela + "WHERE id = ?";

            stmt = conexao.prepareStatement(instrucao);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                questao = new Questao();
                questao.setId(rs.getInt("id"));
                questao.setTexto(rs.getString("texto"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao buscar curso por ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        
        return questao;
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
            "WHERE id = ?";
            
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, questao.getTexto());
            stmt.setInt(2, questao.getId());

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
