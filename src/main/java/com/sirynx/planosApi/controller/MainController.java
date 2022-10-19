package com.sirynx.planosApi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sirynx.planosApi.model.Beneficiaries;
import com.sirynx.planosApi.model.Dependents;
import com.sirynx.planosApi.model.Plans;
import com.sirynx.planosApi.model.Prices;
import com.sirynx.planosApi.repository.BeneficiariesRepository;
import com.sirynx.planosApi.repository.DependentsRepository;
import com.sirynx.planosApi.repository.PlansRepository;
import com.sirynx.planosApi.repository.PricesRepository;
/*
 * Victória Belo
 * Beneficiário principal(responsável) + dependentes
 */
@RestController
@RequestMapping(value = "/api")
public class MainController {
	
	@Autowired
	BeneficiariesRepository br;
	
	@Autowired
	PlansRepository pr;
	
	@Autowired
	DependentsRepository dr;
	
	@Autowired
	PricesRepository priceRepo;
	
	/*
	 *Victória Belo 
	 */	
	@GetMapping(value = "/beneficiarios")	
	public List<Beneficiaries> getBeneficiarios() {			
		return br.findAll();
	}	
	
	/*cadastrar dependentes [ok]
	atualizar a qtd de beneficiados dinamicamente	[ok]
	settar faixa de plano [ok]
	encontrar id do plano escolhido 	[ok]
	pegar preços registrados do Plano	[ok]
	verificar faixa por idade [ok]
	minimo de vidas backend definir qual se enquandra dentro do plano [ok] */
	
	
	/*
	 *Victória Belo 
	 *Identifica se lista dos dependentes está desatualizada e corrige
	 */	
	public int verificaLista(long codigo) { 		
		Beneficiaries principal = br.findByCodigo(codigo);
		int tam = principal.getDependents().size();
		System.out.println(tam);
		if (tam == 0)
			{ principal.setQtd_beneficiario(tam); 
			  principal.setDependents(null);
			  br.save(principal);
			}	
		return 0;		
	}	
	
	/*
	 *Victória Belo 
	 *Busca e aplica descontos no plano para beneficiário caso cumpra requisitos mínimos  
	 */	
	@GetMapping(value = "/beneficiarios/{plans_id}/{beneficiaries_id}/status_desconto")	
	public  ResponseEntity<Beneficiaries>  verificarMinimoVidas(@PathVariable long plans_id, @PathVariable long beneficiaries_id) {
		/*buscar qual plano se trata [ok]
		buscar  beneficiario principal [ok]
		vasculhar em busca de minimo_vida [ok]
		resgatar variavel de minimo_vidas [ok]
		comparar com a lista do beneficiente em questão, ou seja, preciso do codigo [ok] */
		
		boolean flag = false;
		Plans plano = pr.findByCodigo(plans_id);
		Beneficiaries principal = br.findByCodigo(beneficiaries_id);
		List<Dependents> dependentes = principal.getDependents();
		
		int index = 0;
		int minimoVidas = 0;
		int total = principal.getQtd_beneficiario();
		for(int i =0; i <  plano.getPrices().size(); i++) {
			minimoVidas = plano.getPrices().get(i).getMinimo_vidas();		
			//System.out.println("---> MINIMO DA VEZ " + minimoVidas + "\n QTD DE PESSOAS :" + total + "\n QTS OPÇOES POSSUI ESSE PLANO " +  plano.getPrices().size());
			System.out.println( "total " + total + " >=  minimoVidas " + minimoVidas );
			if (total >= minimoVidas) {					
				long planoDetectado = plano.getPrices().get(i).getCodigo(); //pegando codigo correto
				System.out.println("-> DETECTEI ALGO MELHOR NO CODIGO " + planoDetectado);
				principal.setPlans(plano);
				flag = true;
				index = i;
			
			}else System.out.println("Buscando..."); 	//end-if			
		}//end-for
		
		if(flag != false) { //se for detectado promoção dentro do plano escolhido			
			//verificando novos termos para alocação de faixa do responsável beneficiario
			if (principal.getIdade() <= 17) {
				System.out.println("-> FAIXA 1 " + plano.getPrices().get(index).getFaixa1());	
				principal.setFaixa(plano.getPrices().get(index).getFaixa1());				
				
			}if (principal.getIdade() > 40) {
				System.out.println("-> FAIXA 3 " + plano.getPrices().get(index).getFaixa3());
				principal.setFaixa(plano.getPrices().get(index).getFaixa3());				
			
			}if(principal.getIdade() >= 18 && (principal.getIdade() <= 40)) {
				System.out.println("-> FAIXA 2 " + plano.getPrices().get(index).getFaixa2());	
				principal.setFaixa(plano.getPrices().get(index).getFaixa2());
				
			}
			principal.setPlans(plano);				//end-else
			br.save(principal); //salvando atualização de faixa para principal
			//verificando novas alocações para dependentes
			for (int i = 0; i < principal.getDependents().size(); i++ ) {
				
				System.out.println("| -> " + principal.getDependents().get(i) );
				if (dependentes.get(i).getDependente_idade() <= 17) {					
					System.out.println("-> FAIXA 1 " + plano.getPrices().get(index).getFaixa1());	
					dependentes.get(i).setFaixa(plano.getPrices().get(index).getFaixa1());				
					
				}
				if(dependentes.get(i).getDependente_idade() > 40) {
					System.out.println("-> FAIXA 3 " + plano.getPrices().get(index).getFaixa3());
					dependentes.get(i).setFaixa(plano.getPrices().get(index).getFaixa3());	
				
				}
				if((dependentes.get(i).getDependente_idade() >= 18 && dependentes.get(i).getDependente_idade() <= 40)){
					System.out.println("-> FAIXA 2 " + plano.getPrices().get(index).getFaixa2());	
					dependentes.get(i).setFaixa(plano.getPrices().get(index).getFaixa2());					
					
				}//end-if	
				dr.save(dependentes.get(i));
				
			}//end-for		
			
	    }else {
	    	return new ResponseEntity<Beneficiaries>(principal, HttpStatus.BAD_REQUEST); 			
		}		
		
		return new ResponseEntity<Beneficiaries>(principal, HttpStatus.ACCEPTED); 
	}
	
	/*
	 *Victória Belo 
	 *Retorna lista dos clientes que os planos atendem
	 */	
	@GetMapping(value = "/beneficiarios/{codigo}")	
	public Beneficiaries getBeneficiado(@PathVariable(name = "codigo") long codigo) {
		Beneficiaries principal = br.findByCodigo(codigo);
		verificaLista(codigo); //verificando se existe incoerencias		
		return principal;
	}
	
	/*
	 *Victória Belo 
	 * Cria Beneficiario principal
	 * [EM MANUTENÇÃO EVOLUTIVA]
	 */	
	@PostMapping(value="/beneficiarios/plans/{plans_id}") 
	public ResponseEntity<Beneficiaries> setBeneficiados(@RequestBody Beneficiaries beneficiado, @PathVariable(name = "plans_id") long plans_id) {
		int tam = beneficiado.getQtd_beneficiario();		
		System.out.println("---> cliente " + beneficiado.getNome() + " " +  tam);		
		Plans plano = pr.findByCodigo(plans_id);	
		
		if (tam <= 0) { //se não houver principal
			return new ResponseEntity<Beneficiaries>(beneficiado,HttpStatus.BAD_REQUEST);
		}else {		                                                                                              
			if(tam == 1) {
				if (beneficiado.getIdade() <= 17) {
					System.out.println("-> FAIXA 1 " + plano.getPrices().get(0).getFaixa1());	
					beneficiado.setFaixa(plano.getPrices().get(0).getFaixa1());
					beneficiado.setPlans(plano);
					
				}else {
					System.out.println("-> FAIXA 2 " + plano.getPrices().get(0).getFaixa2());	
					beneficiado.setFaixa(plano.getPrices().get(0).getFaixa2());
					beneficiado.setPlans(plano);
				}
				if (beneficiado.getIdade() > 40) {
					System.out.println("-> FAIXA 3 " + plano.getPrices().get(0).getFaixa3());
					beneficiado.setFaixa(plano.getPrices().get(0).getFaixa3());
					beneficiado.setPlans(plano);				
				}				
				br.save(beneficiado);	
				System.out.println(beneficiado.toString());
					
				}				
			}			
			return new ResponseEntity<Beneficiaries>(beneficiado,HttpStatus.CREATED);
		}	
	
	/*
	 * Victória Belo
	 * Excluindo responsável pelo plano + dependentes
	 */
	@DeleteMapping(value = "/beneficiarios/{beneficiaries_id}")
	public String deleteBeneficiario(@PathVariable (name = "beneficiaries_id") long beneficiaries_id  ) {
		Beneficiaries principal = br.findByCodigo(beneficiaries_id);		
		br.delete(principal);
		return "STATUS: CLIENTE EXCLUÍDO";
	}
	
	/* ============================<<< DEPENDENTES >>>================================== 
	/*
	 *Victória Belo 
	 *Lista todos os dependentes 
	 */	
	@GetMapping(value="/beneficiarios/dependentes")
	public List<Dependents> getDependentes() {
		return dr.findAll();		
	}	
	
	/*
	 *Victória Belo 
	 *Cria dependentes de forma dinamica para um determinado beneficiario
	 */	
	@PostMapping(value = "/beneficiarios/{beneficiaries_id}/dependente")
	public ResponseEntity<Dependents> setDependentes(@RequestBody Dependents dependente,@PathVariable(name = "beneficiaries_id") long beneficiaries_id) {
		
		Beneficiaries principal = br.findByCodigo(beneficiaries_id);		
		try {			
			if (dependente.getDependente_idade() <= 17) {
				System.out.println("-> FAIXA 1 " );		
				dependente.setFaixa(principal.getPlans().getPrices().get(0).getFaixa1());
				
			}else if (dependente.getDependente_idade() > 40) {
				System.out.println("-> FAIXA 3 " );			
				dependente.setFaixa(principal.getPlans().getPrices().get(0).getFaixa3());

			}else {
				System.out.println("-> FAIXA 2 " );					
				dependente.setFaixa(principal.getPlans().getPrices().get(0).getFaixa2());
			}
			
			dependente.setBeneficiaries(principal);	
			
			int tam = principal.getDependents().size() + 2;
			principal.setQtd_beneficiario(tam); // + 1 = para beneficiado principal | + 1 para getDependents.size que começa no 0;
			
			dr.save(dependente);
			
		} catch (Exception e) {			
			System.out.println("[ATENÇÃO]	ERRO ENCONTRADO " + e);
			return new ResponseEntity<Dependents>(
					dependente, HttpStatus.BAD_REQUEST);
		}	
		System.out.println(dependente.toString()); //LEMBRETE: evitar colocar informação do beneficiario pois causa loop infinito
		return new ResponseEntity<Dependents>(dependente, HttpStatus.CREATED);
	}	
	
	/*
	 *Victória Belo 
	 *Exclui todos os dependentes de um determinado beneficiario principal
	 */	
	@DeleteMapping(value = "/beneficiarios/{beneficiaries_id}/dependente/{dependents_id}")
	public String deleteDependente (@PathVariable(name ="beneficiaries_id") long  beneficiaries_id, @PathVariable(name = "dependents_id") long dependents_id ){
	
		Dependents dependente = dr.findByCodigo(dependents_id);
		Beneficiaries principal = br.findByCodigo(beneficiaries_id);
		dr.delete(dependente);
		br.save(principal);
		return "STATUS: DEPENDENTE EXCLUÍDO";
	}	

}//end class main 
