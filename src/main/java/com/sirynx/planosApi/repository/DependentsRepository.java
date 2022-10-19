package com.sirynx.planosApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirynx.planosApi.model.Dependents;
/*
 * Victória Belo
 */
public interface DependentsRepository extends JpaRepository<Dependents, Long> {
	
	Dependents findByBeneficiaries(long beneficiaries_id);

	Dependents findByCodigo(long dependents_id);
}
