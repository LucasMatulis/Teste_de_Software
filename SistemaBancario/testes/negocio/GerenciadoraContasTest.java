package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraContasTest {
	
	private GerenciadoraContas gerContas;
	
	int idConta01=1;
	int idConta02=2;
	
	
	
	//Saldo suficiente em ambas as contas
	@Test
	public void testTransfereValor() {
		ContaCorrente conta01= new ContaCorrente(idConta01,200,true);
		ContaCorrente conta02= new ContaCorrente(idConta02,0,true);
		
		List<ContaCorrente> contasDoBanco= new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas= new GerenciadoraContas(contasDoBanco);
		
		
		boolean sucesso= gerContas.transfereValor(idConta01, 100, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta02.getSaldo(), is(100.0));
		assertThat(conta01.getSaldo(), is(100.0));

	}
	
	
	//Saldo insuficiente e positivo na Conta 1
	@Test
	public void testTransfereValor02() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, 100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		GerenciadoraContas gerContas = new GerenciadoraContas(contasDoBanco);
		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-100.0));
		assertThat(conta02.getSaldo(), is(200.0));
	}
	
	
	//Saldo insuficiente e negativo na Conta 1
	@Test
	public void testTransfereValor03() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		GerenciadoraContas gerContas = new GerenciadoraContas(contasDoBanco);
		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(200.0));
	}
	
	
	//Saldoo insuficiente em ambas as contas
	@Test
	public void testTransfereValor04() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, -100, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		GerenciadoraContas gerContas = new GerenciadoraContas(contasDoBanco);
		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(100.0));
	}

}
