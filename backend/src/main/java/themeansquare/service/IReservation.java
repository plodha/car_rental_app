package themeansquare.service;

import themeansquare.model.Reservation;

public interface IReservation {
    public String addReservation(Reservation newReservation) throws Exception;
    public String addReservationOld() throws Exception;
    public Iterable<Reservation> getReservations() throws Exception;
}