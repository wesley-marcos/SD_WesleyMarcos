package br.inatel.labs.labrest.server;

import br.inatel.labs.labrest.server.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void dadoProdutoIdInvalido_quandoDeleteProdutoPeloId_entaoRespondeComStatusNotFound(){
		Long produtoIdValido = 99L;

		webTestClient.delete()
				.uri("/produto/" + produtoIdValido)
				.exchange()
				.expectStatus().isNotFound();
	}
	@Test
	void dadoProdutoIdValido_quandoDeleteProdutoPeloId_entaoRespondeComStatusNoContent(){
		Long produtoIdValido = 2L;

		webTestClient.delete()
				.uri("/produto/" + produtoIdValido)
				.exchange()
				.expectStatus().isNoContent();
	}

	@Test
	void dadoProdutoExistente_quandoPutProduto_entaoRespondeComStatusNoContent(){
		Produto produtoExistente = new Produto();
		produtoExistente.setId(1L);
		produtoExistente.setDescricao("Furadeira a bateria");
		produtoExistente.setPreco(new BigDecimal(2000.0));

		webTestClient.put()
				.uri("/produto")
				.bodyValue(produtoExistente)
				.exchange()
				.expectStatus().isNoContent();
	}

	@Test
	void dadoNovoProduto_quandoPostProduto_entaoRespondeComStatusCreatedEProdutoValido(){
		Produto novoProduto = new Produto();
		novoProduto.setDescricao("Tupia de mesa");
		novoProduto.setPreco(new BigDecimal(9000.0));

		Produto produtoRespondido = webTestClient.post()
				.uri("/produto")
				.bodyValue(novoProduto)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Produto.class)
					.returnResult()
					.getResponseBody();

		assertThat(produtoRespondido).isNotNull();
		assertThat(produtoRespondido.getId()).isNotNull();
	}
	@Test
	void dadoProdutoInvalido_quandoGetProdutoPeloId_entaoRespondeComStatusNotFound(){
		Long idInvalido = 99L;

		webTestClient.get()
				.uri("/produto/" + idInvalido)
				.exchange()
				.expectStatus().isNotFound();
	}
	@Test
	void dadoProdutoValido_quandoGetProdutoPeloId_entaoRespondeComProdutoValido(){
		Long produtoIdValido = 1L;

		Produto produtoRespondido = webTestClient.get()
				.uri("/produto/" + produtoIdValido)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Produto.class)
					.returnResult()
					.getResponseBody();


		assertNotNull(produtoRespondido);
		assertEquals(produtoRespondido.getId(), produtoIdValido);
	}
	@Test
	void deveListarProdutos() {
		webTestClient.get()
				.uri("/produto")
				.exchange()
				.expectStatus()
					.isOk()
				.expectBody()
					.returnResult()
		;
	}

}
