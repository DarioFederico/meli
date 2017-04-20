package com.meli.repository;

import java.io.IOException;
import java.util.Map;

import com.meli.entity.ServicioMeteorologico;

public class RepositoryTest {
	public static void main(String[] args) throws IOException{
		ServicioMeteorologico servicio = new ServicioMeteorologico();
		Map<Integer, String> pronostico = servicio.obtenerPronosticoExtendido(3650);
		ClimaRepository climaRepository = new ClimaRepository();
		
		for (Map.Entry<Integer, String> entry : pronostico.entrySet()) {
			climaRepository.guardarClima(entry.getKey(), entry.getValue());
		}
		
		climaRepository.cerrar();
		System.out.println("Presione una tecla para cerrar");
		System.in.read();
	}
}
