package com.example.emprestimo.controllers;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/simulacoes/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService emprestimoService;
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@PostMapping
	public EmprestimoResponseDTO simular(@Valid @RequestBody EmprestimoRequestDTO request) {
		log.info("Recebida requisição de simulação de empréstimo: {}", request);

		EmprestimoResponseDTO response = emprestimoService.simularEmprestimo(request);

		log.info("Resposta da simulação de empréstimo: {}", response);
		return emprestimoService.simularEmprestimo(request);
	}
	
	@GetMapping
	public List<Emprestimo> listarTodos(){
		log.info("Recebida requisição para listar todos os empréstimos.");

		List<Emprestimo> emprestimos = emprestimoRepository.findAll();

		log.info("Total de empréstimos encontrados: {}", emprestimos.size());
		return emprestimoRepository.findAll();
	}
}
