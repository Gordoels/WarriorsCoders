package com.stefanini.heroi.dto;

import java.util.ArrayList;
import java.util.List;

public class PartidaDto {
	private List<DueloDto> duelos = new ArrayList<DueloDto>();
	private PersonagemDto mutante;

	public List<DueloDto> getDuelos() {
		return duelos;
	}

	public void setDuelos(DueloDto duelos) {
		this.duelos.add(duelos);
	}

	public PersonagemDto getmutante() {
		return mutante;
	}

	public void setMutante(PersonagemDto heroiQueMaisVenceu) {
		this.mutante = heroiQueMaisVenceu;
	}
}