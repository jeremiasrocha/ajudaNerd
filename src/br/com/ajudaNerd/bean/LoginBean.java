package br.com.ajudaNerd.bean;

import br.com.ajudaNerd.dao.LoginDAO;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginBean {

    private String cpf;
    private String senha;
    private LoginDAO loginDAO = new LoginDAO();
    private FacesContext context;

    public LoginBean() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public String validaLogin() {

        boolean acha = loginDAO.validarLogin(cpf, senha);

        context = FacesContext.getCurrentInstance();

        if (acha) {
            return "menuPrincipal";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CPF ou Senha inválidos.", null));
            return null;
        }
    }
}
