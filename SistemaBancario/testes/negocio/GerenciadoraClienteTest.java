package negocio;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.Cliente;
import negocio.GerenciadoraClientes;



public class GerenciadoraClienteTest {

    private GerenciadoraClientes gerClientes;
    private int idCliente01=1;
    private int idCliente02=2;

    @Before
    public void setUp(){
        Cliente cliente01= new Cliente(idCliente01, "João", 31, "joao@gmail.com",1,true);
        Cliente cliente02= new Cliente(idCliente02, "Maria", 34, "maria@gmail.com",1,true);

        List<Cliente> clientesDoBanco= new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);

        gerClientes=new GerenciadoraClientes(clientesDoBanco);
    }

    @After
    public void tearDown(){
        gerClientes.limpa();
    }

    @Test
    public void testPesquisaCliente(){

        Cliente cliente= gerClientes.pesquisaCliente(idCliente01);

        assertThat(cliente.getId(), is(idCliente01));
    }

    @Test
    public void testRemoveCliente(){

        boolean clienteRemovido= gerClientes.removeCliente(idCliente02);

        assertThat(clienteRemovido,is(true));
        assertThat(gerClientes.getClientesDoBanco().size(), is(1));
        assertNull(gerClientes.pesquisaCliente(idCliente02));
    }
    
    
    //Validar a idade do cliente
    @Test
	public void testValidarIdade() {
		try {
			assertThat(gerClientes.validaIdade(gerClientes.pesquisaCliente(idCliente01).getIdade()), is(true));
		} catch (IdadeNaoPermitidaException e) {
			e.printStackTrace();
		}
		try {
			assertThat(gerClientes.validaIdade(gerClientes.pesquisaCliente(idCliente02).getIdade()), is(true));
		} catch (IdadeNaoPermitidaException e) {
			e.printStackTrace();
		}
		try {
			assertThat(gerClientes.validaIdade(17), is(true));
		} catch (IdadeNaoPermitidaException e) {
			e.printStackTrace();
		}
		try {
			assertThat(gerClientes.validaIdade(66), is(true));
		} catch (IdadeNaoPermitidaException e) {
			e.printStackTrace();
		}
	}
    
}
