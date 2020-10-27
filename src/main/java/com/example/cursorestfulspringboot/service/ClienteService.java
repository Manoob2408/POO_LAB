package com.example.cursorestfulspringboot.service;

import java.util.List;
import java.util.Optional;

import com.example.cursorestfulspringboot.dto.ClienteDTO;
import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service /*Anotação para indicar Classe de serviço*/
public class ClienteService {
    
    @Autowired /*Marca um construtor*/
    private ClienteRepository repositorio;

    public Cliente getClienteById(int id){
        Optional<Cliente> op = repositorio.getClienteById(id); /*Optional evita o uso de "if" e "else"*/
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente nao cadastrado: " + id));
        /*Se não houver Cliente, retorna a exceção(erro) 404(Not found)*/
    }



    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setEndereco(dto.getEndereco());
        cliente.setNome(dto.getNome());
        return cliente;
    }



	public Cliente update(Cliente cliente) {
        getClienteById(cliente.getId()); /*entra na função recebendo o numero do Id por meio 
        do get da classe cliente*/
        return repositorio.update(cliente); /*Atualiza no repositorio */
    }



    public List<Cliente> getAllClientes() { 
		return repositorio.getAllClientes();
	}



	public Cliente salvar(Cliente cliente) { /*Salvar cliente no repositório*/
        /*Pode conter validação*/
		return repositorio.salvar(cliente);
	}



	public void removeById(int id) { /*Remove um cliente pelo Id no repositorio*/
       repositorio.remove(getClienteById(id)); /*Retorna um cliente, se não houver o ElseThrow
       vai retornar o erro 404 */
   }




}