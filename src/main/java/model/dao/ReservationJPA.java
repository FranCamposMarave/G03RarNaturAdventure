package model.dao;

import model.entities.Reservation;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationJPA {

    public static Reservation NULL = new Reservation();

    @Inject
    private ActivityJPA activityDAO;

    @PersistenceContext(unitName = "naturAdventureJTA")
    EntityManager em;

    public Reservation[] listAll() {
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.getAll", Reservation.class);
        List<Reservation> ReservationsList = query.getResultList();
        Reservation[] Reservations = new Reservation[ReservationsList.size()];
        ReservationsList.toArray(Reservations);
        return Reservations;
    }

    public Reservation get(Long id) {
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.get", Reservation.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return NULL;
        }
    }

    public boolean delete(Long id) {
        Reservation reservation = get(id);
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.deleteById", Reservation.class);
        query.setParameter("id", id);
        try {
            int deletedRows = query.executeUpdate();
            if(deletedRows == 1){
                activityDAO.increaseRemainingPlaces(reservation.getActivity().getId(),reservation.getPlaces());
            }
            return deletedRows == 1;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean add(Reservation reservation) {
        em.persist( reservation );
        activityDAO.decreseRemainingPlaces(reservation.getActivity().getId(),reservation.getPlaces());
        return true;
    }

    public boolean update(Reservation Reservation) {
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.get", Reservation.class);
        query.setParameter("id", Reservation.getId());
        try {
            Reservation oldReservation = query.getSingleResult();
            oldReservation.setNif(Reservation.getNif());
            oldReservation.setName(Reservation.getName());
            oldReservation.setLastName(Reservation.getLastName());
            oldReservation.setActivity(Reservation.getActivity());
            oldReservation.setPlaces(Reservation.getPlaces());
            oldReservation.setConfirmed(Reservation.isConfirmed());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
