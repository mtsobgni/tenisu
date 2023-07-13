package fr.atelier.tenisu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.atelier.tenisu.model.Player;
import fr.atelier.tenisu.model.PlayerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class PlayerJsonFileServiceTest {

    @InjectMocks
    PlayerJsonFileService playerJsonFileService;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void shouldCorrectlyExtractDataFromJsonFile() throws IOException {
        //Given
        ReflectionTestUtils.setField(playerJsonFileService, "objectMapper", objectMapper);

        //When
        PlayerResponse result = playerJsonFileService.extractPlayersOnJsonFile();

        //Then
        Assertions.assertNotNull(result.getPlayers());
        Assertions.assertEquals(5, result.getPlayers().size());
        Player player = result.getPlayers().get(0);
        Assertions.assertEquals(52, player.getId());
    }
}
