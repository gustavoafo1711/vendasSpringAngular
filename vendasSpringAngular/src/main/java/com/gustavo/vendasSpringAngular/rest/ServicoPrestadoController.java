package com.gustavo.vendasSpringAngular.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.vendasSpringAngular.model.entity.Cliente;
import com.gustavo.vendasSpringAngular.model.entity.ServicoPrestado;
import com.gustavo.vendasSpringAngular.model.repository.ClienteRepository;
import com.gustavo.vendasSpringAngular.model.repository.ServicoPrestadoRepository;
import com.gustavo.vendasSpringAngular.rest.dto.ServicoPrestadoDTO;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository servicoPrestadoRepository;
	
	@PostMapping
	public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto) {
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
		servicoPrestado.setValor();
	}

}
