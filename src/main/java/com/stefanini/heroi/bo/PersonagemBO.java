package com.stefanini.heroi.bo;

import java.io.IOException;
import java.util.List;

import com.stefanini.heroi.dto.PersonagemDto;
import com.stefanini.heroi.util.BancoMemoriaUtil;

/**
 * Classe responsável pelos personagens
 * 
 * @author Gordoel
 *
 */

public class PersonagemBO {
	
	private static List<PersonagemDto> dadoHerois;

	public static List<PersonagemDto> getDadoHerois() {
		return dadoHerois;
	}
	
	public PersonagemBO() {
		try {
			dadoHerois = BancoMemoriaUtil.getInstance().carregaPersonagens();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<PersonagemDto> carregarPersonagem() throws IOException {

		return dadoHerois;
	}
	
}