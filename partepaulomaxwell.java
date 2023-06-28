package projetoFinal;

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