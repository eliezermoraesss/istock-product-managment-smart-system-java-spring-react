package com.eliezer.iestoque.services;

import com.eliezer.iestoque.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepService {

    @GetMapping("{cep}/json")
    AddressDTO findAddress(@PathVariable("cep") String cep);
	
}
