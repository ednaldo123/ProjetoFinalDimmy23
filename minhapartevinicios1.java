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

