package com.gft.loja.services;

import com.gft.loja.entities.Produto;
import com.gft.loja.exception.EntityNotFoundException;
import com.gft.loja.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Page<Produto> listarTodosOsProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto buscarProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new EntityNotFoundException("Produtp não encontrado!"));
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        Produto produtoOriginal = this.buscarProduto(id);

        produto.setId(produtoOriginal.getId());

        return produtoRepository.save(produto);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
