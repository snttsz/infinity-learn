package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SQLiteConnectionManager;
import model.system.Aula;

public class AulaDAO extends DAO<Aula>
{

    @Override
    public void delete(int id) 
    {
     try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "DELETE FROM " + AulaDAO.nomeTabela +  
            " WHERE id = ?;"; 
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setInt(1, id);

            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro em deletar usuário: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }            
    }

    @Override
    public void insert(Aula aula) 
    {
        try{

            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + AulaDAO.nomeTabela +  
            "(" + 
            Aula.Coluna.CURSO_idCurso.getNomeColuna() + 
            ")" +
            "VALUES(?);";
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setInt(1, aula.getIdCurso());;


            stmt.executeUpdate();
            

        }
        catch(Exception e)
        {
            System.out.println("Erro em inserir aula: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }        
    }

    @Override
    public ArrayList<Aula> selectAll() 
    {
        ArrayList<Aula> aulas = new ArrayList<>();

        try
        {  
            Class.forName("org.sqlite.JDBC");
                
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "SELECT * FROM " + AulaDAO.nomeTabela + ";";
        
            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next())
            {
                int id = resultSet.getInt(Aula.Coluna.ID.getNomeColuna());
                int idCurso = resultSet.getInt(Aula.Coluna.CURSO_idCurso.getNomeColuna());


                Aula aula = new Aula(null, idCurso);                
                aula.setId(id);

                aulas.add(aula);
            }

            return aulas;

        }
        catch (Exception e) 
        {
            System.out.println("Erro em selecionar todas as aulas: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    @Override
    public Aula selectById(int id) 
    {
        try
        {  
            Class.forName("org.sqlite.JDBC");
                
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "SELECT * FROM " + 
            AulaDAO.nomeTabela + " WHERE id = ?;";
        
            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next())
            {
                int idCurso = resultSet.getInt(Aula.Coluna.CURSO_idCurso.getNomeColuna());
   
                Aula aula = new Aula(null, idCurso);                
                aula.setId(id);


                return aula;
            }
        }
        catch (Exception e) 
        {
            System.out.println("Erro em selecionar aula: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    @Override
    public void update(Aula entity) 
    {
        // TODO Auto-generated method stub
        
    }
    
    public static final String nomeTabela = "aula";

}
