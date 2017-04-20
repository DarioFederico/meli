package com.meli.entity;

import java.awt.geom.Point2D;
import java.lang.Math;

public class Planeta {
	private String nombre;
	private int movimiento;
	private short distanciaAlSol;
	private int dia;
	private Point2D vector;
	
	public String getNombre() {
		return nombre;
	}
	
	public short getDistancia(){
		return this.distanciaAlSol;
	}
	
	public int getMovimiento() {
		return movimiento;
	}
	
	public Point2D getVector(){
		 return vector;
	}
	public void setDia(int dia){
		this.dia = dia;
		this.setCoordenadaCartesiana();
	}
	
	public Planeta(String nombre, int movimiento, short distanciaAlSol){
		this.nombre = nombre;
		this.movimiento = movimiento;
		this.distanciaAlSol = distanciaAlSol;
		this.vector = new Point2D.Double();
	}
	
	public int getPosicionPolar(){
		return dia * movimiento;
	}
	
	private void setCoordenadaCartesiana(){
		double posicionPolar = Math.toRadians(getPosicionPolar());
		double x = (double) (distanciaAlSol * Math.cos(posicionPolar));
		double y = (double) (distanciaAlSol * Math.sin(posicionPolar));
		this.vector.setLocation(x, y);
	}
	
	@Override
	public String toString(){
		return String.format("Planeta %s. Posicion %s grados. Coordenadas %.2f, %.2f", this.nombre, getPosicionPolar(), getVector().getX(), getVector().getY());
	}
}
