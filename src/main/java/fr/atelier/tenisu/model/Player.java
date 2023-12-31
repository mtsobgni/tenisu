package fr.atelier.tenisu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Integer id;
    private String firstname;
    private String lastname;
    private String shortname;
    private Sex sex;
    private Country country;
    private String picture;
    private Data data;

    public double getImc() {
        return data.computeImc();
    }

    public long gameWin() {
        return data.gameWin();
    }
}
