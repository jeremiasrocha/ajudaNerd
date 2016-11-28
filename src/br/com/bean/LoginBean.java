package br.com.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dao.LoginDAO;
import br.com.dominio.Usuario;
import br.com.util.ENUMPerfil;

public class LoginBean {

    private Usuario usuario = new Usuario();
    private LoginDAO loginDAO = new LoginDAO();
    private FacesContext context;

    public LoginBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String validaLogin() {

        usuario = loginDAO.validarLogin(usuario);
        context = FacesContext.getCurrentInstance();

        // Incluir Perfil na Sessão.
        HttpSession sessao = (HttpSession) context.getExternalContext().getSession(false);

        // Inserir o Perfil na Seção
        sessao.setAttribute("perfil", usuario.getPerfil().getDescricao());

        sessao.setAttribute("cpfUsuario", usuario.getCpf());

        // Verifica o Tipo de Perfil Encontrado.
        if (usuario.getPerfil().getCodigoPerfil() != 0) {

            if (usuario.getPerfil().
                    getDescricao().equals(ENUMPerfil.Usuario.toString())) {
                return "usuario";

            } else if (usuario.getPerfil().
                    getDescricao().equals(ENUMPerfil.Administrador.toString())) {
                return "administrador";
            }

        } else {

            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Usuário não cadastrado.", ""));

        }

        return null;
    }

}
