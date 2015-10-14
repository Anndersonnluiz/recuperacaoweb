package br.senai.sc.tii2014n1.pw4.anderson.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import br.senai.sc.tii2014n1.pw4.anderson.model.dominio.User;

public class UserDao extends Dao {
	

	private final String INSERT = "INSERT INTO user (nome, dosagem, intervalo, duracao) values (?, ?, ?, ?)";
	private final String UPDATE = "UPDATE user SET nome = ?, dosagem = ?, intervalo = ?, duracao = ? WHERE id = ?";
	private final String SELECT = "SELECT * FROM user";
	private final String SELECT_ID = "SELECT * FROM user WHERE id = ?";
	private final String DELETE = "DELETE FROM avaliacao1 WHERE id = ?";
	
	
	public void editar(User user) {
		try {
			PreparedStatement ps;
			ps =  getConnection().prepareStatement(UPDATE);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getDosagem());
			ps.setString(3, user.getIntervalo());
			ps.setString(4, user.getDuracao());
			ps.setLong(5, user.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao executar o editar: " + e);
			e.printStackTrace();
		}finally{
			getConnection();
		}
		
	}

	public void salvar(User user) {
		try {
			PreparedStatement ps;
			ps =   getConnection().prepareStatement(INSERT);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getDosagem());
			ps.setString(3, user.getIntervalo());
			ps.setString(4, user.getDuracao());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao executar o inserir: " + e);
			e.printStackTrace();
		}
		
	}
	
	public List<User> listarTodos(){
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps =  getConnection().prepareStatement(SELECT);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setNome(rs.getString("nome"));
				user.setDosagem(rs.getString("dosagem"));
				user.setIntervalo(rs.getString("intervalo"));
				user.setDuracao (rs.getString("duracao"));
				user.setId(rs.getLong("id"));
				users.add(user);
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar Select do user: " + e);
			e.printStackTrace();
		}
		return users;
	}
	
	public User buscarPorId(Long id){
		try {
			PreparedStatement ps;
			ps =  getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs;
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setNome(rs.getString("nome"));
				user.setDosagem(rs.getString("dosagem"));
				user.setIntervalo(rs.getString("intervalo"));
				user.setDuracao(rs.getString("duracao"));
				user.setId(rs.getLong("id"));
				return user;
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar o buscar por id: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public void excluir(User user){
		try {
			PreparedStatement ps = null;
			ps =  getConnection().prepareStatement(DELETE);
			ps.setLong(1, user.getId());
		} catch (Exception e) {
			System.out.println("Erro ao executar o excluir: "+ e);
			e.printStackTrace();
		}
	}
	
}
