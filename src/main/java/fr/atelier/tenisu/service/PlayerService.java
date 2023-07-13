package fr.atelier.tenisu.service;

import fr.atelier.tenisu.controller.model.PlayerStatistic;
import fr.atelier.tenisu.model.Country;
import fr.atelier.tenisu.model.Data;
import fr.atelier.tenisu.model.Player;
import fr.atelier.tenisu.model.PlayerResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerJsonFileService playerJsonFileService;

    public PlayerService(PlayerJsonFileService playerJsonFileService) {
        this.playerJsonFileService = playerJsonFileService;
    }

    private List<Player> getPlayers() throws IOException {
        return Optional.ofNullable(playerJsonFileService.extractPlayersOnJsonFile()).map(PlayerResponse::getPlayers).orElse(new ArrayList<>());
    }

    public List<Player> getPlayerList() throws IOException {
        List<Player> result = getPlayers().stream().sorted(Comparator.comparingInt(o -> {
                    Integer rank = Optional.ofNullable(o).map(Player::getData).map(Data::getRank).orElse(null);
                    return rank != null ? rank : 0;
                }))
                .collect(Collectors.toList());
        return result;
    }

    public Player getPlayerById(Integer id) throws IOException {
        Optional<Player> player = getPlayers().stream().filter(p -> id.equals(p.getId())).findFirst();
        if (player.isPresent()) {
            return player.get();
        } else {
            return null;
        }
    }

    public PlayerStatistic computePlayerStatistics() throws IOException {
        List<Player> players = getPlayers();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        Country country = computeCountry(players);
        playerStatistic.setCountry(country);
        double averageImc = averageImc(players);
        playerStatistic.setImc(averageImc);
        double mediane = mediane(players);
        playerStatistic.setMediane(mediane);
        return playerStatistic;
    }

    private double mediane(List<Player> players) {
        double mediane = 0;
        List<Integer> heights = players.stream().map(Player::getData).map(Data::getHeight).sorted().collect(Collectors.toList());
        int size = heights.size();
        int rang = (size + 1) / 2;
        if (size % 2 != 0) {
            mediane = heights.get(rang);
        } else {
            mediane = (heights.get(rang) + heights.get(rang + 1)) / 2;
        }
        return mediane;
    }

    private double averageImc(List<Player> players) {
        return players.stream().mapToDouble(value -> value.getImc()).average().getAsDouble();
    }

    private Country computeCountry(List<Player> players) {
        Country country = new Country();
        Player player = players.stream().sorted((o1, o2) -> Math.toIntExact(o2.gameWin() - o1.gameWin())).findFirst().orElse(null);
        if (player != null) {
            country = player.getCountry();
        }
        return country;
    }

}
