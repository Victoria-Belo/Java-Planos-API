package com.sirynx.planosApi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sirynx.planosApi.model.Beneficiaries;
import com.sirynx.planosApi.model.Plans;
/*
 * Victória Belo
 */
public interface BeneficiariesRepository extends JpaRepository<Beneficiaries, Long> {
	
	Plans findByPlans(long codigo);

	Beneficiaries findByCodigo(long codigo);	

}
