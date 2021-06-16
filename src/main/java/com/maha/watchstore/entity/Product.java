package com.maha.watchstore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    private Long id;
    @NonNull
    private Long price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discountId")
    Discount discount;

}
