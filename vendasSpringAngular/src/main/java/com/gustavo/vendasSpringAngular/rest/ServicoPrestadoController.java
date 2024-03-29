package com.gustavo.vendasSpringAngular.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.vendasSpringAngular.model.entity.Cliente;
import com.gustavo.vendasSpringAngular.model.entity.ServicoPrestado;
import com.gustavo.vendasSpringAngular.model.repository.ClienteRepository;
import com.gustavo.vendasSpringAngular.model.repository.ServicoPrestadoRepository;
import com.gustavo.vendasSpringAngular.rest.dto.ServicoPrestadoDTO;
import com.gustavo.vendasSpringAngular.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository servicoPrestadoRepository;
	private final BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = clienteRepository
							.findById(idCliente)
							.orElseThrow(() -> 
							 new ResponseStatusException( HttpStatus.BAD_REQUEST, "Cliente inexistente."));
		  
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor( bigDecimalConverter.converter( dto.getPreco() ) );
		
		return servicoPrestadoRepository.save(servicoPrestado);
	}
	
	@GetMapping
	public  List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			){
		
		return clienteRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
	
	
	

}
