package fr.atelier.tenisu.controller.model;

import fr.atelier.tenisu.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatistic {

    private Country country;
    private double imc;
    private double mediane;

}
