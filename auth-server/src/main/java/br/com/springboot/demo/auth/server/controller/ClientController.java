package br.com.springboot.demo.auth.server.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.demo.auth.server.controller.dto.ClientRequestDTO;
import br.com.springboot.demo.auth.server.domain.Client;
import br.com.springboot.demo.auth.server.mapper.ClientMapper;
import br.com.springboot.demo.auth.server.service.ClientService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/clients")
public class ClientController {

	private final ClientService clientService;

	private final ClientMapper mapper;

	@GetMapping("/{clientId}")
	public Client read(@PathVariable String clientId) {
		return clientService.findByClientId(clientId);
	}

	@GetMapping
	public List<Client> list() {
		return clientService.findAll();
	}

	@PostMapping
	public Client create(@Validated @RequestBody ClientRequestDTO payload) {
		Client client = mapper.toClientWithProperties(payload);
		return clientService.save(client);
	}
}