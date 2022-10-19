package com.sirynx.planosApi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Victoria Belo
 */

@Entity
@Table(name = "beneficiaries")
public class Beneficiaries implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	private String nome;
	
	private int idade;
	
	private int qtd_beneficiario;	
	
	private double faixa;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "beneficiaries", cascade = CascadeType.ALL)
	private List<Dependents> dependents;
	
	@ManyToOne
	@JoinColumn(name="plans_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Plans plans;		
	

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getQtd_beneficiario() {
		return qtd_beneficiario;
	}

	public void setQtd_beneficiario(int qtd_beneficiario) {
		this.qtd_beneficiario = qtd_beneficiario;
	}

	public List<Dependents> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependents> dependents) {
		this.dependents = dependents;
	}

	public Plans getPlans() {
		return plans;
	}

	public void setPlans(Plans plans) {
		this.plans = plans;
	}


	public double getFaixa() {
		return faixa;
	}

	public void setFaixa(double faixa) {
		this.faixa = faixa;
	}

	@Override
	public String toString() {
		return "Beneficiaries \n codigo=" + codigo + "\n nome=" + nome + "\n idade=" + idade + "\n qtd_beneficiario="
				+ qtd_beneficiario + "\n dependents=" + dependents + "\n plans=" + plans  ;
	}		
	
}
