package com.example.cursorestfulspringboot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import com.example.cursorestfulspringboot.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {

    private List<Cliente> clientes;
    private int nextId;

    @PostConstruct
    public void init() {
        Cliente c1 = new Cliente();
        c1.setId(1);
        c1.setNome("Jose");
        c1.setEndereco("Rua X, 99");
        c1.setSaldo(202.0);

        Cliente c2 = new Cliente();
        c2.setId(2);
        c2.setNome("Maria");
        c2.setEndereco("Rua Y, 59");
        c2.setSaldo(444.0);

        Cliente c3 = new Cliente();
        c3.setId(3);
        c3.setNome("Fernanda");
        c3.setEndereco("Rua W, 33");
        c3.setSaldo(332.0);

        clientes = new ArrayList<Cliente>();
        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        nextId = 4;
        
    }

    public List<Cliente> getAllClientes(){
        return clientes;
    }
    
    public Optional<Cliente> getClienteById(int id){ /* Optional evita uso de "if" e "else"*/
        for (Cliente aux : clientes) {
            if(aux.getId() == id){
                return Optional.of(aux); /*Se houver cliente retorna o mesmo*/
            } /*Optional retorna true se existe valor e false se não existir*/
        }
        /*Se não houver, retorna uma instância de Optional vazia*/
        return Optional.empty();
    }

	public Cliente salvar(Cliente cliente) { 
        cliente.setId(nextId++); //novo cliente com o próximo Id não utilizado
        clientes.add(cliente); //Adiciona esse cliente no repositório
        return cliente; //Retorna esse mesmo cliente
	}

	public void remove(Cliente cli) {
        clientes.remove(cli);
	}

	public Cliente update(Cliente cliente) {
        
        //Recebe o Id do cliente a ser atualizado, se houver esse Id retorna o mesmo, se não, taca-lhe 404
        Cliente aux = getClienteById(cliente.getId()).get(); /*get() - Pegar Optional apenas do Cliente*/
        if(aux != null){ //Se não for vazio ou não der 404, atualiza
            aux.setEndereco(cliente.getEndereco()); //atualiza endereço
            aux.setNome(cliente.getNome()); //atualiza nome
        }
        return aux; //retorna o optional do Cliente
        
	}

}