package br.com.security;

import br.com.util.ENUMPerfil;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AutorizaListener implements PhaseListener {

	private static final long serialVersionUID = 7675112087083693053L;

	@Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();

        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        NavigationHandler nh = context.getApplication().getNavigationHandler();

        String requestPage = context.getViewRoot().getViewId();
        String perfil = null;
        String cpf = null;

        if (session != null) {
            // Guarda o perfil do usuário caso a sessão não seja nula
            perfil = String.valueOf(session.getAttribute("perfil"));
            cpf = String.valueOf(session.getAttribute("cpf"));

            // Lista das páginas liberadas para todos os usuários.
            String unlockedPages[] = {"/index.xhtml"};

            // Se o usuário estiver logado e se a página requisitada estiver na lista acima, o usuário é redirecionado para o seu perfil.
            for (int i = 0; i < unlockedPages.length; i++) {
                if (perfil != null && requestPage.equals(unlockedPages[i])) {
                    if (perfil.equals(ENUMPerfil.Usuario.toString())) {
                        nh.handleNavigation(context, null, "usuario");
                    } else if (perfil.equals(ENUMPerfil.Administrador.toString())) {
                        nh.handleNavigation(context, null, "administrador");
                    }
                }
            }
        }

        // Se a página requisitada for uma página admin e se o perfil do funcionario for diferente do perfil do administrador
        // a página é redirecionada conforme o perfil do usuário logado.
        if (perfil != null) {
            if (requestPage.indexOf("/usuario/") != -1 && !perfil.equals(String.valueOf(ENUMPerfil.Usuario.toString()))) {
                if (perfil.equals(ENUMPerfil.Usuario.toString())) {
                    nh.handleNavigation(context, null, "administrador");
                } else {
                    nh.handleNavigation(context, null, "home");
                }
            }

            // Se a página requisitada for uma página de atendente e se o perfil do usuário for diferente do perfil do mesmo,
            // a página é redirecionada conforme o perfil do usuário logado.
            if (requestPage.indexOf("/administrador/") != -1 && !perfil.equals(String.valueOf(ENUMPerfil.Administrador.toString()))) {
                if (perfil.equals(ENUMPerfil.Usuario.toString())) {
                    nh.handleNavigation(context, null, "usuario");
                } else {
                    nh.handleNavigation(context, null, "home");
                }
            }

            if (requestPage.indexOf("/templates/") != -1) {
                if (perfil.equals(ENUMPerfil.Usuario.toString())) {
                    nh.handleNavigation(context, null, "usuario");
                } else if (perfil.equals(ENUMPerfil.Administrador.toString())) {
                    nh.handleNavigation(context, null, "administrador");
                } else {
                    nh.handleNavigation(context, null, "home");
                }
            }
        } else if (requestPage.indexOf("/usuario/") != -1 && perfil == null) {

            nh.handleNavigation(context, null, "home");
        }

        // Se a página requisitada for uma página de analista e se o perfil do usuário for diferente do perfil do mesmo,
        // a página é redirecionada conforme o perfil do usuário logado.
        if (requestPage.indexOf("/administrador/") != -1 && perfil == null) {

            nh.handleNavigation(context, null, "home");

        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
