package br.com.dominio;

public class Perfil {
    
    private Integer codigoPerfil;
    private String descricao;

    public Integer getCodigoPerfil() {
    	if (codigoPerfil == null) {
			codigoPerfil = new Integer(0);
		}
        return codigoPerfil;
    }

    public void setCodigoPerfil(int codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
