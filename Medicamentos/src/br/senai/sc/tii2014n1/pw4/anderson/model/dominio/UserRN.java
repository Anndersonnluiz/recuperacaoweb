package br.senai.sc.tii2014n1.pw4.anderson.model.dominio;

import java.util.List;

import br.senai.sc.tii2014n1.pw4.anderson.dao.UserDao;

public class UserRN {

	private UserDao Dao;
	
	public UserRN(){
		Dao = new UserDao();
	}
	
	public void salvar(User user) throws Exception{
		if (user.getNome().length() < 5 ) {
			throw new Exception("O nome do medicamento  deve ter no minimo 5 Caracter!!");
		}
		if (user.getDosagem().isEmpty()) {
			throw new Exception("Informar a dosagem e obrigatorio!!");
		}
		if (user.getIntervalo().isEmpty()) {
			throw new Exception("Informar o intervalo e obrigatorio!!");
		}
		if (user.getDuracao().isEmpty()) {
			throw new Exception("Informa a duracao e obrigatorio!!");
		}
		
		Dao.salvar(user);
	}
	
	public List<User> listar(){
		return Dao.listarTodos();
	}
	
	public User buscarPorId(int id) {
		return Dao.buscarPorId(id);
	}
	
	public void excluir(Integer id) throws Exception{
		Dao.excluir(id);
	}

	public User buscaPorNome(String nome) {
		return Dao.buscarPorNome(nome);
	}
}
