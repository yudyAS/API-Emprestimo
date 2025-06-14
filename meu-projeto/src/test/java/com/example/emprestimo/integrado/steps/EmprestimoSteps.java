package com.example.emprestimo.integrado.steps;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.emprestimo.dto.EmprestimoRequestDTO;
import com.example.emprestimo.dto.EmprestimoResponseDTO;
import com.example.emprestimo.service.EmprestimoService;

import io.cucumber.java.pt.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmprestimoSteps {

	private EmprestimoRequestDTO request;
	private EmprestimoResponseDTO response;

	@Autowired
	private EmprestimoService service;

	@Dado("um cliente com score {int} e renda mensal {double}")
	public void um_cliente_com_score_e_renda(int score, double renda) {
		request = new EmprestimoRequestDTO();
		request.setScore(score);
		request.setRendaMensal(renda);
	}
	@E("deseja um emprestimo de {double}")
	public void deseja_um_emprestimo(double valor) {
		request.setValorCreditoSolicitado(valor);
	}
	
	@Quando("ele solicita um empréstimo")
	public void ele_solicita_um_emprestimo() {
		response = service.simularEmprestimo(request);
	}
	
	@Entao("a situação deve ser {string}")
	public void a_situacao_deve_ser(String situacao) {
		assertEquals(situacao, response.getSituacao());
	}
	
	@E("o valor da parcela mensal deve ser exibido")
	public void valor_parcela_deve_ser_exibido() {
		assertNotNull(response.getValorParcelaMensal());
	}
}
