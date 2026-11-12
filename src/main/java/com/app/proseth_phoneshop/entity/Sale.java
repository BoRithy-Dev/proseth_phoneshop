package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.core.SpringVersion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long id;
    @Column(name="sold_date")
    private LocalDate soleDate;
    private Boolean active;
//    @Column(name="invoice_no")
//    private String invoiceNo;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="customer_id", nullable = false)
//    private Customer customer;
//    @Column(name="total_amount",precision = 12,scale = 2)
//    private BigDecimal totalAmount;
//    @Column(name="payment_method")
//    private String paymentMethod;
}
