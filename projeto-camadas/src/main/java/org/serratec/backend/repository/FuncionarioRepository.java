package org.serratec.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.serratec.backend.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query("SELECT f FROM Funcionario f WHERE f.salario >=:valorMinimo AND f.salario <= :valorMaximo ")
	Page<Funcionario> buscarSalario(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	@Query("SELECT f FROM Funcionario f WHERE UPPER(f.nome) LIKE UPPER(CONCAT('%',:paramNome,'%'))")
	Page<Funcionario> buscarPorNome(String paramNome, Pageable pageable);
	
	Page<Funcionario> findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	Page<Funcionario> findByNomeContainingIgnoreCase(String paramNome, Pageable pageable);
	
	Page<Funcionario> findBynomeStartingWith(String paramNome, Pageable pageable);
	
	Page<Funcionario> findBynomeEndingWith(String paramNome, Pageable pageable);
	
	Page<Funcionario> findBydataNascimentoGreaterThan(LocalDate paramDataNasc, Pageable pageable);
		
}

