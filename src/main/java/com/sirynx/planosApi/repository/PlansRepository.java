package com.sirynx.planosApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirynx.planosApi.model.Plans;
import com.sirynx.planosApi.model.Prices;
/*
 * Victória Belo
 * [PARA MANUTENÇÃO EVOLUTIVA] Acrescentar paginação
 */
public interface PlansRepository extends JpaRepository<Plans, Long> {
	
	Plans findByCodigo(long codigo);
	
	List<Plans> findByPrices(Prices price);
	
	
}
