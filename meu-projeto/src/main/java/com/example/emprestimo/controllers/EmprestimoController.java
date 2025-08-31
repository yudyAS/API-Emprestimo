package com.example.emprestimo.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emprestimo.dto.EmprestimoRequestDTO;
import com.example.emprestimo.dto.EmprestimoResponseDTO;
import com.example.emprestimo.entities.Emprestimo;
import com.example.emprestimo.repository.EmprestimoRepository;
import com.example.emprestimo.service.EmprestimoService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/simulacoes/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService emprestimoService;
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger logger = LoggerFactory.getLogger(EmprestimoController.class);
	
	@PostMapping
	public EmprestimoResponseDTO simular(@Valid @RequestBody EmprestimoRequestDTO request) throws JsonProcessingException {
		logger.info("Recebida requisição de simulação de empréstimo: {}", objectMapper.writeValueAsString(request));

		EmprestimoResponseDTO response = emprestimoService.simularEmprestimo(request);

		logger.info("Resposta da simulação de empréstimo: {}", objectMapper.writeValueAsString(response));
		return response;
	}
	
	@GetMapping
	public List<Emprestimo> listarTodos(){
		logger.info("Recebida requisição para listar todos os empréstimos.");

		List<Emprestimo> emprestimos = emprestimoRepository.findAll();

		logger.info("Total de empréstimos encontrados: {}", emprestimos.size());
		return emprestimos;
	}
}
