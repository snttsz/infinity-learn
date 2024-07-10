package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/* 
 * Classe abstrata Data Access Object (Objeto de Acesso a Dados)
 * Serve como intermediária entre a aplicação java e o banco de dados.
 * 
 * Todos os metódos especificados aqui serão implementados em todas as tabelas simples do banco de dados
 */
public abstract class DAO<T> 
{
    /* 
     * Método responsável por puxar do banco de dados um elemento através do ID
     */
    public abstract T selectById(int id) throws SQLException;

    /* 
     * Método responsável por puxar do banco de dados todos os elementos de um tabela 
     */
    public abstract ArrayList<T> selectAll() throws SQLException;

    /* 
     * Método responsável por inserir um elemento, em uma determinada tabela no banco de dados
     */
    public abstract void insert(T entity) throws SQLException;


    public abstract void update(T entity) throws SQLException;
    
    /* 
     * Método responsável por deletar um elemento em uma determinada tabela no banco de dados
     */
    public abstract void delete(int id) throws SQLException;

}

