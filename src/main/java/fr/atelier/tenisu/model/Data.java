package fr.atelier.tenisu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    private Integer rank;
    private Integer points;
    private Integer weight;
    private Integer height;
    private Integer age;
    private List<Integer> last;

    public double computeImc() {
        double newWeight = (double) weight/1000;
        double newHeight = (double) height/100;
        return newWeight / Math.pow(newHeight, 2);
    }

    public long gameWin() {
        return last.stream().filter(x -> x != 0).count();
    }
}
