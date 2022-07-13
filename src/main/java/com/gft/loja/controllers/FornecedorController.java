package com.gft.loja.controllers;

import com.gft.loja.dto.fornecedor.ConsultaFornecedorDTO;
import com.gft.loja.dto.fornecedor.FornecedorMapper;
import com.gft.loja.dto.fornecedor.RegistroFornecedorDTO;
import com.gft.loja.entities.Fornecedor;
import com.gft.loja.services.FornecedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaFornecedorDTO>> buscarTodosOsFornecedores(@PageableDefault Pageable pageable) {

        return ResponseEntity.ok(fornecedorService.listarTodosOsFornecedores(pageable).map(FornecedorMapper::fromEntity));
    }

    @PostMapping
    public ResponseEntity<ConsultaFornecedorDTO> salvarFornecedor(@RequestBody RegistroFornecedorDTO dto) {
        Fornecedor fornecedor = fornecedorService.salvarFornecedor(FornecedorMapper.fromDTO(dto));

        return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaFornecedorDTO> buscarFornecedor(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.buscarFornecedor(id);

        return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaFornecedorDTO> atualizarFornecedor(@PathVariable Long id, @RequestBody RegistroFornecedorDTO dto) {

        try {
            Fornecedor fornecedor = fornecedorService.atualizarFornecedor(FornecedorMapper.fromDTO(dto), id);
            return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaFornecedorDTO> excluirFornecedor(@PathVariable Long id) {
        try {
            fornecedorService.excluirFornecedor(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
