package br.com.ecouto.fdte.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecouto.fdte.model.Mensagem;
import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.repository.VigenciaRepository;
import br.com.ecouto.fdte.utils.TipoMensagem;

@Service
public class CompromissoRecorrenteService {

	@Autowired
	VigenciaRepository vigenciaRepository;
	
	
	public Mensagem gerarCompromissoRecorrente(){
		Mensagem msg = new Mensagem();
		Calendar calendar = Calendar.getInstance();
		//Retorna a primeira vigencia cadastrada
		Vigencia vigencia = vigenciaRepository.findOne("1");
		if(vigencia == null) {
			msg.setTipo(TipoMensagem.ERRO);
			msg.setMensagem("Não existe vigencia cadastrada. É necessário pelo menos um período de vigência.");
		}
		
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		//data de inicio
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dtInicio = calendar.getTime();
		
		//Gerar compromisso recorrente
	    gerarCompromissos(dtInicio,dtInicioVigencia,dtFinalVigencia);
	    msg.setTipo(TipoMensagem.SUCESSO);
		msg.setMensagem("Compromissos recorrentes gerados com sucesso");
		return msg;
	}


	private void gerarCompromissos(Date dtInicio, Date dtInicioVigencia, Date dtFinalVigencia) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dtCorrente = dtInicio;
		//Gerar compromissos para os proximos 30 dias
		for(int i = 0; i < 30; i++){
			if(dtCorrente.after(dtInicio) && dtCorrente.before(dtFinalVigencia)) {
				System.out.println("criando compromisso para:"+sdf.format(dtInicio));
			}
		} 
		
	}
}
