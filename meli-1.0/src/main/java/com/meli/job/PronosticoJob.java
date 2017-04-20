package com.meli.job;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.meli.entity.ServicioMeteorologico;
import com.meli.repository.ClimaRepository;

public class PronosticoJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		ServicioMeteorologico servicio = new ServicioMeteorologico();
		Map<Integer, String> pronostico = servicio.obtenerPronosticoExtendido(3650);
		ClimaRepository climaRepository = new ClimaRepository();
		
		for (Map.Entry<Integer, String> entry : pronostico.entrySet()) {
			climaRepository.guardarClima(entry.getKey(), entry.getValue());
		}
		
		climaRepository.cerrar();
	}
}
