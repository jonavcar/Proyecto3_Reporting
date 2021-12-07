/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banck.reporting.utils;

/**
 *
 * @author jonavcar
 */
public enum Status {
    //PENDIENTE
    PENDING("PENDIENTE") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    },
    //PAGADO
    PAID_OUT("PAGADO") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    },
    //VENCIDO
    DEFEATED("VENCIDO") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    };

    public final String value;

    public boolean equals(String customerType) {
        return value.equals(customerType);
    }

    private Status(String value) {
        this.value = value;
    }
}
