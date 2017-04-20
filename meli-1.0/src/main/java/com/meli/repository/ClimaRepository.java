package com.meli.repository;

import com.meli.entity.Clima;

import redis.clients.jedis.Jedis;

public class ClimaRepository {
	private final Jedis jedis;
	
	public ClimaRepository(){
		jedis = new Jedis("redis-12368.c9.us-east-1-2.ec2.cloud.redislabs.com", 12368);
		jedis.auth("d@0");
	}
	
	public Clima obtenerClimaPorDia(int dia){
		String clima = jedis.get(String.valueOf(dia));
		jedis.disconnect();
		return new Clima(dia, clima);
	}
	
	public void guardarClima(int dia, String clima){
		jedis.set(String.valueOf(dia), clima);
	}
	
	public void cerrar(){
		jedis.disconnect();
	}
}
