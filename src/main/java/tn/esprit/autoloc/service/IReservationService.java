package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation create(Reservation reservation);
    Reservation update(Long id, Reservation reservation);
    void delete(Long id);
    Reservation findById(Long id);
    List<Reservation> findAll();
}
