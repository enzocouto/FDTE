package br.com.ecouto.fdte.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class Vigencia {

	@Id
	private Long id;
	@NotNull
	private Date dtInicioVigencia;
	@NotNull
	private Date dtFinalVigencia;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull
	private Date horarioInicio;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull
	private Date horarioFinal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}
	
	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}
	public Date getDtFinalVigencia() {
		return dtFinalVigencia;
	}
	
	public void setDtFinalVigencia(Date dtFinalVigencia) {
		this.dtFinalVigencia = dtFinalVigencia;
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
