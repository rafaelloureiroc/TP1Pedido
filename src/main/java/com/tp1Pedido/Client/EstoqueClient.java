package com.tp1Pedido.Client;

import com.tp1Pedido.Model.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoqueClient", url = "http://localhost:8080")
public interface EstoqueClient {
    @GetMapping("/produtos/{id}")
    Produto getProduto(@PathVariable("id") Long id);

    @PostMapping("/produtos/{id}/atualizar")
    void atualizarQuantidade(@PathVariable("id") Long id, @RequestParam("quantidade") Integer quantidade);
}