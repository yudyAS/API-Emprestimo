package com.example.emprestimo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmprestimoResponseDTO {

	private String situacao;
	private String motivo;
	private Double valorAprovado;
	private Double jurosAnoPercentual;
	private Integer quantidadeMaximaParcelas;
	private String valorParcelaMensal;
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Double getValorAprovado() {
		return valorAprovado;
	}
	public void setValorAprovado(Double valorAprovado) {
		this.valorAprovado = valorAprovado;
	}
	public Double getJurosAnoPercentual() {
		return jurosAnoPercentual;
	}
	public void setJurosAnoPercentual(Double jurosAnoPercentual) {
		this.jurosAnoPercentual = jurosAnoPercentual;
	}
	public Integer getQuantidadeMaximaParcelas() {
		return quantidadeMaximaParcelas;
	}
	public void setQuantidadeMaximaParcelas(Integer quantidadeMaximaParcelas) {
		this.quantidadeMaximaParcelas = quantidadeMaximaParcelas;
	}
	public String getValorParcelaMensal() {
		return valorParcelaMensal;
	}
	public void setValorParcelaMensal(String valorParcelaMensal) {
		this.valorParcelaMensal = valorParcelaMensal;
	}
	
	
}
