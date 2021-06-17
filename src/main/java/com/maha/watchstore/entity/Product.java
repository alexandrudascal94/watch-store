package com.maha.watchstore.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    private Long id;
    @NonNull
    private Long price;
    @NotNull
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discountId")
    Discount discount;

    public boolean hasDiscount(){
        return Optional.ofNullable(discount).isPresent();
    }
}
