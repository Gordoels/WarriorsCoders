package com.stefanini.heroi.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que irá carregar todos os personagens
 * @author paulo
 *
 */
public class PersonagemDto implements Comparable<PersonagemDto>{

	private Integer id;
	private String nome;
	private String alinhamento;
	private Integer vitorias = 0;
	
	private Map<String,Integer> habilidades = new HashMap<String,Integer>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}
	public Integer getVitorias() {
		return vitorias;
	}
	public void setVitorias() {
		this.vitorias++;
	}
	
	public Map<String, Integer> getHabilidades() {
		return habilidades;
	}
	@JsonIgnore
	public Integer getDefesa() {
		return habilidades.get("defesa");
	}
	public void setDefesa(Integer defesa) {
		this.habilidades.put("defesa", defesa);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAlinhamento() {
		return alinhamento;
	}
	public void setAlinhamento(String alinhamento) {
		this.alinhamento = alinhamento;
	}
	@JsonIgnore
	public Integer getInteligencia() {
		return habilidades.get("inteligencia");
	}
	public void setInteligencia(Integer inteligencia) {
		this.habilidades.put("inteligencia", inteligencia);
	}
	@JsonIgnore
	public Integer getForca() {
		return habilidades.get("forca");
	}
	public void setForca(Integer forca) {
		this.habilidades.put("forca", forca);
	}
	@JsonIgnore
	public Integer getDestreza() {
		return habilidades.get("destreza");
	}
	public void setDestreza(Integer destreza) {
		this.habilidades.put("destreza", destreza);
	}
	@JsonIgnore
	public Integer getPoder() {
		return habilidades.get("poder");
	}
	public void setPoder(Integer poder) {
		this.habilidades.put("poder", poder);
	}
	@JsonIgnore
	public Integer getCombate() {
		return habilidades.get("combate");
	}
	public void setCombate(Integer combate) {
		this.habilidades.put("combate", combate);
	}
	
	@Override
	public int compareTo(PersonagemDto o) {
		
		return o.vitorias - vitorias;
	}
}