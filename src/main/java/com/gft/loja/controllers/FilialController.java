package com.gft.loja.controllers;

import com.gft.loja.dto.filial.ConsultaFilialDTO;
import com.gft.loja.dto.filial.FilialMapper;
import com.gft.loja.dto.filial.RegistroFilialDTO;
import com.gft.loja.entities.Filial;
import com.gft.loja.services.FilialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/filiais")
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaFilialDTO>> buscarTodasAsFiliais(@PageableDefault(size = 3) Pageable pageable) {

        return ResponseEntity.ok(filialService.listarTodasAsFilias(pageable).map(FilialMapper::fromEntity));
    }

    @PostMapping
    public ResponseEntity<ConsultaFilialDTO> salvarFilial(@RequestBody RegistroFilialDTO dto) {
        Filial filial = filialService.salvarFilial(FilialMapper.fromDTO(dto));

        return ResponseEntity.ok(FilialMapper.fromEntity(filial));
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> buscarFilial(@PathVariable Long id) {

            Filial filial = filialService.buscarFilial(id);

            return ResponseEntity.ok(FilialMapper.fromEntity(filial));

    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> alterarFilial(@RequestBody RegistroFilialDTO dto, @PathVariable Long id) {

        try {
            Filial filial = filialService.atualizarFilial(FilialMapper.fromDTO(dto), id);

            return ResponseEntity.ok(FilialMapper.fromEntity(filial));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> excluirFilial(@PathVariable Long id) {

        try {
            filialService.excluirFilial(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
     }

}
