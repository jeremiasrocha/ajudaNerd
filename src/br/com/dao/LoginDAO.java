package br.com.dao;

import br.com.dominio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO extends GenericDAO{
    
    public Usuario validarLogin(Usuario usuario){
        try {
            String sql = "select u.perfil,p.descricao from usuario u"
                    + " inner join perfil p on u.perfil = p.codigoperfil "
                    + "where cpf = ? and senha = ?";
            
            ResultSet rs = executeQuery(sql, usuario.getCpf(),
                    usuario.getSenha());
            while(rs.next()){
                usuario.getPerfil().setCodigoPerfil(rs.getInt("perfil"));
                usuario.getPerfil().setDescricao(rs.getString("descricao"));
            }
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    }
    
}
