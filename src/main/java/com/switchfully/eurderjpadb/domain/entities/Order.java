package com.switchfully.eurderjpadb.domain.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order", schema = "eurder")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id_fk")
    private User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<Itemgroup> itemgroups;


    public BigDecimal getTotalPriceOfOrder() {
        return itemgroups.stream()
                .map(Itemgroup::getOrderlineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
