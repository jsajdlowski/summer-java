package com.recruitment.fund_box.domain;

import com.recruitment.fund_box.enums.Currency;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FundraisingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Currency accountCurrency;
    
    @Column(nullable = false)
    private BigDecimal funds = BigDecimal.ZERO;
    
    @OneToMany(mappedBy = "fundraisingEvent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CollectionBox> collectionBoxes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(Currency accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public List<CollectionBox> getCollectionBoxes() {
        return collectionBoxes;
    }

    public void setCollectionBoxes(List<CollectionBox> collectionBoxes) {
        this.collectionBoxes = collectionBoxes;
    }
}
