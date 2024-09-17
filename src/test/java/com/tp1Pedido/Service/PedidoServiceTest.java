package com.tp1Pedido.Service;

import static org.mockito.Mockito.when;

import com.tp1Pedido.Client.ProdutoClient;
import com.tp1Pedido.DTO.ProdutoDTO;
import com.tp1Pedido.Model.Pedido;
import com.tp1Pedido.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;


@SpringBootTest
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoClient estoqueClient;

    public PedidoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
@Test
public void criarPedido_Sucesso() {
    Long produtoId = 1L;
    int quantidade = 5;
    double dinheiro = 100.0;
    ProdutoDTO produto = new ProdutoDTO();
    produto.setId(produtoId);
    produto.setQuantidade(10);
    produto.setPreco(10.0);

    when(estoqueClient.getProduto(produtoId)).thenReturn(produto);

    pedidoService.criarPedido(produtoId, quantidade, dinheiro);

    verify(pedidoRepository, times(1)).save(any(Pedido.class));
    verify(estoqueClient, times(1)).atualizarQuantidade(produtoId, produto.getQuantidade() - quantidade);
}

@Test
public void criarPedido_EstoqueInsuficiente() {
    Long produtoId = 1L;
    int quantidade = 5;
    double dinheiro = 100.0;
    ProdutoDTO produto = new ProdutoDTO();
    produto.setId(produtoId);
    produto.setQuantidade(2);
    produto.setPreco(10.0);

    when(estoqueClient.getProduto(produtoId)).thenReturn(produto);

    try {
        pedidoService.criarPedido(produtoId, quantidade, dinheiro);
    } catch (RuntimeException e) {
        assert(e.getMessage().equals("Estoque insuficiente"));
    }

    verify(pedidoRepository, never()).save(any(Pedido.class));
    verify(estoqueClient, never()).atualizarQuantidade(anyLong(), anyInt());
}

@Test
public void criarPedido_DinheiroInsuficiente() {
    Long produtoId = 1L;
    int quantidade = 5;
    double dinheiro = 30.0;
    ProdutoDTO produto = new ProdutoDTO();
    produto.setId(produtoId);
    produto.setQuantidade(10);
    produto.setPreco(10.0);

    when(estoqueClient.getProduto(produtoId)).thenReturn(produto);

    try {
        pedidoService.criarPedido(produtoId, quantidade, dinheiro);
    } catch (RuntimeException e) {
        assert(e.getMessage().equals("Dinheiro insuficiente para realizar a compra"));
    }

    verify(pedidoRepository, never()).save(any(Pedido.class));
    verify(estoqueClient, never()).atualizarQuantidade(anyLong(), anyInt());
}
}