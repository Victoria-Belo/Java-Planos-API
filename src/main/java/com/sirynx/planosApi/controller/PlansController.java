package com.sirynx.planosApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirynx.planosApi.model.Plans;
import com.sirynx.planosApi.repository.PlansRepository;
import com.sirynx.planosApi.repository.PricesRepository;

/*
 * Victória Belo
 * Controller para planos de saúde
 */
@RestController
@RequestMapping(value = "/api")
public class PlansController {
	
	@Autowired
	PlansRepository pr;
	
	/*
	 * Victória Belo
	 * Lista todos os planos
	 */
	@GetMapping(value = "/plans")
	public  List<Plans> getPlans(){		
		return pr.findAll();
	}
	
	/*
	 * Victória Belo
	 * Lista um determinado plano
	 */	
	@GetMapping(value = "/plans/{codigo}")
	public Plans getPlans(@PathVariable (name = "codigo") long codigo){		
		return pr.findByCodigo(codigo);
	}
	
	/*
	 * Victória Belo
	 * Cria planos 
	 */
	@PostMapping(value = "/plans")
	public String setPlans(@RequestBody Plans plano) {		
		pr.save(plano);
		return "PLANO " + plano.getNome() + " FOI CADASTRADO";
	}
	
	/*
	 * Victória Belo
	 * Exclui um determinado plano de saúde
	 */
	@DeleteMapping(value = "/plans/{id}")
	public Plans excludePlans(@PathVariable(name = "codigo") long codigo) {
		Plans plan = pr.findByCodigo(codigo);
		pr.delete(plan);
		return plan;		
	}
	
	
	

}
