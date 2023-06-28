package projetoFinal.projetoFinal;

import java.util.*;

//Classe Cidade para representar cada cidade do jogo
public class Cidade {

	String nome;
	List<Cidade> vizinhas;
	public List<Missao> missoes;

	public Cidade(String nome) {
		this.nome = nome;
		this.vizinhas = new ArrayList<>();
		this.missoes = new ArrayList<>();
	}

}
