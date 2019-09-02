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
	private Integer numero[] = new Integer[2]; //2 habilidades
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
		if (dadoHerois.get(numero[0]).getHabilidades().get(habilidadeEscolhida) > dadoHerois.get(numero[1]).getHabilidades().get(habilidadeEscolhida)) {

			dadoHerois.get(numero[0]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(numero[0]).getHabilidades().get(habilidadeEscolhida) + 2);
			dadoHerois.get(numero[1]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(numero[1]).getHabilidades().get(habilidadeEscolhida) - 2);

			vitorioso = dadoHerois.get(numero[0]);
		} 
		else{

			dadoHerois.get(numero[0]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(numero[0]).getHabilidades().get(habilidadeEscolhida) - 2);
			dadoHerois.get(numero[1]).getHabilidades().replace(habilidadeEscolhida,dadoHerois.get(numero[1]).getHabilidades().get(habilidadeEscolhida) + 2);

			vitorioso = dadoHerois.get(numero[1]);
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

			boolean valida = true;

			while (numero[0] == numero[1] || valida) { //garantindo que não haverá empates
				numero[0] = rm.nextInt(dadoHerois.size());
				numero[1] = rm.nextInt(dadoHerois.size());

				if (!dadoHerois.get(numero[0]).getAlinhamento().equals(dadoHerois.get(numero[1]).getAlinhamento())) //garantindo que são de alinhamento diferentes
					valida = false;
			}
			dadoHerois.get(numero[0]).setId(numero[0]);
			dadoHerois.get(numero[1]).setId(numero[1]);
			herois = Arrays.asList(dadoHerois.get(numero[0]), dadoHerois.get(numero[1]));
			return herois;
		} 
		else {
			PersonagemDto vitoriosoBatalhaAnterior = partida.getDuelos().get(partida.getDuelos().size() - 1).getVitorioso();
			List<PersonagemDto> herois = null;

			Integer n = null;
			boolean valida = true;

			while (vitoriosoBatalhaAnterior.getId().equals(n) || valida) {

				n = rm.nextInt(dadoHerois.size()); //valor do 'index'
				if (!vitoriosoBatalhaAnterior.getAlinhamento().equals(dadoHerois.get(n).getAlinhamento()))
					valida = false;
			}

			dadoHerois.get(n).setId(n);
			herois = Arrays.asList(vitoriosoBatalhaAnterior, dadoHerois.get(n));
			numero[0] = vitoriosoBatalhaAnterior.getId();
			numero[1] = n;
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

	public PersonagemDto getMutante() { //setando o mutante

		List<PersonagemDto> herois = getVencedores();
		PersonagemDto mutante = new PersonagemDto();
		List<String> keyPoderes = Arrays.asList("inteligencia", "forca", "defesa", "destreza", "poder", "combate");

		for (String poder : keyPoderes) {
			if (herois.get(0).getHabilidades().get(poder) > herois.get(1).getHabilidades().get(poder)) {
				mutante.getHabilidades().put(poder, herois.get(0).getHabilidades().get(poder));
			} else {
				mutante.getHabilidades().put(poder, herois.get(1).getHabilidades().get(poder));
			}
		}
		mutante.setNome("P1k das galacta");
		return mutante;
	}
}
