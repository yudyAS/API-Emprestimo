package com.example.emprestimo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.emprestimo.dto.EmprestimoRequestDTO;
import com.example.emprestimo.dto.EmprestimoResponseDTO;
import com.example.emprestimo.entities.Cliente;
import com.example.emprestimo.entities.Emprestimo;
import com.example.emprestimo.repository.ClienteRepository;
import com.example.emprestimo.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	
	public EmprestimoResponseDTO simularEmprestimo(EmprestimoRequestDTO request) {
		
		Cliente cliente = new Cliente();
		cliente.setNomeCompleto(request.getNomeCompleto());
		cliente.setRendaMensal(request.getRendaMensal());
		cliente.setScore(request.getScore());
		cliente = clienteRepository.save(cliente);
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setValorClienteSolicitado(request.getValorCreditoSolicitado());
		emprestimo.setCliente(cliente);
		
		EmprestimoResponseDTO response = new EmprestimoResponseDTO();
		
		if(request.getValorCreditoSolicitado() > request.getRendaMensal() * 5) {
			response.setSituacao("REPROVADO");
			response.setMotivo("Valor solicitado execede o limite permitido.");
			
			emprestimo.setStatus("REPROVADO");
			emprestimo.setJurosAnoPercentual(0.0);
			emprestimo.setQuantidadeMaximaParcelas(0);
			emprestimo.setValorParcelaMensal(0.0);
			
			emprestimoRepository.save(emprestimo);
			
			return response;
		}
		
		if(request.getScore() < 300) {
			response.setSituacao("REPROVADO");
			response.setMotivo("Seu score está abaixo do mínimo necessário para solicitar um empréstimo.");
			
			emprestimo.setStatus("REPROVADO");
			emprestimo.setJurosAnoPercentual(0.0);
			emprestimo.setQuantidadeMaximaParcelas(0);
			emprestimo.setValorParcelaMensal(0.0);
			
			emprestimoRepository.save(emprestimo);
			
			return response;
		}
		
		
		response.setSituacao("APROVADO");
		response.setMotivo("Parabens, sua renda mensal e seu score se adequam para receber o empréstimo.");
		response.setValorAprovado(request.getValorCreditoSolicitado());
		response.setJurosAnoPercentual(7.0);
		response.setQuantidadeMaximaParcelas(12);
		
		
		double valorParcela = calcularParcela(request.getValorCreditoSolicitado(), 7.0, 12);
		emprestimo.setValorParcelaMensal(valorParcela);
		response.setValorParcelaMensal(String.format("%.2f", valorParcela));
		
		emprestimoRepository.save(emprestimo);
		
		emprestimo.setStatus("APROVADO");
		emprestimo.setJurosAnoPercentual(7.0);
		emprestimo.setQuantidadeMaximaParcelas(12);
		emprestimo.setValorParcelaMensal(valorParcela);
		
		emprestimoRepository.save(emprestimo);
		
		return response;
	}
	
	
	private double calcularParcela(double valor, double jurosAnual, int parcelas) {
		double jurosMensal = (jurosAnual / 12) / 100;
		return (valor * jurosMensal) / (1 - Math.pow(1 + jurosMensal, - parcelas));
	}
}
