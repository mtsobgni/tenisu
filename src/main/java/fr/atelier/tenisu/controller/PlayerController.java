package fr.atelier.tenisu.controller;

import fr.atelier.tenisu.controller.model.PlayerStatistic;
import fr.atelier.tenisu.model.Player;
import fr.atelier.tenisu.model.PlayerResponse;
import fr.atelier.tenisu.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @Operation(summary = "Get all players order by rank", description = "Get all players order by rank")
    @GetMapping(value = "players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Player>> getPlayers() throws IOException {
        List<Player> players = playerService.getPlayerList();
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Get player by id", description = "Get player by id")
    @GetMapping(value = "players/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayer(@PathVariable Integer id) throws IOException {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return  ResponseEntity.ok(player);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Return stats players", description = "Return stats players")
    @GetMapping(value = "players/player-statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerStatistic> computeStatisticPlayer() throws IOException {
        PlayerStatistic playerStatistic = playerService.computePlayerStatistics();
        return ResponseEntity.ok(playerStatistic);
    }
}
