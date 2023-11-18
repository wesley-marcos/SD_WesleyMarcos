package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class WebClientGetMonoProdutoPeloIDd {

    public static void main(String[] args) {

        try{
            var monoProduto = WebClient.create(Constantes.BASE_URL)
                    .get()
                    .uri("/produto/4")
                    .retrieve()
                    .bodyToMono(ProdutoDTO.class);

            monoProduto.subscribe();

            ProdutoDTO produtoRetornado = monoProduto.block();

            System.out.println("Produto:");
            System.out.println(produtoRetornado);
        } catch (WebClientResponseException e){
            System.out.println("Status code: " + e.getStatusCode());
            System.out.println("Message: " + e.getMessage());
        }
    }
}
