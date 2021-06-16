package com.maha.watchstore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "discount")
@Data
@NoArgsConstructor
public class Discount {
    @Id
    private Long id;
    @NonNull
    private int units;
    @NonNull
    private Long price;
    @NonNull

    @OneToOne(mappedBy = "discount", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        return id != null && id.equals(((Discount) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
