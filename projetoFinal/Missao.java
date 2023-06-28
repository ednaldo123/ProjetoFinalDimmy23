package projetoFinal.projetoFinal;

class Missao {
	private String cidade;

	private boolean missaoAtiva;

	public Missao(boolean missaoAtiva) {
		this.missaoAtiva = missaoAtiva;
	}

	public boolean isMissaoAtiva() {
		return missaoAtiva;
	}

	public Missao(String cidade) {
		this.cidade = cidade;

	}

	public String getCidade() {
		return cidade;
	}
}
