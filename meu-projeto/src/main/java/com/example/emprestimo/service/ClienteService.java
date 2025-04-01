package com.example.emprestimo.service;

import org.springframework.stereotype.Service;

import com.example.emprestimo.entities.Cliente;
import com.example.emprestimo.repository.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente salvarCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
}
