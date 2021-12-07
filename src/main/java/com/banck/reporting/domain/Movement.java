package com.banck.reporting.domain;

import lombok.Data;

/**
 *
 * @author jonavcar
 */
@Data
public class Movement {

    public String movement;
    public String movementType;
    public String schedule;
    public String product;
    public String modality;
    public String concept;
    public String customer;
    public int amount;
    public String observations;
    public String date;
    public String time;
    public String status;
}
