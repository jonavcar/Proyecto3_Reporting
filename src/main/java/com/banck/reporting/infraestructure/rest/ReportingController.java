package com.banck.reporting.infraestructure.rest;

import com.banck.reporting.aplication.AccountOperations;
import com.banck.reporting.domain.ProductSummaryDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.banck.reporting.aplication.CreditOperations;
import com.banck.reporting.aplication.MovementOperations;
import com.banck.reporting.domain.DateIDateF;
import com.banck.reporting.domain.ProductMovementDto;
import com.banck.reporting.utils.DateValidator;
import com.banck.reporting.utils.DateValidatorUsingLocalDate;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jonavcar
 */
@RestController
@RequestMapping("/banck-report")
@RequiredArgsConstructor
public class ReportingController {

    Logger logger = LoggerFactory.getLogger(ReportingController.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Bogota"));
    private final CreditOperations creditOperations;
    private final AccountOperations accountOperations;
    private final MovementOperations movementOperations;

    @GetMapping("/product/summary/{customer}/list")
    public Flux<ProductSummaryDto> listByCustomer(@PathVariable("customer") String customer) {
        return Flux.merge(creditOperations.listByCustomer(customer), accountOperations.listByCustomer(customer));
    }

    @PostMapping("/product/movement/{customer}/list")
    public Flux<ProductMovementDto> ProductMovementByCustomerAndDate(@PathVariable("customer") String customer, @RequestBody DateIDateF didf) {

        DateValidator validator = new DateValidatorUsingLocalDate(formatDate);

        if (!validator.isValid(didf.getDateI())) {
            Throwable t = new Throwable();
            return Flux.error(t, true);
        }

        if (!validator.isValid(didf.getDateF())) {
            Throwable t = new Throwable();
            return Flux.error(t, false);
        }

        return movementOperations.listMovementByCustomerAndDates(customer, didf);
    }

}
