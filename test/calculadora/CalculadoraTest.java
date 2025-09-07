package calculadora;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void inicializa() {
		calc = new Calculadora();
	}
	
	@DisplayName("Testa a soma de dois n�meros")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}
	
	@Test
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertTrue(divisao == 2);
	}
	
	@Test
	public void testDivisaoPorZero() {
		try {
			calc.divisao(8, 0);
			fail("Exce��o n�o lan�ada");
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@Test
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}
	
	@DisplayName("Testa a subtração de dois números")
	@Test
	public void testSubtracaoDoisNumeros() {
		int subtracao = calc.subtracao(10, 3);
		assertEquals(7, subtracao);
	}
	
	@DisplayName("Testa a subtração com resultado negativo")
	@Test
	public void testSubtracaoResultadoNegativo() {
		int subtracao = calc.subtracao(3, 10);
		assertEquals(-7, subtracao);
	}
	
	@DisplayName("Testa a multiplicação de dois números")
	@Test
	public void testMultiplicacaoDoisNumeros() {
		int multiplicacao = calc.multiplicacao(4, 5);
		assertEquals(20, multiplicacao);
	}
	
	@DisplayName("Testa a multiplicação por zero")
	@Test
	public void testMultiplicacaoPorZero() {
		int multiplicacao = calc.multiplicacao(10, 0);
		assertEquals(0, multiplicacao);
	}
	
	@DisplayName("Testa a multiplicação de números negativos")
	@Test
	public void testMultiplicacaoNumerosNegativos() {
		int multiplicacao = calc.multiplicacao(-3, -4);
		assertEquals(12, multiplicacao);
	}
	
	@DisplayName("Testa a somatória de um número positivo")
	@Test
	public void testSomatoriaNumeroPositivo() {
		int somatoria = calc.somatoria(5);
		assertEquals(15, somatoria); // 5+4+3+2+1+0 = 15
	}
	
	@DisplayName("Testa a somatória de zero")
	@Test
	public void testSomatoriaZero() {
		int somatoria = calc.somatoria(0);
		assertEquals(0, somatoria);
	}
	
	@DisplayName("Testa a somatória de um número negativo")
	@Test
	public void testSomatoriaNumeroNegativo() {
		int somatoria = calc.somatoria(-3);
		assertEquals(0, somatoria); // while (n >= 0) não executa
	}
	
	@DisplayName("Testa se um número positivo é identificado como positivo")
	@Test
	public void testEhPositivoNumeroPositivo() {
		assertTrue(calc.ehPositivo(5));
	}
	
	@DisplayName("Testa se zero é identificado como positivo")
	@Test
	public void testEhPositivoZero() {
		assertTrue(calc.ehPositivo(0));
	}
	
	@DisplayName("Testa se um número negativo é identificado como não positivo")
	@Test
	public void testEhPositivoNumeroNegativo() {
		assertTrue(!calc.ehPositivo(-5));
	}
	
	@DisplayName("Testa comparação quando a == b")
	@Test
	public void testComparaIguais() {
		int resultado = calc.compara(5, 5);
		assertEquals(0, resultado);
	}
	
	@DisplayName("Testa comparação quando a > b")
	@Test
	public void testComparaAMaiorQueB() {
		int resultado = calc.compara(10, 5);
		assertEquals(1, resultado);
	}
	
	@DisplayName("Testa comparação quando a < b")
	@Test
	public void testComparaAMenorQueB() {
		int resultado = calc.compara(3, 8);
		assertEquals(-1, resultado);
	}


}
