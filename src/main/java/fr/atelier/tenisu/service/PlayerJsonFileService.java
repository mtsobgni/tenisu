package fr.atelier.tenisu.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.atelier.tenisu.model.PlayerResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PlayerJsonFileService {

    private final ObjectMapper objectMapper;
    private static final String JSON_FILE = "players.json";

    public PlayerJsonFileService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PlayerResponse extractPlayersOnJsonFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(JSON_FILE);
        InputStream inputStream = resource.getInputStream();
        PlayerResponse players = objectMapper.readValue(inputStream, new TypeReference<PlayerResponse>() {});
        return players;
    }
}
