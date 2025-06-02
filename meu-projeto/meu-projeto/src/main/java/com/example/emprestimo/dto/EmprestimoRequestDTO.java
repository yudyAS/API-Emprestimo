package com.example.emprestimo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class EmprestimoRequestDTO {
	@Size(min = 15, max = 50, message = "O nome deve ter entre 15 e 50 caracteres")
	private String nomeCompleto;
	private Double valorCreditoSolicitado;
	@Min(value = 1, message = "O score deve ser no mínimo 1") 
    @Max(value = 1000, message = "O score deve ser no máximo 1000")
	private Integer score;
	private Double rendaMensal;
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Double getValorCreditoSolicitado() {
		return valorCreditoSolicitado;
	}
	public void setValorCreditoSolicitado(Double valorCreditoSolicitado) {
		this.valorCreditoSolicitado = valorCreditoSolicitado;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Double getRendaMensal() {
		return rendaMensal;
	}
	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
	
	
}
