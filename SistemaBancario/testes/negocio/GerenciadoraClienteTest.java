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
    private int idCLiente01=1;
    private int idCLiente02=2;

    @Before
    public void setUp(){
        Cliente cliente01= new Cliente(idCLiente01, "João", 31, "joao@gmail.com",1,true);
        Cliente cliente02= new Cliente(idCLiente02, "Maria", 34, "maria@gmail.com",1,true);

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

        Cliente cliente= gerClientes.pesquisaCliente(idCLiente01);

        assertThat(cliente.getId(), is(idCLiente01));
    }

    @Test
    public void testRemoveCliente(){

        boolean clienteRemovido= gerClientes.removeCliente(idCLiente02);

        assertThat(clienteRemovido,is(true));
        assertThat(gerClientes.getClientesDoBanco().size(), is(1));
        assertNull(gerClientes.pesquisaCliente(idCLiente02));
    }
    
}
