package com.poketrader.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Date createdAt;

    private boolean status;

    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TradedPokemon> tradedPokemon;

}
