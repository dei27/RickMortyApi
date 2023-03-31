package com.rickmorty.core.controller;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rickmorty.core.model.Personaje;
import com.rickmorty.core.services.PersonajeService;

@Controller
public class JuegosController {

	@Autowired
	private PersonajeService personajeService;

	private Random random = new Random();

	private int numeroAleatorio;

	public static int vidas = 3;
	public static int puntosObtenidos = 0;

	@GetMapping("/juego01")
	public ModelAndView juego01() {
		ModelAndView model = new ModelAndView("juego01");
		Set<Integer> numeros = crearNumeros();
		
		for (int i = 0; i < 4; i++) {
			int numero = (int) numeros.toArray()[i];
			Personaje personaje = personajeService.obtenerPersonajeById(numero);
			model.addObject("personaje" + i, personaje);
		}

		numeroAleatorio = random.nextInt(4);
		model.addObject("numRand", numeroAleatorio);
		model.addObject("vidas", vidas);
		return model;
	}

	@GetMapping("/juego01/next")
	public ModelAndView juego01Next(@RequestParam("imagen") String imagen, @RequestParam("respuesta") String respuesta) {
		ModelAndView model = new ModelAndView("redirect:/juego01");
		if (imagen.equals(respuesta)) {
			puntosObtenidos++;
			System.out.println(puntosObtenidos);
		} else {
			vidas--;
			System.out.println(puntosObtenidos);
		}
		
		if (vidas==0) {
			return new ModelAndView("redirect:/juego01/gameOver");
		}
		model.addObject("vidas", vidas);
		return model;
	}

	@GetMapping("/juego01/gameOver")
	public ModelAndView juego01GameOver() {
		ModelAndView model = new ModelAndView("gameOver");
		model.addObject("puntos",puntosObtenidos);
		vidas=3;
		puntosObtenidos=0;
		return model;
	}
	
	@GetMapping("/juego02")
	public ModelAndView juego02() {
		ModelAndView model = new ModelAndView("juego02");
		return model;
	}

	@GetMapping("/juego03")
	public ModelAndView juego03() {
		ModelAndView model = new ModelAndView("juego03");
		return model;
	}

	public Set<Integer> crearNumeros() {
		Set<Integer> miSet = new HashSet<Integer>();

		while (miSet.size() < 4) {
			numeroAleatorio = random.nextInt(826) + 1;
			miSet.add(numeroAleatorio);
		}

		return miSet;
	}

}
