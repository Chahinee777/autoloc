package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence create(Agence agence);
    Agence update(Long id, Agence agence);
    void delete(Long id);
    Agence findById(Long id);
    List<Agence> findAll();
}
