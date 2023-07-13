package fr.atelier.tenisu.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerResponse {

    @Builder.Default
    private List<Player> players = new ArrayList<>();
}
