package com.sirynx.planosApi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Victoria Belo
 */

@Entity
@Table(name="dependents")
public class Dependents implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	private String dependente_nome;
	
	private int dependente_idade;	
	
	private double faixa;
	
	@ManyToOne
	@JoinColumn(name="beneficiaries_id")
	@JsonIgnore
	private Beneficiaries beneficiaries;	
	
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDependente_nome() {
		return dependente_nome;
	}

	public void setDependente_nome(String dependente_nome) {
		this.dependente_nome = dependente_nome;
	}
	public int getDependente_idade() {
		return dependente_idade;
	}

	public void setDependente_idade(int dependente_idade) {
		this.dependente_idade = dependente_idade;
	}

	public Beneficiaries getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(Beneficiaries beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	

	public double getFaixa() {
		return faixa;
	}

	public void setFaixa(double faixa) {
		this.faixa = faixa;
	}

	@Override
	public String toString() {
		return " codigo=" + codigo + ", dependente_nome=" + dependente_nome + ", dependente_idade="
				+ dependente_idade;
	}	
	
}
