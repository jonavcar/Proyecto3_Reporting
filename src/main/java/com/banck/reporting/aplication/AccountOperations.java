/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banck.reporting.aplication;

import com.banck.reporting.domain.ProductSummaryDto;
import reactor.core.publisher.Flux;

/**
 *
 * @author jnacarra
 */
public interface AccountOperations {

    public Flux<ProductSummaryDto> listByCustomer(String customer);
}
