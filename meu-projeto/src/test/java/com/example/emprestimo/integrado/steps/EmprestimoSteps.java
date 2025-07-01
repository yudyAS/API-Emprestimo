package com.example.emprestimo.integrado.steps;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.emprestimo.dto.EmprestimoRequestDTO;
import com.example.emprestimo.dto.EmprestimoResponseDTO;
import com.example.emprestimo.service.EmprestimoService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmprestimoSteps {

	private ResponseEntity <String>response;

    private String resourceJson;
	@LocalServerPort
	private int port;


	private final RestTemplate restTemplate = new RestTemplate();

	private final ObjectMapper objectMapper = new ObjectMapper();

	Map<String, Object> dados = new HashMap<>();

	@Dado("um cliente com score {int} e renda mensal {double}")
	public void um_cliente_com_score_e_renda(int score, double renda) {

		dados.put("nomeCompleto", "João da Silva Santos");
		dados.put("score", score);
		dados.put("rendaMensal", renda);

	}

	@E("deseja um empréstimo de {double}")
	public void desejaUmEmprestimoDe(double emprestimo) throws JsonProcessingException {

		dados.put("valorCreditoSolicitado", emprestimo);

		resourceJson = objectMapper.writeValueAsString(dados);
	}

	@Quando("ele solicita um empréstimo")
	public void ele_solicita_um_emprestimo() {
		String url = "http://localhost:" + port + "/api/v1/simulacoes/emprestimos";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>(resourceJson, headers);

		response = restTemplate.postForEntity(url, request, String.class);
	}

	@Entao("a situação deve ser {string}")
	public void a_situacao_deve_ser(String situacao) throws JsonProcessingException {
		JsonNode responseJson = objectMapper.readTree(response.getBody());
		String valorCampo = responseJson.get("situacao").asText();
		assertEquals(situacao, valorCampo);
	}

	@E("o valor da parcela mensal deve ser exibido")
	public void valor_parcela_deve_ser_exibido() throws JsonProcessingException {
		JsonNode responseJson = objectMapper.readTree(response.getBody());

		if("APROVADO".equalsIgnoreCase(responseJson.get("situacao").asText())){

			String valorCampo = responseJson.get("valorParcelaMensal").asText();
			assertNotNull(valorCampo);

		}else{
			String valorCampo = responseJson.get("motivo").asText();
			assertNotNull(valorCampo);
		}
	}

}
