package com.example.emprestimo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emprestimo.entities.Cliente;
import com.example.emprestimo.repository.ClienteRepository;
import com.example.emprestimo.service.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	
	@PostMapping
	public Cliente criarCliente(@RequestBody Cliente cliente) {
		return clienteService.salvarCliente(cliente);
	}

}
