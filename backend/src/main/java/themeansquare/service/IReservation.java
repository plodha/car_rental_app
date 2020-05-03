package themeansquare.service;

import themeansquare.model.Reservation;

public interface IReservation {
    public String addReservation() throws Exception;
    public Iterable<Reservation> getReservations() throws Exception;
}