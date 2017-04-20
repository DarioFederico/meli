package com.meli.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.meli.entity.Clima;
import com.meli.repository.ClimaRepository;

@SuppressWarnings("serial")
public class ClimaAPI extends HttpServlet {
	
	private final ClimaRepository climaRepository;
	
	public ClimaAPI() {
		// TODO Auto-generated constructor stub
		climaRepository = new ClimaRepository();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	      
	  String msg;
	  String parametro = request.getParameter("dia");
	  
	  try {
		  int dia = Integer.parseInt(parametro);
		  Clima clima = climaRepository.obtenerClimaPorDia(dia);
		  msg = new Gson().toJson(clima);
	  } 
	  catch (NumberFormatException e) {
		  msg = "Parametro inválido";
	  }
	  
	  response.setCharacterEncoding("UTF-8");  
	  response.setContentType("application/json");
	  response.getWriter().println(msg);
	
	}
}
