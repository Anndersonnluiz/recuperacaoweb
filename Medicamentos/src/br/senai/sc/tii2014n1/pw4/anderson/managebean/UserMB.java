package br.senai.sc.tii2014n1.pw4.anderson.managebean;

import java.util.List;




import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


import br.senai.sc.tii2014n1.pw4.anderson.model.dominio.User;
import br.senai.sc.tii2014n1.pw4.anderson.model.dominio.UserRN;

@ManagedBean
public class UserMB {

	private List<User> usuarios;
	private User user;
	private UserRN userRN;
		
	@PostConstruct
	public void init(){
		userRN = new UserRN();
		user = new User();
	}
	
	public List<User> getUsuarios(){
		if (usuarios == null) {
			usuarios = userRN.listar();
		}
		return usuarios;
	}
	
	public void setUsuarios(List<User> usuarios){
		this.usuarios = usuarios;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String salvar(){
		try {
			userRN.salvar(user);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "userlist";
	}

	
	public String excluir(){
		try {
			userRN.excluir(user);
			usuarios = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"";
	}
	
	public String editar(String idParam) throws Exception{
		Long id = Long.parseLong(idParam);
		user = userRN.buscarPorId(id);
		return "userform";
	}
	
}
