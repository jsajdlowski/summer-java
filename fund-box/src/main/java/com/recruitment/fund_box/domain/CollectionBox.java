package com.recruitment.fund_box.domain;

import com.recruitment.fund_box.enums.Currency;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Entity
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "collection_box_balance", joinColumns = @JoinColumn(name = "collection_box_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "currency")
    @Column(name = "amount")
    private Map<Currency, BigDecimal> balance = new HashMap<>();
    @ManyToOne
    @JoinColumn(name = "fundraising_event_id")
    private FundraisingEvent fundraisingEvent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Currency, BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(Map<Currency, BigDecimal> balance) {
        this.balance = balance != null ? balance : new HashMap<>();
    }

    public FundraisingEvent getFundraisingEvent() {
        return fundraisingEvent;
    }

    public void setFundraisingEvent(FundraisingEvent fundraisingEvent) {
        this.fundraisingEvent = fundraisingEvent;
    }

    public boolean isEmpty() {
        return balance.isEmpty();
    }

}
