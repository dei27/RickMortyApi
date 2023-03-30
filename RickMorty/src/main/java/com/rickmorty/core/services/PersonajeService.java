package com.rickmorty.core.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.rickmorty.core.model.Personaje;

@Service
public class PersonajeService {

	private WebClient webClient;

	public PersonajeService() {
		this.webClient = WebClient.builder().baseUrl("https://rickandmortyapi.com/api/").build();
	}

	public Personaje obtenerPersonajeById(int id) {
		try {
			return this.webClient.get().uri("/character/{id}", id).retrieve().bodyToMono(Personaje.class).block();
		} catch (WebClientResponseException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Personaje> obtenerTodosLosPersonajes() {
	    try {
	        return this.webClient.get().uri("/character").retrieve().bodyToMono(ResultadoPersonajes.class)
	            .block().getResults();
	    } catch (WebClientResponseException ex) {
	    	ex.printStackTrace();
	        return null;
	    }
	}
	
	public static class ResultadoPersonajes {
	    private List<Personaje> results;

	    public List<Personaje> getResults() {
	        return results;
	    }

	    public void setResults(List<Personaje> results) {
	        this.results = results;
	    }
	}

}


