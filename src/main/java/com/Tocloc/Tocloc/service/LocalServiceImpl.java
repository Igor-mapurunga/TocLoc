package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.dao.LocalRepository;
import com.Tocloc.Tocloc.entities.Local;
import com.Tocloc.Tocloc.exceptions.LocalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {
    @Autowired
    private LocalRepository localRepository;
    @Override
    public List<Local> findAll() {
        return localRepository.findAll();
    }
    @Override
    public Local findById(Long localId) {
        return localRepository.findById(localId)
                .orElseThrow(() -> new LocalNotFoundException("Local not found with id - " + localId));
    }
    @Override
    public Local save(Local local) {
        return localRepository.save(local);
    }
    @Override
    public Local update(Long id, Local localDetails) {
        Local localExistente = findById(id);
        localExistente.setNome(localDetails.getNome());
        localExistente.setEndereco(localDetails.getEndereco());
        localExistente.setDescricao(localDetails.getDescricao());
        localExistente.setTipoEspaco(localDetails.getTipoEspaco());
        localExistente.setCapacidade(localDetails.getCapacidade());
        localExistente.setPrecoPorHora(localDetails.getPrecoPorHora());
        localExistente.setProprietario(localDetails.getProprietario());
        localExistente.setUsuarioLocador(localDetails.getUsuarioLocador());
        return localRepository.save(localExistente);
    }
    @Override
    public void deleteById(Long localId) {
        Local localToDelete = findById(localId);
        localRepository.deleteById(localToDelete.getId());
    }
}
