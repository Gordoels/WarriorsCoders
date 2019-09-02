package com.stefanini.heroi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.heroi.bo.DueloBO;
import com.stefanini.heroi.bo.PersonagemBO;
import com.stefanini.heroi.dto.DueloDto;
import com.stefanini.heroi.dto.PersonagemDto;

@RestController
@RequestMapping(value = "/duelo")
public class DueloController {
	
	PersonagemBO personagemBo = new PersonagemBO();
	
	DueloBO dueloBo = new DueloBO();

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DueloDto randomHerois() throws IOException {
		return dueloBo.createDuelo();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vencedores", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonagemDto> getVencedores() throws IOException {
		
		return dueloBo.getVencedores();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/novo", produces = MediaType.APPLICATION_JSON_VALUE)
	public void novoDuelo() throws IOException {
		dueloBo.novoDuelo();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/mutante", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonagemDto getMutante() throws IOException {
		
		return dueloBo.getMutante();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/historico", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DueloDto> getHistorico() throws IOException {
		
		return dueloBo.getHistorico();
	}
	
}