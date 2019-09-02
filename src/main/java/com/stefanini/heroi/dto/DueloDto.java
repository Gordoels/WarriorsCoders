package com.stefanini.heroi.dto;

import java.util.ArrayList;
import java.util.List;

public class DueloDto {

	private List<PersonagemDto> personagens = new ArrayList<PersonagemDto>();
	private PersonagemDto vitorioso;
	private String forca;

	public PersonagemDto getVitorioso() {
		return vitorioso;
	}

	public DueloDto(List<PersonagemDto> personagens) {
		this.personagens = personagens;
	}

	public List<PersonagemDto> getPersonagens() {
		return personagens;
	}

	public void setForca(String forca) {
		this.forca = forca;
	}

	public String getForca() {
		return forca;
	}

	public void setPersonagens(List<PersonagemDto> personagens) {
		this.personagens = personagens;
	}

	public PersonagemDto vitorioso() {
		return this.vitorioso;
	}

	public void setVitorioso(PersonagemDto vitorioso) {
		this.vitorioso = vitorioso;
	}
}