package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SQLiteConnectionManager;
import model.system.Unidade;

public class UnidadeDAO extends DAO<Unidade> {

    @Override
    public void delete(int id) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM " + UnidadeDAO.nomeTabela + " WHERE id = ?");
            
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
    public void insert(Unidade unidade) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");   
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   
            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + UnidadeDAO.nomeTabela +  
            "(" + 
            Unidade.Coluna.NUMERO.getNomeColuna() +
            ")" +
            "VALUES(?);";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);            
            stmt.setInt(1, unidade.getNumero());
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("Erro em inserir a Unidade: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
    }

    @Override
    public ArrayList<Unidade> selectAll() {
        ResultSet rs = null;
        ArrayList<Unidade> unidades = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);
    
            String instrucao = "SELECT * FROM " + UnidadeDAO.nomeTabela; // Assumindo que o nome da tabela é "curso"
    
            stmt = conexao.prepareStatement(instrucao);
            rs = stmt.executeQuery();
    
            while (rs.next()) {
                Unidade unidade = new Unidade();
                unidade.setId(rs.getInt("id"));
                unidade.setNumero(rs.getInt("numero"));
                unidades.add(unidade);
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
        
        return unidades;
    }
    
    @Override
    public Unidade selectById(int id) 
    {
        try
        {  
            Class.forName("org.sqlite.JDBC");
                
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "SELECT " +
            Unidade.Coluna.ID.getNomeColuna() + "," +
            Unidade.Coluna.NUMERO.getNomeColuna() + 
            " FROM " + UnidadeDAO.nomeTabela + " WHERE id = ?;";
        
            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next())
            {
                int numero = resultSet.getInt(Unidade.Coluna.NUMERO.getNomeColuna());
                
                Unidade unidade = new Unidade(numero);
                unidade.setId(id);

                return unidade;
            }
        }
        catch (Exception e) 
        {
            System.out.println("Erro em selecionar unidade: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    @Override
    public void update(Unidade unidade) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "UPDATE " + UnidadeDAO.nomeTabela + " SET " +
            Unidade.Coluna.NUMERO.getNomeColuna() + " = ?, " +
            "WHERE id = ?";
            
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setInt(1, unidade.getNumero());
            stmt.setInt(2, unidade.getId());

            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro em atualizar a UNIDADE: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }        
    }

    private static final String nomeTabela = "unidade";
}
