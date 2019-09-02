package com.stefanini.heroi.util;

import java.io.IOException;
import java.util.List;

import com.stefanini.heroi.dto.PersonagemDto;
import com.stefanini.heroi.dto.PoderesDto;

public class BancoMemoriaUtil {
	
	private static List<PoderesDto> poderesDtos;	
	private static List<PersonagemDto> personagemDtos;

	
	public static BancoMemoriaUtil getInstance(){
		return new BancoMemoriaUtil();
	}
	
	public List<PersonagemDto> carregaPersonagens() throws IOException{
		if(personagemDtos == null)
			personagemDtos = new PersonagemUtil().carregaCSV();
		
		return personagemDtos;
	}
	
}