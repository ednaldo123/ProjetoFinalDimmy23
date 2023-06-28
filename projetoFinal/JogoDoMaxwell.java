package projetoFinal.projetoFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class JogoDoMaxwell {
	// Declarações de variáveis referentes as cidades, as moedas, as jóias e as
	// missões.
	private Cidade ubud;
	private Cidade legmod;
	private Cidade nekikh;
	private Cidade gritesthr;
	private Cidade dogrove;
	private Cidade lastwatch;
	private Cidade smalia;
	private Cidade oldcalia;
	private Cidade kalb;
	private Cidade aymar;
	private Cidade defalsia;
	private Cidade vunese;
	private Cidade karhora;
	private Cidade chandir;
	private Cidade bun;
	private Cidade kasya;
	private Cidade nargumun;

	private int moedasTransporte;
	private int limiarJóia;
	private ArrayList<Missao> missaoLista;
	private boolean missaoAtiva;
	private String cidadeMissao;

	// Construtor da classe JogoDoMaxwell
	public JogoDoMaxwell() {
		criarCidades();
		criarVizinhas();

	}

	// Método responsável por criar as cidades do jogo
	private void criarCidades() {
		ubud = new Cidade("Ubud");
		legmod = new Cidade("Kingdom of Legmod");
		nekikh = new Cidade("Principality of Nekikh");
		gritesthr = new Cidade("Principality of Gritesthr");
		dogrove = new Cidade(" Protectorate of Dogrove");
		lastwatch = new Cidade("Kingdom of Lastwatch");
		smalia = new Cidade("Grand Duchy of Smalia");
		oldcalia = new Cidade("Kingdom of Oldcalia");
		kalb = new Cidade("Kingdom of Kalb");
		aymar = new Cidade("Aymar League");
		defalsia = new Cidade("Defalsia");
		vunese = new Cidade("Vunese of Empire");
		karhora = new Cidade("Principality of Karhora");
		chandir = new Cidade("Chandir Sultanate");
		bun = new Cidade("Bun");
		kasya = new Cidade("Principality of Kasya");
		nargumun = new Cidade("Nargumun");

	}

	// Método responsável por criar as vizinhas de cada cidade ny
	private void criarVizinhas() {
		ubud.vizinhas.addAll(Arrays.asList(legmod, nekikh));
		legmod.vizinhas.addAll(Arrays.asList(ubud, nekikh, oldcalia, dogrove, gritesthr));
		nekikh.vizinhas.addAll(Arrays.asList(legmod, gritesthr, ubud));
		gritesthr.vizinhas.addAll(Arrays.asList(nekikh, dogrove, legmod, lastwatch));
		dogrove.vizinhas.addAll(Arrays.asList(gritesthr, lastwatch, oldcalia, legmod));
		lastwatch.vizinhas.addAll(Arrays.asList(dogrove, smalia, gritesthr));
		smalia.vizinhas.addAll(Arrays.asList(lastwatch, oldcalia));
		oldcalia.vizinhas.addAll(Arrays.asList(smalia, kalb, dogrove, defalsia, aymar, legmod));
		kalb.vizinhas.addAll(Arrays.asList(oldcalia, aymar, vunese));
		aymar.vizinhas.addAll(Arrays.asList(defalsia, vunese, kalb, karhora, bun, oldcalia, chandir, nargumun));
		defalsia.vizinhas.addAll(Arrays.asList(aymar, oldcalia));
		vunese.vizinhas.addAll(Arrays.asList(aymar, chandir, kalb));
		karhora.vizinhas.addAll(Arrays.asList(aymar, nargumun));
		chandir.vizinhas.addAll(Arrays.asList(vunese, aymar, bun, kasya));
		bun.vizinhas.addAll(Arrays.asList(aymar, chandir, nargumun));
		kasya.vizinhas.addAll(Collections.singletonList(chandir));
		nargumun.vizinhas.addAll(Collections.emptyList());
	}

	// Método responsável por realizar um movimento do jogador
	private void realizarMovimento(Cidade cidade) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Você está na cidade de " + cidade.nome);
		System.out.println("Quantas moedas de transporte você tem? (" + moedasTransporte + ")");
		System.out.println("De onde você vem, e para onde você vai?");
		String origem = scanner.nextLine();
		String destino = scanner.nextLine();

		Cidade cidadeOrigem = getCidadeByName(origem);
		Cidade cidadeDestino = getCidadeByName(destino);

		// Verifica se as cidades de origem e destino são válidas
		if (cidadeOrigem == null || cidadeDestino == null) {
			System.out.println("Cidades de origem ou destino inválidas. Tente novamente.");
			realizarMovimento(cidade);
			return;
		}
		// Verifica se a cidade de destino é uma vizinha da cidade atual
		if (!cidade.vizinhas.contains(cidadeDestino)) {
			System.out.println("Você não pode se mover para essa cidade. Tente novamente.");
			realizarMovimento(cidade);
			return;
		}
		// Verifica se há uma missão ativa e se o destino corresponde a uma missão
		// específica
		if (cidadeDestino == vunese) {
			realizarMissaoVunese();
		} else if (cidadeDestino == defalsia) {
			realizarMissaoDefalsia();
		} else if (cidadeDestino == kalb) {
			realizarMissaoKalb();
		}
		// Verifica se o movimento realizado corresponde a uma missão específica
		if (cidadeOrigem == kalb && cidadeDestino == smalia && missaoAtiva) {
			limiarJóia += 2;
			moedasTransporte += 2;
			System.out.println(
					"Você completou a missão em smalia e recebeu 2 moedas de transporte e o limiar de poder aumentou em 2 pontos");
			missaoAtiva = false;

		} else if (cidadeOrigem == vunese && cidadeDestino == ubud && missaoAtiva) {
			limiarJóia -= 4;
			moedasTransporte += 10;
			System.out.println(
					"Você completou a missão em ubud of Empire e recebeu 10 moedas de transporte e o limiar de poder caiu em 2 pontos");
			missaoAtiva = false;

		} else if (cidadeOrigem == defalsia && cidadeDestino == kasya && missaoAtiva) {
			limiarJóia += 1;
			moedasTransporte += 3;
			System.out.println(
					"Você completou a missão em kasya e recebeu 2 moedas de transporte e o limiar de poder aumentou em 2 pontos");
			missaoAtiva = false;

		}

		if (moedasTransporte > 0) {
			realizarTroca(cidadeOrigem, cidadeDestino);// Realiza a troca de moedas por limiar na jóia, dependendo das
														// condições.
			moedasTransporte--;
			limiarJóia += obterGanhoDePoder(cidadeDestino);// Adiciona o ganho de poder da jóia com base na cidade de
															// destino.
			if (limiarJóia < 0) { // Verifica se o limiar da jóia é menor que 0.
				limiarJóia = 0;// Define o limiar da jóia como 0, pois não pode ser negativo.

			} else if (limiarJóia > 7) {// Verifica se o limiar da jóia excedeu o limite de poder.
				System.out.println("A jóia excedeu seu limite de poder. Você morreu.");
				return;
			}
			System.out.println("Você chegou à cidade de " + cidadeDestino.nome);// Imprime a mensagem de chegada à
																				// cidade de destino.
			System.out.println("Limiar de poder da jóia: " + limiarJóia);// Imprime o limiar de poder atual da jóia.
			System.out.println("Moedas de transporte restantes: " + moedasTransporte);// Imprime o número de moedas de
																						// transporte restantes.

			if (cidadeDestino == nargumun) {// Verifica se a cidade de destino é Nargumun.
				if (moedasTransporte >= 4) {// Verifica se o jogador possui pelo menos 4 moedas de transporte.
					System.out.println(
							"\nParabéns! Você chegou a Nargumun com mais de 4 moedas de transporte. Você é condecorado Lorde de Nargumun!");
				} else {
					System.out.println(
							"\nVocê chegou a Nargumun, mas não possui moedas de transporte suficientes para se tornar Lorde. O povo de Nargumun o acolhe como servo da coroa.");
				}
				return;// Encerra a função
			}
			realizarMovimento(cidadeDestino);// Realiza o próximo movimento do jogador.
		} else {
			System.out.println("Você não possui mais moedas de transporte. Você morreu.");
		}

	}

	private void realizarTroca(Cidade cidadeOrigem, Cidade cidadeDestino) {
		if (moedasTransporte < 5 && cidadeOrigem.vizinhas.size() < 3) {// Verifica se o jogador possui menos de 5 moedas
																		// de transporte e se a cidade de origem tem
																		// menos de 3 cidades vizinhas.
			System.out.println("Deseja trocar suas moedas por limiar na jóia? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();

			if (resposta.equals("S")) {// Se o jogador responder "S" (sim), realiza a troca.
				moedasTransporte -= 1;
				limiarJóia += 1;
				System.out.println("Você perdeu 1 moeda de transporte e ganhou 1 ponto de limiar na jóia.");
			} else {// Caso contrário, apenas retira uma moeda de transporte.
				moedasTransporte -= 1;
				System.out.println("Você perdeu 1 moeda de transporte.");
			}

		} else if (moedasTransporte < 5 && cidadeOrigem.vizinhas.size() >= 3) {// Verifica se o jogador possui menos de
																				// 5 moedas de transporte e se a cidade
																				// de origem tem 3 ou mais cidades
																				// vizinhas.
			System.out.println("Deseja trocar suas moedas por limiar na jóia? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();

			if (resposta.equals("S")) {
				moedasTransporte -= 1;
				limiarJóia += 2;
				System.out.println("Você perdeu uma moeda de transporte e ganhou 2 pontos de limiar na jóia.");
			} else {
				moedasTransporte += 2;
				System.out.println("Você recebeu 2 moedas de transporte.");
			}
		} else if (moedasTransporte >= 5 && cidadeOrigem.vizinhas.size() < 3) {
			System.out.println("Deseja trocar suas moedas por limiar na jóia? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();

			if (resposta.equals("S")) {
				moedasTransporte -= 3;
				limiarJóia += 2;
				System.out.println("Você perdeu 3 moedas de transporte e ganhou 2 pontos de limiar na jóia.");
			} else {
				moedasTransporte -= 2;
				System.out.println("Você perdeu 2 moedas de transporte.");
			}
		} else if (moedasTransporte >= 5 && cidadeOrigem.vizinhas.size() <= 3) {
			System.out.println("Deseja trocar suas moedas por limiar na jóia? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();
			if (resposta.equals("S")) {
				moedasTransporte -= 1;
				limiarJóia += 3;
				System.out.println("Você perdeu 1 moeda de transporte e ganhou 3 pontos de limiar na jóia.");
			} else {
				moedasTransporte -= 3;
				System.out.println("Você perdeu 3 moedas de transporte.");
			}

		}
	}

	private void realizarMissaoVunese() {
		if (moedasTransporte > 0) {
			System.out.println("\nVocê chegou à cidade de Vunese of Empire.");
			System.out.println("Há uma missão disponível: 'Glória dos Retornados'.");
			System.out.println("Prêmio por aceitar a missão: 1 moeda de transporte.");
			System.out.println("Missão: Vá até Ubud e receba a Glória dos Retornados.");

			System.out.println("\nDeseja aceitar a missão 'Glória dos Retornados'? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();

			if (resposta.equals("S")) {
				moedasTransporte += 1;
				System.out.println("\nVocê aceitou a missão 'Glória dos Retornados'.");
				System.out.println("Prêmio: 1 moeda de transporte.");

			} else {
				System.out.println("\nVocê decidiu não aceitar a missão 'Glória dos Retornados'.");
			}

		}
	}

	private void realizarMissaoDefalsia() {
		if (moedasTransporte > 0) {
			System.out.println("\nVocê chegou à cidade de Defalsia.");
			System.out.println("Há uma missão disponível: 'Botas do Poder'.");
			System.out.println("Prêmio por aceitar a missão: 6 moedas de transporte.");
			System.out.println("Missão: Vá até a cidade de Principality of Kasya e receba as botas do poder.");

			System.out.println("\nDeseja aceitar a missão 'Botas do Poder'? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();

			if (resposta.equals("S")) {
				moedasTransporte += 6;
				missaoAtiva = true;
				System.out.println("\nVocê aceitou a missão 'Botas do Poder'.");
				System.out.println("Prêmio: 6 moedas de transporte.");

			} else {
				System.out.println("\nVocê decidiu não aceitar a missão 'Botas do Poder'.");
			}

		}
	}

	private void realizarMissaoKalb() {
		if (moedasTransporte > 0) {
			System.out.println("\nVocê chegou à cidade de Kalb.");
			System.out.println("Há uma missão disponível: 'Luvas de poder'.");
			System.out.println("Prêmio por aceitar a missão: 4 moedas de transporte.");
			System.out.println("Missão: Vá até Grand duchy of Smalia e receba as luvas de poder.");

			System.out.println("\nDeseja aceitar a missão 'O Tesouro Perdido'? (S/N)");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine().toUpperCase();

			if (resposta.equals("S")) {
				moedasTransporte += 4;
				System.out.println("\nVocê aceitou a missão 'Luvas de poder'.");
				System.out.println("Prêmio: 4 moedas de transporte.");

			} else {
				System.out.println("\nVocê decidiu não aceitar a missão 'Luvas de poder'.");
			}

		}
	}

	private int obterGanhoDePoder(Cidade cidade) {// Mapeia o nome da cidade com o ganho de poder correspondente.
		Map<String, Integer> poderes = new HashMap<>();
		poderes.put("Ubud", 0);
		poderes.put("Kingdom of Legmod", 2);
		poderes.put("Principality of Nekikh", 1);
		poderes.put("Principality of Gritesthr", 5);
		poderes.put("Protectorate of Dogrove", 3);
		poderes.put("Kingdom of Lastwatch", -2);
		poderes.put("Grand Duchy of Smalia", 1);
		poderes.put("Kingdom of Oldcalia", 4);
		poderes.put("Kingdom of Kalb", 2);
		poderes.put("Aymar League", 1);
		poderes.put("Defalsia", -3);
		poderes.put("Vunese of Empire", 0);
		poderes.put("Principality of Karhora", -2);
		poderes.put("Chandir Sultanate", 1);
		poderes.put("Bun", 5);
		poderes.put("Principality of Kasya", -7);
		poderes.put("Nargumun", 0);

		return poderes.get(cidade.nome);
	}

	private Cidade getCidadeByName(String nome) {// recebe o nome de uma cidade como argumento e retorna o objeto
		switch (nome.toLowerCase()) {
		case "ubud":
			return ubud;
		case "kingdom of legmod":
			return legmod;
		case "principality of nekikh":
			return nekikh;
		case "principality of gritesthr":
			return gritesthr;
		case "protectorate of dogrove":
			return dogrove;
		case "kingdom of lastwatch":
			return lastwatch;
		case "grand duchy of smalia":
			return smalia;
		case "kingdom of oldcalia":
			return oldcalia;
		case "kingdom of kalb":
			return kalb;
		case "aymar league":
			return aymar;
		case "defalsia":
			return defalsia;
		case "vunese of empire":
			return vunese;
		case "principality of karhora":
			return karhora;
		case "chandir sultanate":
			return chandir;
		case "bun":
			return bun;
		case "principality of kasya":
			return kasya;
		case "nargumun":
			return nargumun;
		default:
			return null;
		}
	}

	public void iniciarJogo() {// Método responsável por realizar a jornada no jogo
		moedasTransporte = 3;// Inicializa o número de moedas de transporte com 3
		limiarJóia = 0;// Inicializa o limiar da jóia com 0

		System.out.println("Bem-vindo ao Jogo de Transporte da Jóia!");
		System.out.println("Sua missão é transportar uma jóia mágica de Ubud até Nargumun sem morrer.");
		System.out.println("A jóia ganha poder à medida que você percorre os trajetos.");
		System.out.println("Você pode fazer missões para otimizar sua jornada.");
		System.out.println("Clique aqui para vizualizar o mapa  ");

		realizarMovimento(ubud); // Inicia a jornada a partir da cidade de Ubud
	}
}
