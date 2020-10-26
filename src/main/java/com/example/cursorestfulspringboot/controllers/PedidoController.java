package com.example.cursorestfulspringboot.controllers;

import java.util.List;
import com.example.cursorestfulspringboot.model.Pedido;
import com.example.cursorestfulspringboot.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pedidos")

public class PedidoController {

    
    @Autowired /*Fornece controle sobre onde e como a ligação entre os beans (classes) deve ser realizada.*/
    private PedidoService servico;

    @GetMapping /*GetMapping só se aplica à métodos e não classes como o @RequestMapping*/
    public List<Pedido> getPedidos() {
        return servico.getAllPedidos(); /*Retornar todos os pedidos da lista*/
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Pedido> getPedidoByNumero(@PathVariable long numero) {
        Pedido pedido= servico.getPedidoByNumero(numero);
        return ResponseEntity.ok(pedido);	
    }

    

}
