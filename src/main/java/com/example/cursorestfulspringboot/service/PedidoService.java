package com.example.cursorestfulspringboot.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.model.Pedido;
import com.example.cursorestfulspringboot.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service //Mostra que eh uma classe de serviços
public class PedidoService {
    
    @Autowired
    private PedidoRepository repositorio;

    @Autowired
    private ClienteService clienteService;

    public Pedido  getPedidoByNumero(long numero){ //Retornar pedido pelo id
        Optional<Pedido> op = repositorio.getPedidoByNumero(numero); //O optional substitui o if/else
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido nao cadastrado: " + numero));
        //Se houver algum pedido, retorna o mesmo, se não, retorna o 404
    }

    public List<Pedido>  getAllPedidos(){
        return repositorio.getAllPedidos();
    }

	public Pedido salvar(int idCliente, Pedido pedido) {

        //Se o cliente não existir, lança o erro 404 e finaliza!
        Cliente cliente = clienteService.getClienteById(idCliente);


        /**O pedido é salvo com a data da hora do salvamento  */
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente); //Adiciona o cliente do pedido
        cliente.addPedido(pedido); //Adiciona o pedido do cliente

        return  repositorio.salvar(pedido); //Salva no repositorio
    }
}