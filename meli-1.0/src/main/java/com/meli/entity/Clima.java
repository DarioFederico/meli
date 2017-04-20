package com.meli.entity;

public class Clima {
	private int dia;
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	private String clima;
	
	public Clima(int dia, String clima){
		setDia(dia);
		setClima(clima);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Dia: ");
		sb.append(getDia());
		sb.append(" Clima: ");
		sb.append(getClima());
		return sb.toString();
	}
}
