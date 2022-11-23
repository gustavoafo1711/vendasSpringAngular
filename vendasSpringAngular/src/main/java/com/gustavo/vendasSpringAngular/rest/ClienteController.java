package com.gustavo.vendasSpringAngular.rest;

import com.gustavo.vendasSpringAngular.model.entity.Cliente;
import com.gustavo.vendasSpringAngular.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repositoryCliente;

    @Autowired
    public ClienteController(ClienteRepository repositoryCliente){
        this.repositoryCliente =repositoryCliente;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return repositoryCliente.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return repositoryCliente.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repositoryCliente.findById(id)
                .map( c -> {
                    repositoryCliente.delete(c);
                    return Void.TYPE;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
        repositoryCliente.findById(id)
                .map( c -> {
                    clienteAtualizado.setId(c.getId());
                    return repositoryCliente.save(clienteAtualizado);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

}
