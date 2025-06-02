package com.example.emprestimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emprestimo.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}
