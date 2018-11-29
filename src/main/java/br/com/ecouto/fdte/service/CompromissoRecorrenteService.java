package br.com.ecouto.fdte.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecouto.fdte.model.CompromissoRecorrente;
import br.com.ecouto.fdte.model.Mensagem;
import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.repository.CompromissoRecorrenteRepository;
import br.com.ecouto.fdte.repository.VigenciaRepository;
import br.com.ecouto.fdte.utils.TipoMensagem;

@Service
public class CompromissoRecorrenteService {

	@Autowired
	VigenciaRepository vigenciaRepository;
	
	@Autowired
	CompromissoRecorrenteRepository compromissoRecorrenteRepository;
	
	public Mensagem gerarCompromissoRecorrente(Vigencia vigencia){
		Mensagem msg = new Mensagem();
		
		Calendar calendar = Calendar.getInstance();
		//Deve ter ao menos uma vigencia cadastrada
		Long qtdVigencia = vigenciaRepository.count();
		if(qtdVigencia == 0) {
			msg.setTipo(TipoMensagem.ERRO);
			msg.setMensagem("Não existe vigencia cadastrada. É necessário pelo menos um período de vigência.");
		}else {	
			//Se o processo de gerar compromisso diario foi executado isoladadamente
			//Retorna a ultima vigencia cadastrada
			if(vigencia == null) {
				vigencia = vigenciaRepository.findById(qtdVigencia);
			}
			//data de inicio d+1
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date dtInicio = calendar.getTime();
			
			//Gerar compromisso recorrente
		    gerar(dtInicio,vigencia);
		    msg.setTipo(TipoMensagem.SUCESSO);
			msg.setMensagem("Compromissos recorrentes gerados com sucesso");
		}
		
		return msg;
	}


	private void gerar(Date dtInicio, Vigencia vigencia) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		
		Long count = compromissoRecorrenteRepository.count();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtInicio);
		//Gerar compromissos para os proximos 30 dias
		for(int i = 0; i < 30; i++){
			if(cal.getTime().after(dtInicioVigencia) && cal.getTime().before(dtFinalVigencia)) {
				System.out.println("criando compromisso para:"+sdf.format(cal.getTime()));	
				CompromissoRecorrente compromissoRecorrente = new CompromissoRecorrente();
				compromissoRecorrente.setId(count);
				compromissoRecorrente.setDataCompromisso(cal.getTime());
				compromissoRecorrente.setHorarioInicio(vigencia.getHorarioInicio());
				compromissoRecorrente.setHorarioFinal(vigencia.getHorarioFinal());
				compromissoRecorrenteRepository.save(compromissoRecorrente);
				count++;
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} 
		
	}


	public void excluirTodosCompromissosDiarios() {
		
		compromissoRecorrenteRepository.deleteAll();
	}


	public List<CompromissoRecorrente> listarCompromissosDiario(Object object) {
		
		return compromissoRecorrenteRepository.findAll();
	}
}
