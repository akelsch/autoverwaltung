package de.htwsaar.prog3.carrental.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Rental model for Hibernate.
 *
 * @author Julian Quint
 */
@Entity
@Table
@Data
public class Rental {
    public Rental() {
    }

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private String begin;

    @Column(nullable = false)
    private String end;

    @Column
    private String note;

    @Column(name = "extra_costs", nullable = false)
    private String extraCosts;
}
