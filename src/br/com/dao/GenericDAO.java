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
    
     //M�todo que vai realizar a conex�o com o banco de dados
    public Connection getConnection(){
        try {

            //Carrega a classe que tem o diver de conex�o, mas ainda n�o foi feito a conex�o com o banco de dados
            Class.forName("com.mysql.jdbc.Driver");

            //Baseado na linha anterior, agora � feito a conex�o com o banco de dados, onde � informado o lacal onde esta instalado
            //o banco, o usu�rio e senha de administrador do banco de dados.
            Connection cx = DriverManager.getConnection("jdbc:mysql://localhost/ajuda_nerd2?user=root&password=");

            //Retorna a conex�o
            return cx;

        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

  
    //Caso voc� queira utilizar o PreparedStatement para realizar os comandos de banco de dados, foi criado este m�todo onde ele retorna um
    //PreparedStatement atrav�s da conex�o que fora criado no m�todo getConnection(). Neste m�todo, � obrigat�rio passar o comando SQL no
    //formato de STRING
    public PreparedStatement getStatement(String sql) throws SQLException{
        return getConnection().prepareStatement(sql);
    }

    //Este m�todo serviv� para realizar consultas no banco de dados, onde obrigatoriamente eu tenho que passar o comando SQL no
    //formato de STRING e se necess�rio algum par�metro.
    public ResultSet executeQuery(String sql, Object... parametros) throws SQLException{

        //O objeto ps da classe PreparedStatement chama o m�todo getStatement() que foi criado anteriormente. � passado o comando sql para o
        //m�todo getStatement() e este retorna a conex�o com o banco e dentro desta conex�o o comando sql.
        PreparedStatement ps = getStatement(sql);

        //Caso tenha que informar algum parametro neste comando sql, � criado um for para adicionar os parametros necess�rios.
        if(parametros.length > 0 ){
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i+1, parametros[i]);

            }
        }

        //Depois de adicionados os parametros ou n�o, o objeto ps chama o m�todo executeQuery() para executar o meu comando SQL.
        return ps.executeQuery();

    }


     //Este m�todo serviv� para realizar inser��o, remo��o e atualiza��o de dados no banco de dados, onde obrigatoriamente eu tenho
    //que passar o comando SQL no formato de STRING e se necess�rio algum par�metro.
    public Object executeCommand(String sql, Object... parametros) throws SQLException{

        //O objeto ps da classe PreparedStatement chama o m�todo getStatement() que foi criado anteriormente. � passado o comando sql para o
        //m�todo getStatement() e este retorna a conex�o com o banco e dentro desta conex�o o comando sql.
        PreparedStatement ps = getStatement(sql);

         //Caso tenha que informar algum parametro neste comando sql, � criado um for para adicionar os parametros necess�rios.
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i+1, parametros[i]);

        }

        //Depois de adicionados os parametros ou n�o, o objeto ps chama o m�todo executeUpdate() para executar o meu comando SQL.
        return ps.executeUpdate();
    }
    
}
