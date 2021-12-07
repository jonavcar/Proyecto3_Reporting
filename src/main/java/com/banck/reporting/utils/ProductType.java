/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banck.reporting.utils;

/**
 *
 * @author jonavcar
 */
public enum ProductType {
    //CREDITO-PERSONAL
    PERSONAL_CREDIT("CP") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    },
    //CREDITO-EMPRESARIAL
    BUSINESS_CREDIT("CE") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    },
    //TARGETA-CREDITO
    CREDIT_CARD("TC") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    },
    //TARGETA-DEBITO
    DEBIT_CARD("TD") {
        @Override
        public boolean equals(String customerType) {
            return value.equals(customerType);
        }
    };

    public final String value;

    public boolean equals(String customerType) {
        return value.equals(customerType);
    }

    private ProductType(String value) {
        this.value = value;
    }
}
