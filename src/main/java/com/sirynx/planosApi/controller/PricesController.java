package com.sirynx.planosApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sirynx.planosApi.model.Plans;
import com.sirynx.planosApi.model.Prices;
import com.sirynx.planosApi.repository.PlansRepository;
import com.sirynx.planosApi.repository.PricesRepository;

/*
 * Victória Belo
 * Controller para preços. 
 */
@RestController
@RequestMapping(value = "/api")
public class PricesController {
		
	@Autowired
	PricesRepository priceRepo;
	
	@Autowired
	PlansRepository pr;	
	
	/*
	 * Victória Belo
	 * Lista todos os valores
	 */
	@GetMapping(value = "/prices")
	public List<Prices> getPrices() {
		return priceRepo.findAll();
	}
	
	/*
	 * Victória Belo
	 * Cria preços para plano de saúde
	 */
	@PostMapping(value = "/plans/{plans_id}/prices")
	public ResponseEntity<Prices> setPrices(@RequestBody Prices valor, @PathVariable(value = "plans_id") long plans_id) {
		
		Plans plan = pr.findByCodigo(plans_id);	
		System.out.println("-> PREÇO SALVO: " + valor + " PARA O PLANO " +  plan);		
		valor.setPlans(plan);
		priceRepo.save(valor);		
		
		return new ResponseEntity<>(valor, HttpStatus.CREATED);
	}
	
	/*
	 * Victória Belo
	 * Exclui um determinado valor
	 */
	@DeleteMapping(name = "/prices/{prices_id}")
	public String deletePrices(@PathVariable (name="prices_id") long prices_id) {
		Prices price = priceRepo.findByCodigo(prices_id);
		priceRepo.delete(price);
		return "STATUS: VALOR EXCLUÍDO DA GRADE";
	}
}
