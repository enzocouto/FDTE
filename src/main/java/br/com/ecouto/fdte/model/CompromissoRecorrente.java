package br.com.ecouto.fdte.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class CompromissoRecorrente {

	@Id
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCompromisso;
	
	@JsonFormat(pattern = "HH:mm")
	private Date horarioInicio;
	
	@JsonFormat(pattern = "HH:mm")
	private Date horarioFinal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataCompromisso() {
		return dataCompromisso;
	}
	public void setDataCompromisso(Date dataCompromisso) {
		this.dataCompromisso = dataCompromisso;
	}
	public Date getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public Date getHorarioFinal() {
		return horarioFinal;
	}
	public void setHorarioFinal(Date horarioFinal) {
		this.horarioFinal = horarioFinal;
	}
	
	
}
