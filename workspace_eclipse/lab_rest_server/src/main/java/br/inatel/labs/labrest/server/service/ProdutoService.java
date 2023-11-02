package br.inatel.labs.labrest.server.service;

import br.inatel.labs.labrest.server.model.Produto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    List<Produto> produtos = new ArrayList<>();
    @PostConstruct
    private void setup(){
        Produto p1 = new Produto(1l, "Furadeira", new BigDecimal(230.00));
        Produto p2 = new Produto(2l, "Serra Circular", new BigDecimal(500.00));
        Produto p3 = new Produto(3l, "Parafusadeira", new BigDecimal(400.00));

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
    }

    public List<Produto> findAll(){
        return this.produtos;
    }
}
