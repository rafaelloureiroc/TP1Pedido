package com.tp1Pedido.Service;

import com.tp1Pedido.Client.EstoqueClient;
import com.tp1Pedido.Model.Pedido;
import com.tp1Pedido.Model.Produto;
import com.tp1Pedido.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EstoqueClient estoqueClient;

    public void criarPedido(Long produtoId, Integer quantidade) {
        Produto produto = estoqueClient.getProduto(produtoId);
        if (produto != null && produto.getQuantidade() >= quantidade) {
            Pedido pedido = new Pedido();
            pedido.setProdutoId(produtoId);
            pedido.setQuantidade(quantidade);
            pedidoRepository.save(pedido);

            estoqueClient.atualizarQuantidade(produtoId, produto.getQuantidade() - quantidade);
        } else {
            throw new RuntimeException("Estoque insuficiente");
        }
    }
}