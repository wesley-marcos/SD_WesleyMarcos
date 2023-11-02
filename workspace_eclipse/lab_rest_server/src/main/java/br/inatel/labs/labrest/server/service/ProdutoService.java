package br.inatel.labs.labrest.server.service;

import br.inatel.labs.labrest.server.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    List<Produto> produtos = new ArrayList<>();

    public List<Produto> findAll(){
        return this.produtos;
    }
}
