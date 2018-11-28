package br.com.ecouto.fdte.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.repository.VigenciaRepository;

@Service
public class CompromissoRecorrenteService {

	@Autowired
	VigenciaRepository vigenciaRepository;
	
	
	public String gerarCompromissoRecorrente(){
		String msg = "";
		
		Calendar calendar = Calendar.getInstance();
		//Retorna a primeira vigencia cadastrada
		Vigencia vigencia = vigenciaRepository.findOne("1");
		if(vigencia == null) {
			msg = "Não existe vigencia cadastrada. É necessário pelo menos um período de vigência.";
		}
		
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		//data de inicio
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dtInicio = calendar.getTime();
		
		//Gerar compromisso recorrente
	    gerarCompromissos(dtInicio,dtInicioVigencia,dtFinalVigencia);
		msg = "Compromissos recorrentes gerados com sucesso";
		return msg;
	}


	private void gerarCompromissos(Date dtInicio, Date dtInicioVigencia, Date dtFinalVigencia) {
		// TODO Auto-generated method stub
		
	}
}
