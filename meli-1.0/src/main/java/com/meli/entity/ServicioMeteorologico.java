package com.meli.entity;


import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class ServicioMeteorologico {
	
	private Planeta vulcano;
	private Planeta betasoides;
	private Planeta feregis;
	private Point2D vectorSol;
	
	public ServicioMeteorologico(){
		this.vulcano = new Planeta("Vulcano", -1, (short) 5);
		this.feregis = new Planeta("Feregis", -3, (short) 20);
		this.betasoides = new Planeta("Betasoides", 5, (short) 10);
		this.vectorSol = new Point2D.Double(0, 0);
	}
	
	public Map<Integer, String> obtenerPronosticoExtendido(int dias){
		int dia = 1;
		Map<Integer, String> pronostico = new HashMap<Integer, String>();
		while(dia <= dias){
			String clima = getPronosticoDelDia(dia);
			pronostico.put(dia, clima);
			dia++;
		}
		
		return pronostico;
	}
	
	private String getPronosticoDelDia(int dia){
		//Verifica si los puntos cartesianos de los planetas pertenecen a una misma recta
		this.vulcano.setDia(dia);
		this.feregis.setDia(dia);
		this.betasoides.setDia(dia);
		
		
		double pendienteAB = getCalculoDePendiente(betasoides.getVector(), vulcano.getVector());
		double pendienteBC = getCalculoDePendiente(feregis.getVector(), betasoides.getVector());
		
		if(pendienteAB == pendienteBC){
			//De pertenecer a la misma recta, se verifica si pertenecen al origen de coordenadas (sol)
			double pendienteOrigen = getCalculoDePendiente(vulcano.getVector(), this.vectorSol);
			if(pendienteOrigen == pendienteAB)
				return "Sequía";
			return "Optimas condiciones de clima";
		}
		else{
			//Al no estar alineados, buscamos si el sol se encuentra dentro del triangulo formado por los 3 planetas
			if(puntoDentroDelTriangulo(vectorSol, vulcano.getVector(), betasoides.getVector(), feregis.getVector()))
				return "Lluvias";
		}
		
		return "Clima indefinido";
	}
	
	private double getCalculoDePendiente(Point2D puntoA, Point2D puntoB){
		double dx = puntoB.getX() - puntoA.getX();
		double dy = puntoB.getY() - puntoA.getY();
		return (dx != 0) ? (dy / dx) : Double.POSITIVE_INFINITY;
	}
	
	private double getProductoEscalar(Point2D p1, Point2D p2, Point2D p3)
	{
	    return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}

	private Boolean puntoDentroDelTriangulo(Point2D p, Point2D v1, Point2D v2, Point2D v3)
	{
		Boolean b1, b2, b3;

	    b1 = getProductoEscalar(p, v1, v2) < 0.0f;
	    b2 = getProductoEscalar(p, v2, v3) < 0.0f;
	    b3 = getProductoEscalar(p, v3, v1) < 0.0f;

	    return ((b1 == b2) && (b2 == b3));
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(vulcano.toString());
		sb.append("\r");
		sb.append(betasoides.toString());
		sb.append("\r");
		sb.append(feregis.toString());
		return sb.toString();
	}
}
