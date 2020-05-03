package themeansquare.service;


import java.util.Optional;

import themeansquare.model.Invoice;

public interface IInvoice {
    public String computeInvoice(Integer reservationId, String actualDropOffTime, Boolean IsDamage,String[] damageId) throws Exception;
    
}