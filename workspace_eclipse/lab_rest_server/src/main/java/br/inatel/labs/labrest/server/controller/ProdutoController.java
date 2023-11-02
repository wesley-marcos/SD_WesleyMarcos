package br.inatel.labs.labrest.server.controller;

import br.inatel.labs.labrest.server.model.Produto;
import br.inatel.labs.labrest.server.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> getProdutos(){
        return service.findAll();
    }
}
