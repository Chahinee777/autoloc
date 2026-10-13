package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance create(Maintenance maintenance);
    Maintenance update(Long id, Maintenance maintenance);
    void delete(Long id);
    Maintenance findById(Long id);
    List<Maintenance> findAll();
}
