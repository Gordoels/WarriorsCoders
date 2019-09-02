package com.stefanini.heroi.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.stefanini.heroi.dto.DueloDto;
import com.stefanini.heroi.dto.PartidaDto;
import com.stefanini.heroi.dto.PersonagemDto;
import com.stefanini.heroi.util.PersonagemUtil;

/**
 * Classe responsável pelos duelos
 * 
 * @author Gordoel
 *
 */

public class DueloBO {

	
	private PartidaDto partida;
	private List<PersonagemDto> jogadoresData;
	private Integer x[] = new Integer[2]; //começando no index 2 para buscar apenas os poderes
	Integer nPartidas = 0;
	private List<DueloDto> historicoDuelos = new ArrayList<DueloDto>();
	Random random = new Random();

	public DueloBO() {

		partida = new PartidaDto();
		jogadoresData = PersonagemBO.getJogadoresData();
	}

	public DueloDto criaDuelo() {

		List<PersonagemDto> herois;
		List<String> keyPoderes = Arrays.asList("inteligencia", "forca", "defesa", "destreza", "poder", "combate");
		String habilidadeRandom = keyPoderes.get(new Random().nextInt(keyPoderes.size()));
		PersonagemDto ganhador;

		herois = randomHeroi();

		DueloDto duelo = new DueloDto(herois);

		if (jogadoresData.get(x[0]).getHabilidades().get(habilidadeRandom) > jogadoresData.get(x[1]).getHabilidades()
				.get(habilidadeRandom)) {

			jogadoresData.get(x[0]).getHabilidades().replace(habilidadeRandom,
					jogadoresData.get(x[0]).getHabilidades().get(habilidadeRandom) + 2);
			jogadoresData.get(x[1]).getHabilidades().replace(habilidadeRandom,
					jogadoresData.get(x[1]).getHabilidades().get(habilidadeRandom) - 2);

			ganhador = jogadoresData.get(x[0]);
		} else {

			jogadoresData.get(x[0]).getHabilidades().replace(habilidadeRandom,
					jogadoresData.get(x[0]).getHabilidades().get(habilidadeRandom) - 2);
			jogadoresData.get(x[1]).getHabilidades().replace(habilidadeRandom,
					jogadoresData.get(x[1]).getHabilidades().get(habilidadeRandom) + 2);

			ganhador = jogadoresData.get(x[1]);
		}
		ganhador.setVitorias();

		duelo.setGanhador(ganhador);
		duelo.setForca(habilidadeRandom);
		historicoDuelos.add(duelo);
		partida.setDuelos(duelo);

		nPartidas++;
		return duelo;
	}

	public List<DueloDto> getHistorico() {

		return historicoDuelos;
	}

	private List<PersonagemDto> randomHeroi() {

		if (partida.getDuelos().isEmpty()) {
			List<PersonagemDto> herois = null;

			boolean ok = true;

			while (x[0] == x[1] || ok) {
				x[0] = random.nextInt(jogadoresData.size());
				x[1] = random.nextInt(jogadoresData.size());

				if (!jogadoresData.get(x[0]).getAlinhamento().equals(jogadoresData.get(x[1]).getAlinhamento()))
					ok = false;
			}
			jogadoresData.get(x[0]).setId(x[0]);
			jogadoresData.get(x[1]).setId(x[1]);
			herois = Arrays.asList(jogadoresData.get(x[0]), jogadoresData.get(x[1]));
			return herois;
		} else {
			PersonagemDto ganhadorDueloAnterior = partida.getDuelos().get(partida.getDuelos().size() - 1).getGanhador();
			List<PersonagemDto> herois = null;

			Integer x1 = null;
			boolean ok = true;

			while (ganhadorDueloAnterior.getId().equals(x1) || ok) {

				x1 = random.nextInt(jogadoresData.size());
				if (!ganhadorDueloAnterior.getAlinhamento().equals(jogadoresData.get(x1).getAlinhamento()))
					ok = false;
			}

			jogadoresData.get(x1).setId(x1);
			herois = Arrays.asList(ganhadorDueloAnterior, jogadoresData.get(x1));
			x[0] = ganhadorDueloAnterior.getId();
			x[1] = x1;
			return herois;
		}
	}

	public List<PersonagemDto> getVencedores() {

		List<PersonagemDto> herois = new ArrayList<PersonagemDto>();
		List<PersonagemDto> herois1 = new ArrayList<PersonagemDto>();
		String nomeAnt = " ";
		int cont = 0;
		for (DueloDto x : partida.getDuelos()) {
			for (PersonagemDto y : x.getPersonagens()) {
				herois.add(y);
			}
		}
		Collections.sort(herois);

		for (PersonagemDto p : herois) {
			if (!nomeAnt.equals(p.getNome())) {
				herois1.add(p);
				cont++;
			}
			if (cont > 1)
				break;
			nomeAnt = p.getNome();
		}
		return herois1;
	}

	public void novoDuelo() {

		partida = new PartidaDto();
		try {
			jogadoresData = new PersonagemUtil().carregaCSV();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PersonagemDto getMutante() {

		List<PersonagemDto> herois = getVencedores();
		PersonagemDto mutante = new PersonagemDto();
		List<String> keyPoderes = Arrays.asList("inteligencia", "forca", "defesa", "destreza", "poder", "combate");

		for (String k : keyPoderes) {
			if (herois.get(0).getHabilidades().get(k) > herois.get(1).getHabilidades().get(k)) {
				mutante.getHabilidades().put(k, herois.get(0).getHabilidades().get(k));
			} else {
				mutante.getHabilidades().put(k, herois.get(1).getHabilidades().get(k));
			}
		}
		mutante.setNome("MUTANTE");
		return mutante;
	}
}
