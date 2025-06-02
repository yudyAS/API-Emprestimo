package com.example.emprestimo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.emprestimo.dto.EmprestimoRequestDTO;
import com.example.emprestimo.dto.EmprestimoResponseDTO;
import com.example.emprestimo.entities.Cliente;
import com.example.emprestimo.entities.Emprestimo;
import com.example.emprestimo.repository.ClienteRepository;
import com.example.emprestimo.repository.EmprestimoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class EmprestimoServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSimularEmprestimoAprovado() {
        // Monta o DTO de requisição
        EmprestimoRequestDTO request = new EmprestimoRequestDTO();
        request.setNomeCompleto("João da Silva");
        request.setRendaMensal(3000.0);
        request.setValorCreditoSolicitado(5000.0);
        request.setScore(500);

        // Simula salvamento do cliente e empréstimo (pode devolver o mesmo objeto ou um mock)
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArguments()[0]);
        when(emprestimoRepository.save(any(Emprestimo.class))).thenAnswer(i -> i.getArguments()[0]);

        // Executa o método que será testado
        EmprestimoResponseDTO response = emprestimoService.simularEmprestimo(request);

        // Verificações
        assertEquals("APROVADO", response.getSituacao());
        assertEquals(5000.0, response.getValorAprovado());
        assertEquals(12, response.getQuantidadeMaximaParcelas());
        assertNotNull(response.getValorParcelaMensal());
    }
    
    @Test
    public void testSimularEmprestimoReprovadoPorValorMaiorQue5xRenda() {
    	EmprestimoRequestDTO request = new EmprestimoRequestDTO();
    	request.setNomeCompleto("Maria Oliveira");
    	request.setRendaMensal(2000.0);
    	request.setValorCreditoSolicitado(12000.0);
    	request.setScore(400);// maior que 2000 * 5
    	
    	when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArguments()[0]);
    	when(emprestimoRepository.save(any(Emprestimo.class))).thenAnswer(i -> i.getArguments()[0]);
    	
    	EmprestimoResponseDTO response = emprestimoService.simularEmprestimo(request);
    	
    	assertEquals("REPROVADOO", response.getSituacao());
    	assertEquals("Valor solicitado execede o limite permitido.", response.getMotivo());
    	assertEquals(0.0, response.getValorAprovado());
    }
    
    
    @Test
    public void testSimularEmprestimoReprovadoPorScoreBaixo() {
    	EmprestimoRequestDTO request = new EmprestimoRequestDTO();
    	request.setNomeCompleto("Carlos Lima");
    	request.setRendaMensal(2500.0);
    	request.setValorCreditoSolicitado(5000.0);
    	request.setScore(200);// score menor que 300
    	
    	when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArguments()[0]);
    	when(emprestimoRepository.save(any(Emprestimo.class))).thenAnswer(i -> i.getArguments()[0]);
    	
    	EmprestimoResponseDTO response = emprestimoService.simularEmprestimo(request);
    	
    	assertEquals("REPROVADO", response.getSituacao());
    	assertEquals("Seu score está abaixo do minimo necessário para solicitar um empréstimo.", response.getMotivo());
    	assertEquals(0.0, response.getValorAprovado());
    	
    }
}





