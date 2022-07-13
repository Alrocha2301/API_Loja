package com.gft.loja.controllers;

import com.gft.loja.dto.produto.ConsultaProdutoDTO;
import com.gft.loja.dto.produto.ProdutoMapper;
import com.gft.loja.dto.produto.RegistroProdutoDTO;
import com.gft.loja.entities.Produto;
import com.gft.loja.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaProdutoDTO>> listarProdutos(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarTodosOsProdutos(pageable).map(ProdutoMapper::fromEntity));
    }

    @PostMapping
    public ResponseEntity<ConsultaProdutoDTO> salvarProduto(@RequestBody RegistroProdutoDTO dto) {
        Produto produto = produtoService.salvarProduto(ProdutoMapper.fromDTO(dto));

        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaProdutoDTO> buscarProduto(@PathVariable Long id) {
        Produto produto = produtoService.buscarProduto(id);

        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaProdutoDTO> alterarProduto(@PathVariable Long id, @RequestBody RegistroProdutoDTO dto) {
        try {
            Produto produto = produtoService.atualizarProduto(id, ProdutoMapper.fromDTO(dto));

            return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaProdutoDTO> excluirProduto(@PathVariable Long id) {
        try {
            produtoService.excluirProduto(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
