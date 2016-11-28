package br.com.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.dominio.Usuario;

public class UsuarioDAO extends GenericDAO {

    public boolean cadastrarUsuario(Usuario usuario) {

        try {
            String sql = "insert into usuario (cpf,senha,nome,estado,nascimento,cidade,cep,telefone,endereco,celular,bairro,email,perfil) values(?,?,?,?,?,?,?,?,?,?,?,?,1)";
            
            executeCommand(sql, usuario.getCpf(), usuario.getSenha(), usuario.getNome(), usuario.getEstado(),
                    usuario.getNascimento(), usuario.getCidade(), usuario.getCep(), usuario.getTelefone(),
                    usuario.getEndereco(), usuario.getCelular(), usuario.getBairro(), usuario.getEmail(), 
                    usuario.getPerfil().getCodigoPerfil());
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
