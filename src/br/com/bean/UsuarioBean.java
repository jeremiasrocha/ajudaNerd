package br.com.bean;

import br.com.dao.UsuarioDAO;
import br.com.dominio.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UsuarioBean {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FacesContext context;
    Usuario usuario = new Usuario();

    public UsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cadastrarUsuario() {

        context = FacesContext.getCurrentInstance();

        if (usuarioDAO.cadastrarUsuario(usuario)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Cadastro Realizado com Sucesso.", null));
        } else {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro no Cadastro.", null));
        }

    }

}
