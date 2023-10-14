package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.entity.Fornecedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FornecedorService {

    @PersistenceContext
    private EntityManager em;

    public Fornecedor salvar(Fornecedor f) {
        return em.merge(f);
    }

    public Fornecedor buscarPeloId(Long id) {
        return em.find(Fornecedor.class, id);
    }
    public List<Fornecedor> listar(){
        String jpql = "select f from Fornecedor f";
        List<Fornecedor> fornecedores = em.createQuery(jpql, Fornecedor.class).getResultList();
        return fornecedores;
    }

    public void remover(Fornecedor f) {
        em.remove(em.merge(f));
    }
}
