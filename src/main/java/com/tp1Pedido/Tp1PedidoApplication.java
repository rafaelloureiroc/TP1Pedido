package com.tp1Pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Tp1PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp1PedidoApplication.class, args);
	}

}
