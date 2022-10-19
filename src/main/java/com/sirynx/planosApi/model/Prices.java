package com.sirynx.planosApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Victoria Belo
 */
@Entity
@Table(name="prices")
public class Prices implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;	
	
	private int minimo_vidas;
	
	private double faixa1;
	
	private double faixa2;
	
	private double faixa3;
	
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
	public int getMinimo_vidas() {
		return minimo_vidas;
	}
	public void setMinimo_vidas(int minimo_vidas) {
		this.minimo_vidas = minimo_vidas;
	}
	public double getFaixa1() {
		return faixa1;
	}
	public void setFaixa1(double faixa1) {
		this.faixa1 = faixa1;
	}
	public double getFaixa2() {
		return faixa2;
	}
	public void setFaixa2(double faixa2) {
		this.faixa2 = faixa2;
	}
	public double getFaixa3() {
		return faixa3;
	}
	public void setFaixa3(double faixa3) {
		this.faixa3 = faixa3;
	}
	
	public Plans getPlans() {
		return plans;
	}

	public void setPlans(Plans plans) {
		this.plans = plans;
	}

	@Override
	public String toString() {
		return "Prices [codigo=" + codigo + ", minimo_vidas=" + minimo_vidas + ", faixa1=" + faixa1 + ", faixa2="
				+ faixa2 + ", faixa3=" + faixa3 + "]";
	}
	

}
