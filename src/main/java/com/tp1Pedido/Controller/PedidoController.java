package com.tp1Pedido.Controller;

import com.tp1Pedido.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestParam Long produtoId, @RequestParam Integer quantidade) {
        pedidoService.criarPedido(produtoId, quantidade);
        return ResponseEntity.ok().build();
    }
}