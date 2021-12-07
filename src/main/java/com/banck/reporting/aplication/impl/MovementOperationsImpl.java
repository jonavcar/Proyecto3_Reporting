package com.banck.reporting.aplication.impl;

import com.banck.reporting.aplication.AccountMovementOperations;
import com.banck.reporting.aplication.CreditMovementOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.banck.reporting.aplication.MovementOperations;
import com.banck.reporting.domain.DateIDateF;
import com.banck.reporting.domain.ProductMovementDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jonavcar
 */
@Service
@RequiredArgsConstructor
public class MovementOperationsImpl implements MovementOperations {

    org.slf4j.Logger logger = LoggerFactory.getLogger(MovementOperationsImpl.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Bogota"));
    private final CreditMovementOperations creditMovementOperations;
    private final AccountMovementOperations accountMovementOperations;

    @Override
    public Flux<ProductMovementDto> listMovementByCustomerAndDates(String customer, DateIDateF didf) {
        return Flux.merge(accountMovementOperations.listByCustomerAndDates(customer, didf),
                creditMovementOperations.listByCustomerAndDates(customer, didf));
    }

    public boolean isDateRange(String strDateI, String strDateF, String strDateC) {
        LocalDate dateI = LocalDate.parse(strDateI, formatDate);
        LocalDate dateF = LocalDate.parse(strDateF, formatDate);
        LocalDate dateC = LocalDate.parse(strDateC, formatDate);
        return ((dateC.isAfter(dateI) || dateC.isEqual(dateI)) && (dateC.isBefore(dateF) || dateC.isEqual(dateF)));
    }

}
