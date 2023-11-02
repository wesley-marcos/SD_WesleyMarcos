package br.inatel.labs.labrest.server.controller;

import br.inatel.labs.labrest.server.model.Produto;
import br.inatel.labs.labrest.server.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> getProdutos(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable("id") Long produtoId){
        Optional<Produto> opProduto = service.findById(produtoId);

        if(opProduto.isEmpty()) {
            String msgErro = String.format("Nehum produto encontrado com id [%s]", produtoId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgErro);
        }

        return opProduto.get();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto postProduto(@RequestBody Produto p){
        Produto produtoCriado = service.create(p);
        return produtoCriado;
    }
}
