package com.banck.reporting.aplication;

import com.banck.reporting.domain.DateIDateF;
import com.banck.reporting.domain.ProductMovementDto;
import reactor.core.publisher.Flux;

/**
 *
 * @author jonavcar
 */
public interface CreditMovementOperations {

    public Flux<ProductMovementDto> listByCustomerAndDates(String customer, DateIDateF didf);

}
