package com.eliezer.iestoque.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public class ViaCepService {
	
}
