package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Agence create(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence update(Long id, Agence agence) {
        Agence existing = findById(id);
        existing.setNom(agence.getNom());
        existing.setVille(agence.getVille());
        existing.setAdresse(agence.getAdresse());
        existing.setTelephone(agence.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        agenceRepository.deleteById(id);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable avec id: " + id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        // keep the vehicles aside: they can't be saved before the agency exists (id_agence is NOT NULL)
        List<Vehicule> vehicules = new ArrayList<>(agence.getVehicules());
        agence.setVehicules(new ArrayList<>());

        // 1. save the agency
        Agence savedAgence = agenceRepository.save(agence);

        // 2. assign each vehicle to the agency, then save them
        for (Vehicule v : vehicules) {
            v.setAgence(savedAgence);
        }
        List<Vehicule> savedVehicules = vehiculeRepository.saveAll(vehicules);
        savedAgence.getVehicules().addAll(savedVehicules);

        return savedAgence;
    }
}