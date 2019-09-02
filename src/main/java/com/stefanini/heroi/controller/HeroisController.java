package com.stefanini.heroi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.heroi.bo.DueloBO;
import com.stefanini.heroi.bo.PersonagemBO;
import com.stefanini.heroi.dto.DueloDto;
import com.stefanini.heroi.dto.PersonagemDto;

/**
 * Classe adicionar face
 * @author c1297467
 *
 */

@RestController
@RequestMapping(value="/herois")
public class HeroisController {

	public PersonagemBO personagemBo = new PersonagemBO();
	public DueloBO dueloBo = new DueloBO();
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PersonagemDto> carregaHeroi() throws IOException {
		return personagemBo.carregarPersonagem();
	}
	
}