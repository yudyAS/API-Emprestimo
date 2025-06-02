package com.example.emprestimo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;
	
	@Column(name = "valor_cliente_solicitado")
	private Double valorClienteSolicitado;
	
	@Column(name = "juros_ano_percentual")
	private Double jurosAnoPercentual;
	
	@Column(name = "quantidade_maxima_parcelas")
	private Integer quantidadeMaximaParcelas;
	
	@Column(name = "valo_parcela_mensal")
	private Double valorParcelaMensal;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public Emprestimo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValorClienteSolicitado() {
		return valorClienteSolicitado;
	}

	public void setValorClienteSolicitado(Double valorClienteSolicitado) {
		this.valorClienteSolicitado = valorClienteSolicitado;
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

	public Double getValorParcelaMensal() {
		return valorParcelaMensal;
	}

	public void setValorParcelaMensal(Double valorParcelaMensal) {
		this.valorParcelaMensal = valorParcelaMensal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
