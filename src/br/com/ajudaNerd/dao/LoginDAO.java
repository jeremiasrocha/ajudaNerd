package br.com.ajudaNerd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO extends GenericDAO {

    public boolean validarLogin(String cpf, String senha) {

        try {
            String sql = "select * from login where cpf=? and senha=?";

            ResultSet rs = executeQuery(sql, cpf, senha);

            boolean acha = false;

            while (rs.next()) {

                acha = true;
            }

            return acha;

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
