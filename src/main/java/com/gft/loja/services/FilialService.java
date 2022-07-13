package com.gft.loja.services;

import com.gft.loja.entities.Filial;
import com.gft.loja.exception.EntityNotFoundException;
import com.gft.loja.repositories.FilialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilialService {

    private final FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    public Filial salvarFilial(Filial filial) {
        return filialRepository.save(filial);
    }

    public Page<Filial> listarTodasAsFilias(Pageable pageable) {

        return filialRepository.findAll(pageable);
    }

    public Filial buscarFilial(Long id) {

        Optional<Filial> filial = filialRepository.findById(id);

        return filial.orElseThrow(() -> new EntityNotFoundException("Filial não encontrada!"));
    }

    public Filial atualizarFilial(Filial filial, Long id) {
        Filial filialOriginal = this.buscarFilial(id);

        filial.setId(filialOriginal.getId());

        return filialRepository.save(filial);
    }

    public void excluirFilial(Long id) {
        Filial filial = this.buscarFilial(id);

        filialRepository.deleteById(id);
    }
}
