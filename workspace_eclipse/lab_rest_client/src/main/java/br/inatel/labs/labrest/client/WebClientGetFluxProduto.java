package br.inatel.labs.labrest.client;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class WebClientGetFluxProduto {



    public static void main(String[] args) {
        List<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();

        Flux<ProdutoDTO> fluxProduto = WebClient.create(Constantes.BASE_URL)
                .get()
                .uri("/produto")
                .retrieve()
                .bodyToFlux(ProdutoDTO.class);

        fluxProduto.subscribe(p -> listaProduto.add(p));

        fluxProduto.blockLast();

        System.out.println("Lista de Produto:");
        System.out.println(listaProduto);
    }
}
