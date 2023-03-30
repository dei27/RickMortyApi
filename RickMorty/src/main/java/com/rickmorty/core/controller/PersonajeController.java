package com.rickmorty.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rickmorty.core.model.Personaje;
import com.rickmorty.core.services.PersonajeService;

@Controller
public class PersonajeController {

	@Autowired
	private PersonajeService personajeService;
	
	@PostMapping("/personajeTabla")
	  public ModelAndView getCharacterById(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView("page01");
		List<Personaje> personajes = personajeService.obtenerTodosLosPersonajes();
		System.out.println(id);
	    Personaje personaje  = personajeService.obtenerPersonajeById(id);
	    model.addObject("personajes", personajes);
	    model.addObject("personaje", personaje);
	    System.out.println(personaje.getName());
	    return model;
	  }	
	
	 @GetMapping("personajes")
	    public String obtenerTodosLosPersonajes(Model model) {
	        List<Personaje> personajes = personajeService.obtenerTodosLosPersonajes();
	        model.addAttribute("personajes", personajes);
	        return "listaPersonajes";
	 }
	
}
