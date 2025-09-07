package carrinho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Classe para teste do carrinho de compras")
public class CarrinhoTest {
	
	private Carrinho carrinho;
	private Produto produto1;
	private Produto produto2;
	private Produto produto3;
	
	@BeforeEach
	public void inicializa() {
		carrinho = new Carrinho();
		produto1 = new Produto("Livro Java", 50.00);
		produto2 = new Produto("Mouse", 25.00);
		produto3 = new Produto("Teclado", 100.00);
	}
	
	@DisplayName("Testa se o carrinho inicia vazio")
	@Test
	public void testCarrinhoIniciaVazio() {
		assertEquals(0, carrinho.getQtdeItems());
		assertEquals(0.0, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa adicionar um item ao carrinho")
	@Test
	public void testAddItem() {
		carrinho.addItem(produto1);
		assertEquals(1, carrinho.getQtdeItems());
		assertEquals(50.00, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa adicionar múltiplos itens ao carrinho")
	@Test
	public void testAddMultiplosItems() {
		carrinho.addItem(produto1);
		carrinho.addItem(produto2);
		carrinho.addItem(produto3);
		
		assertEquals(3, carrinho.getQtdeItems());
		assertEquals(175.00, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa remover um item do carrinho")
	@Test
	public void testRemoveItem() throws ProdutoNaoEncontradoException {
		carrinho.addItem(produto1);
		carrinho.addItem(produto2);
		
		carrinho.removeItem(produto1);
		
		assertEquals(1, carrinho.getQtdeItems());
		assertEquals(25.00, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa remover item que não existe no carrinho")
	@Test
	public void testRemoveItemNaoExistente() {
		carrinho.addItem(produto1);
		
		assertThrows(ProdutoNaoEncontradoException.class, 
			() -> carrinho.removeItem(produto2));
		
		// Verifica se o carrinho não foi alterado
		assertEquals(1, carrinho.getQtdeItems());
		assertEquals(50.00, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa esvaziar o carrinho")
	@Test
	public void testEsvazia() {
		carrinho.addItem(produto1);
		carrinho.addItem(produto2);
		carrinho.addItem(produto3);
		
		carrinho.esvazia();
		
		assertEquals(0, carrinho.getQtdeItems());
		assertEquals(0.0, carrinho.getValorTotal(), 0.01);
	}
	
	
	@DisplayName("Testa quantidade de itens após operações")
	@Test
	public void testGetQtdeItems() {
		assertEquals(0, carrinho.getQtdeItems());
		
		carrinho.addItem(produto1);
		assertEquals(1, carrinho.getQtdeItems());
		
		carrinho.addItem(produto2);
		assertEquals(2, carrinho.getQtdeItems());
		
		carrinho.addItem(produto3);
		assertEquals(3, carrinho.getQtdeItems());
	}
	
	@DisplayName("Testa valor total com produtos de preços diferentes")
	@Test
	public void testGetValorTotalComPrecosDiferentes() {
		Produto produtoBarato = new Produto("Lápis", 1.50);
		Produto produtoCaro = new Produto("Notebook", 2500.00);
		
		carrinho.addItem(produtoBarato);
		carrinho.addItem(produtoCaro);
		
		assertEquals(2501.50, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa adicionar o mesmo produto múltiplas vezes")
	@Test
	public void testAddMesmoProdutoMultiplasVezes() {
		carrinho.addItem(produto1);
		carrinho.addItem(produto1);
		carrinho.addItem(produto1);
		
		assertEquals(3, carrinho.getQtdeItems());
		assertEquals(150.00, carrinho.getValorTotal(), 0.01);
	}
	
	@DisplayName("Testa cenário completo: adicionar, remover e esvaziar")
	@Test
	public void testCenarioCompleto() throws ProdutoNaoEncontradoException {
		// Adiciona produtos
		carrinho.addItem(produto1);
		carrinho.addItem(produto2);
		assertEquals(2, carrinho.getQtdeItems());
		assertEquals(75.00, carrinho.getValorTotal(), 0.01);
		
		// Remove um produto
		carrinho.removeItem(produto1);
		assertEquals(1, carrinho.getQtdeItems());
		assertEquals(25.00, carrinho.getValorTotal(), 0.01);
		
		// Adiciona mais produtos
		carrinho.addItem(produto3);
		assertEquals(2, carrinho.getQtdeItems());
		assertEquals(125.00, carrinho.getValorTotal(), 0.01);
		
		// Esvazia o carrinho
		carrinho.esvazia();
		assertEquals(0, carrinho.getQtdeItems());
		assertEquals(0.0, carrinho.getValorTotal(), 0.01);
	}
	
	// ===== SEGUNDOS TESTES PARA CADA MÉTODO =====
	
	// Segundo teste para getValorTotal
	@DisplayName("Testa valor total com produtos de preços extremos")
	@Test
	public void testGetValorTotalPrecosExtremos() {
		Produto produtoBarato = new Produto("Lápis", 0.01);
		Produto produtoCaro = new Produto("Notebook", 9999.99);
		
		carrinho.addItem(produtoBarato);
		carrinho.addItem(produtoCaro);
		
		assertEquals(10000.00, carrinho.getValorTotal(), 0.01);
	}
	
	// Segundo teste para addItem
	@DisplayName("Testa adicionar produtos com nomes iguais mas preços diferentes")
	@Test
	public void testAddItemNomesIguaisPrecosDiferentes() {
		Produto produto1 = new Produto("Livro", 50.00);
		Produto produto2 = new Produto("Livro", 75.00);
		
		carrinho.addItem(produto1);
		carrinho.addItem(produto2);
		
		assertEquals(2, carrinho.getQtdeItems());
		assertEquals(125.00, carrinho.getValorTotal(), 0.01);
	}
	
	// Segundo teste para removeItem
	@DisplayName("Testa remover item de carrinho vazio")
	@Test
	public void testRemoveItemCarrinhoVazio() {
		assertThrows(ProdutoNaoEncontradoException.class, 
			() -> carrinho.removeItem(produto1));
	}
	
	// Segundo teste para getQtdeItems
	@DisplayName("Testa quantidade de itens após múltiplas operações")
	@Test
	public void testGetQtdeItemsMultiplasOperacoes() {
		assertEquals(0, carrinho.getQtdeItems());
		
		carrinho.addItem(produto1);
		carrinho.addItem(produto2);
		carrinho.addItem(produto3);
		assertEquals(3, carrinho.getQtdeItems());
		
		carrinho.esvazia();
		assertEquals(0, carrinho.getQtdeItems());
	}
	
	// Segundo teste para esvazia
	@DisplayName("Testa esvaziar carrinho já vazio")
	@Test
	public void testEsvaziaCarrinhoVazio() {
		carrinho.esvazia();
		
		assertEquals(0, carrinho.getQtdeItems());
		assertEquals(0.0, carrinho.getValorTotal(), 0.01);
	}

}
