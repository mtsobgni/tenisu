package fr.atelier.tenisu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.atelier.tenisu.controller.model.PlayerStatistic;
import fr.atelier.tenisu.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    PlayerService playerService;

    @Spy
    @InjectMocks
    PlayerJsonFileService playerJsonFileService = new PlayerJsonFileService(new ObjectMapper());

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(playerService, "playerJsonFileService", playerJsonFileService);
    }

    @Test
    void shouldCorrectlyReturnListOfPlayers() throws IOException {
        //Given

        //When
        List<Player> result = playerService.getPlayerList();

        //Then
        Assertions.assertNotNull(result);
        Player firstPlayer = result.get(0);
        Player lastPlayer = result.get(4);
        Assertions.assertEquals("Rafael", firstPlayer.getFirstname());
        Assertions.assertEquals("Venus", lastPlayer.getFirstname());
    }

    @Test
    void shouldCorrectlyReturnAPlayerByIsId() throws IOException {
        //Given
        Integer id = 52;

        //When
        Player result = playerService.getPlayerById(id);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Novak", result.getFirstname());
        Assertions.assertEquals(52, result.getId());
    }

    @Test
    void computeStatistic() throws IOException {
        //Given

        //When
        PlayerStatistic result = playerService.computePlayerStatistics();

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("SRB", result.getCountry().getCode());
        Assertions.assertEquals(185.0, result.getMediane());
        Assertions.assertEquals(23, (int) result.getImc());
    }
}
