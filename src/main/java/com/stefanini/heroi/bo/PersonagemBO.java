package com.stefanini.heroi.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stefanini.heroi.dto.DueloDto;
import com.stefanini.heroi.dto.PersonagemDto;
import com.stefanini.heroi.util.BancoMemoriaUtil;

/**
 * Classe responsável pelos personagens
 * 
 * @author Gordoel
 *
 */

public class PersonagemBO {
	
	private static List<PersonagemDto> jogadoresData;

	public static List<PersonagemDto> getJogadoresData() {
		return jogadoresData;
	}
	
	public PersonagemBO() {
		try {
			jogadoresData = BancoMemoriaUtil.getInstance().carregaPersonagens();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<PersonagemDto> carregarPersonagem() throws IOException {

		return jogadoresData;
	}
	
}