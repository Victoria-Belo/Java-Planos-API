package com.sirynx.planosApi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Victoria Belo
 */

@Entity
@Table(name = "plans")
public class Plans implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;	
	
	private String registro;	
	
	private String nome;		
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plans", cascade = CascadeType.ALL)
	private List<Prices> prices;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plans", cascade = CascadeType.ALL)
	private List<Beneficiaries> beneficiaries;
	
	public Plans() {}
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Prices> getPrices() {
		return prices;
	}

	public void setPrices(List<Prices> prices) {
		this.prices = prices;
	}
	
	public List<Beneficiaries> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Beneficiaries> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	@Override
	public String toString() {
		return "Plans [codigo=" + codigo + ", registro=" + registro + ", nome=" + nome + ", prices=" + prices + "]";
	}

	
}
