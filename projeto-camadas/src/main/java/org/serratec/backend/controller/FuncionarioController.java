package org.serratec.backend.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.serratec.backend.model.Funcionario;
import org.serratec.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll(){
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/pagina")
	public ResponseEntity<Page<Funcionario>> findAll(@PageableDefault(sort = "nome",
            direction = Sort.Direction.ASC,
            page = 3,
            size = 8) Pageable pageable){
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/salario")
	public ResponseEntity<Page<Funcionario>> listarSalarios(@RequestParam(defaultValue = "0") Double valorMinimo, @RequestParam(defaultValue = "20000") Double valorMaximo, Pageable pageable){
		Page<Funcionario> funcionarios = funcionarioRepository.findBySalarioBetween(valorMinimo, valorMaximo, pageable);
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/nome")
	public ResponseEntity<Page<Funcionario>>buscarPorNome(@RequestParam(defaultValue = "") String paramNome,
			Pageable pageable){
		Page<Funcionario> funcionarios = funcionarioRepository.findByNomeContainingIgnoreCase(paramNome, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/data")
	public ResponseEntity<Page<Funcionario>>buscarPorData(@RequestParam String paramDataNasc,
			Pageable pageable){
		LocalDate dataNascimento = LocalDate.parse(paramDataNasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Page<Funcionario> funcionarios = funcionarioRepository.findBydataNascimentoGreaterThan(dataNascimento, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
}

