package com.sirynx.planosApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirynx.planosApi.model.Prices;
/*
 * Victoria Belo
 */
public interface PricesRepository extends JpaRepository<Prices, Long>{

	Prices findByCodigo(long prices_id);

}
