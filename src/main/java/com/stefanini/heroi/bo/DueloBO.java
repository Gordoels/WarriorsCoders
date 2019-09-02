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
	private List<PersonagemDto> dadoHerois;
	private Integer x[] = new Integer[2]; //2 habilidades
	private List<DueloDto> historicoBatalhas = new ArrayList<DueloDto>();
	Random rm = new Random();
	Integer nPartidas = 0;

	public DueloBO() {

		partida = new PartidaDto();
		dadoHerois = PersonagemBO.getDadoHerois();
	}

	public DueloDto createDuelo() {

		List<String> skills = Arrays.asList("inteligencia", "forca", "defesa", "destreza", "poder", "combate");
		String habilidadeEscolhida = skills.get(new Random().nextInt(skills.size())); // randomizando a habilidade a ser
																						// escolhida
		PersonagemDto vitorioso;

		List<PersonagemDto> herois = heroiRandom(); // instanciando lista de herois aleatórios

		DueloDto duelo = new DueloDto(herois);

		//incrementando e decrementando
		if (dadoHerois.get(x[0]).getHabilidades().get(habilidadeEscolhida) > dadoHerois.get(x[1]).getHabilidades().get(habilidadeEscolhida)) {

			dadoHerois.get(x[0]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(x[0]).getHabilidades().get(habilidadeEscolhida) + 2);
			dadoHerois.get(x[1]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(x[1]).getHabilidades().get(habilidadeEscolhida) - 2);

			vitorioso = dadoHerois.get(x[0]);
		} 
		else{

			dadoHerois.get(x[0]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(x[0]).getHabilidades().get(habilidadeEscolhida) - 2);
			dadoHerois.get(x[1]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(x[1]).getHabilidades().get(habilidadeEscolhida) + 2);

			vitorioso = dadoHerois.get(x[1]);
		}
		vitorioso.setVitorias();

		duelo.setVitorioso(vitorioso);
		duelo.setForca(habilidadeEscolhida);
		historicoBatalhas.add(duelo);
		partida.setDuelos(duelo);

		nPartidas++;
		return duelo;
	}

	public List<DueloDto> getHistorico() {

		return historicoBatalhas;
	}

	private List<PersonagemDto> heroiRandom() {

		if (partida.getDuelos().isEmpty()) {
			List<PersonagemDto> herois = null;

			boolean ok = true;

			while (x[0] == x[1] || ok) { //garantindo que não haverá empates
				x[0] = rm.nextInt(dadoHerois.size());
				x[1] = rm.nextInt(dadoHerois.size());

				if (!dadoHerois.get(x[0]).getAlinhamento().equals(dadoHerois.get(x[1]).getAlinhamento())) //garantindo que são de alinhamento diferentes
					ok = false;
			}
			dadoHerois.get(x[0]).setId(x[0]);
			dadoHerois.get(x[1]).setId(x[1]);
			herois = Arrays.asList(dadoHerois.get(x[0]), dadoHerois.get(x[1]));
			return herois;
		} 
		else {
			PersonagemDto ganhadorBatalhaAnterior = partida.getDuelos().get(partida.getDuelos().size() - 1).getVitorioso();
			List<PersonagemDto> herois = null;

			Integer x1 = null;
			boolean ok = true;

			while (ganhadorBatalhaAnterior.getId().equals(x1) || ok) {

				x1 = rm.nextInt(dadoHerois.size());
				if (!ganhadorBatalhaAnterior.getAlinhamento().equals(dadoHerois.get(x1).getAlinhamento()))
					ok = false;
			}

			dadoHerois.get(x1).setId(x1);
			herois = Arrays.asList(ganhadorBatalhaAnterior, dadoHerois.get(x1));
			x[0] = ganhadorBatalhaAnterior.getId();
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
			dadoHerois = new PersonagemUtil().carregaCSV();
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
