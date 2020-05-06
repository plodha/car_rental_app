package themeansquare.service;


import java.util.Optional;

import themeansquare.model.Invoice;

public interface IInvoice {
    public int computeInvoice(Integer reservationId, String actualDropOffTime, Boolean IsDamage,String[] damageId) throws Exception;
    public Iterable<Invoice> getInvoices() throws Exception;
    public Optional<Invoice> getInvoiceById(Integer id) throws Exception;
}