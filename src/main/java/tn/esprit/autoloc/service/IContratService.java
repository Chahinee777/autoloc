package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat create(Contrat contrat);
    Contrat update(Long id, Contrat contrat);
    void delete(Long id);
    Contrat findById(Long id);
    List<Contrat> findAll();
}
