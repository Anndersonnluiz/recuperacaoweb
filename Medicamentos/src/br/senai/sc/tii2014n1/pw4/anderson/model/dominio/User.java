package br.senai.sc.tii2014n1.pw4.anderson.model.dominio;

public class User {
	
	private int id;
	private String nome;
	private String dosagem;
	private String intervalo;
	private String duracao;
	
	public User(int id, String nome, String dosagem, String intervalo,
			String duracao) {
		super();
		this.id = id;
		this.nome = nome;
		this.dosagem = dosagem;
		this.intervalo = intervalo;
		this.duracao = duracao;
	}



	public User(){
		
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	public String getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	
	
}
