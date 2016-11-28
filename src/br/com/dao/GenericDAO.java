package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Israel
 */
public abstract class GenericDAO {
    
     //Método que vai realizar a conexão com o banco de dados
    public Connection getConnection(){
        try {

            //Carrega a classe que tem o diver de conexão, mas ainda não foi feito a conexão com o banco de dados
            Class.forName("com.mysql.jdbc.Driver");

            //Baseado na linha anterior, agora é feito a conexão com o banco de dados, onde é informado o lacal onde esta instalado
            //o banco, o usuário e senha de administrador do banco de dados.
            Connection cx = DriverManager.getConnection("jdbc:mysql://localhost/ajuda_nerd2?user=root&password=");

            //Retorna a conexão
            return cx;

        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

  
    //Caso você queira utilizar o PreparedStatement para realizar os comandos de banco de dados, foi criado este método onde ele retorna um
    //PreparedStatement através da conexão que fora criado no método getConnection(). Neste método, é obrigatório passar o comando SQL no
    //formato de STRING
    public PreparedStatement getStatement(String sql) throws SQLException{
        return getConnection().prepareStatement(sql);
    }

    //Este método servivá para realizar consultas no banco de dados, onde obrigatoriamente eu tenho que passar o comando SQL no
    //formato de STRING e se necessário algum parâmetro.
    public ResultSet executeQuery(String sql, Object... parametros) throws SQLException{

        //O objeto ps da classe PreparedStatement chama o método getStatement() que foi criado anteriormente. É passado o comando sql para o
        //método getStatement() e este retorna a conexão com o banco e dentro desta conexão o comando sql.
        PreparedStatement ps = getStatement(sql);

        //Caso tenha que informar algum parametro neste comando sql, é criado um for para adicionar os parametros necessários.
        if(parametros.length > 0 ){
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i+1, parametros[i]);

            }
        }

        //Depois de adicionados os parametros ou não, o objeto ps chama o método executeQuery() para executar o meu comando SQL.
        return ps.executeQuery();

    }


     //Este método servivá para realizar inserção, remoção e atualização de dados no banco de dados, onde obrigatoriamente eu tenho
    //que passar o comando SQL no formato de STRING e se necessário algum parâmetro.
    public Object executeCommand(String sql, Object... parametros) throws SQLException{

        //O objeto ps da classe PreparedStatement chama o método getStatement() que foi criado anteriormente. É passado o comando sql para o
        //método getStatement() e este retorna a conexão com o banco e dentro desta conexão o comando sql.
        PreparedStatement ps = getStatement(sql);

         //Caso tenha que informar algum parametro neste comando sql, é criado um for para adicionar os parametros necessários.
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i+1, parametros[i]);

        }

        //Depois de adicionados os parametros ou não, o objeto ps chama o método executeUpdate() para executar o meu comando SQL.
        return ps.executeUpdate();
    }
    
}
