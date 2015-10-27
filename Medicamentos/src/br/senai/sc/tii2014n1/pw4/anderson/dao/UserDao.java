package br.senai.sc.tii2014n1.pw4.anderson.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;




import java.sql.SQLException;

import br.senai.sc.tii2014n1.pw4.anderson.model.dominio.User;

public class UserDao extends Dao {
	
	private final String SELECT_NOME = "SELECT * FROM user WHERE nome = ?";
	private final String INSERT = "INSERT INTO user (nome, dosagem, intervalo, duracao, senha) values (?, ?, ?, ?, ?)";
	private final String UPDATE = "UPDATE user SET nome = ?, dosagem = ?, intervalo = ?, duracao = ?, senha =?  WHERE id = ?";
	private final String SELECT = "SELECT * FROM user";
	private final String SELECT_ID = "SELECT * FROM user WHERE id = ?";
	private final String DELETE = "DELETE FROM user WHERE id = ?";
	
	public void salvar(User user) throws Exception {
		if (user.getId() == 0) {
			inserir(user);
		} else {
			alterar(user);
		}
	} 
	
	public void alterar(User user) {
		try {
			PreparedStatement ps;
			ps =  getConnection().prepareStatement(UPDATE);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getDosagem());
			ps.setString(3, user.getIntervalo());
			ps.setString(4, user.getDuracao());
			ps.setString(5, user.getSenha());
			ps.setInt(6, user.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao executar o editar: " + e);
			e.printStackTrace();
		}finally{
			getConnection();
		}
		
	}
	
	

	public void inserir(User user) {
		try {
			PreparedStatement ps;
			ps =   getConnection().prepareStatement(INSERT);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getDosagem());
			ps.setString(3, user.getIntervalo());
			ps.setString(4, user.getDuracao());
			ps.setString(5, user.getSenha());
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
				User user = parseUser(rs);
				users.add(user);
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar Select do user: " + e);
			e.printStackTrace();
		}
		return users;
	}
	
	public User buscarPorId(int id){
		try {
			PreparedStatement ps;
			ps =  getConnection().prepareStatement(SELECT_ID);
			ps.setInt(1, id);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = parseUser(rs);
				return user;
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar o buscar por id: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public void excluir(Integer id) throws Exception{
		try {
			PreparedStatement ps = null;
			ps =  getConnection().prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao executar o excluir: "+ e);
			e.printStackTrace();
		}
	}
	
	private User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setNome(rs.getString("nome"));
		user.setDosagem(rs.getString("dosagem"));
		user.setIntervalo(rs.getString("intervalo"));
		user.setDuracao(rs.getString("duracao"));
		user.setId(rs.getInt("id"));
		user.setSenha(rs.getString("senha"));
		return user;
	}
	
	public User buscarPorNome(String nome){
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_NOME);
			preparedStatement.setString(1, nome);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return parseUser(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
