package br.senai.sc.tii2014n1.pw4.anderson.managebean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.senai.sc.tii2014n1.pw4.anderson.model.dominio.User;
import br.senai.sc.tii2014n1.pw4.anderson.model.dominio.UserRN;


@SessionScoped
@ManagedBean
public class SessaoMB {
	private User usuarioLogado;
	private User usuarioForm;
	
	

	@PostConstruct
	public void iniciar(){
		setUsuarioForm(new User());
	}
	
	public User getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(User usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public String entrar(){
		UserRN rn = new UserRN();
		User usuarioBanco = rn.buscaPorNome(usuarioForm.getNome());
		if (usuarioBanco != null
				&& getUsuarioForm().getNome().equalsIgnoreCase(usuarioBanco.getNome()) 
				&& getUsuarioForm().getSenha().equals(usuarioBanco.getSenha())) {
			usuarioLogado = usuarioBanco;
			return"/index";
		}
		return "";
	}
	
	public String sair(){
		usuarioLogado = null;
		return"/index?faces-redirect=true";
	}
	
	public Boolean estaLogado(){
		return usuarioLogado != null;
	}
}
