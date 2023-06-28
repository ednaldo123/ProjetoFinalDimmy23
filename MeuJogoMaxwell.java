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
