package themeansquare.service;

import java.util.Optional;

import themeansquare.model.Reservation;

public interface IReservation {
    public String addReservation(Reservation newReservation) throws Exception;
    public String addReservationOld() throws Exception;
    public Iterable<Reservation> getReservations() throws Exception;
    public Optional<Reservation> getReservationById(Integer id) throws Exception;
    public Iterable<Reservation> getReservationByCustomerId(Integer customerId) throws Exception;
    public String cancelReservation(Integer reservationId, Boolean isLatefee) throws Exception;
    public String getEstimatedPriceForVehicles (Integer locationId, String startTime, String endTime) throws Exception;
}