package com.banar.labs;

import com.banar.labs.model.FootballPlayers;
import com.banar.labs.repository.FootballPlayersRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataMongoTest
public class FootballPlayersRepositoryTest {

    @Autowired
    FootballPlayersRepository underTest;

    @BeforeEach
    void setUp() {
        FootballPlayers messi = new FootballPlayers("5", "Lionel", "Messi", 10, 36, "MLS", "Inter Miami", "Argentina", "CAM");
        FootballPlayers ronaldo = new FootballPlayers("6", "Cristiano", "Ronaldo", 7, 38, "Saudi Pro League", "Al-Nassr", "Portugal", "ST");
        FootballPlayers neymar = new FootballPlayers("7", "Neymar", "Jr", 10, 31, "Saudi Pro League", "Al-Hilal", "Brazil", "LW");
        underTest.saveAll(List.of(messi, ronaldo, neymar));
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testSetShouldContains3Players() {
        List<FootballPlayers> players = underTest.findAll();
        assertEquals(3, players.size());
    }

    @Test
    void testShouldFindPlayerById() {
        Optional<FootballPlayers> player = underTest.findById("5");
        assertTrue(player.isPresent());
        assertEquals("Lionel", player.get().getName());
    }

    @Test
    void testShouldSaveNewPlayer() {
        FootballPlayers newPlayer = new FootballPlayers("8", "Lamine", "Yamal", 19, 17, "La Liga", "FC Barcelona", "Spain", "RW");
        FootballPlayers savedPlayer = underTest.save(newPlayer);
        assertNotNull(savedPlayer.getId());
        assertEquals("Lamine", savedPlayer.getName());
    }

    @Test
    void testShouldDeletePlayerById() {
        underTest.deleteById("5");
        Optional<FootballPlayers> deletedPlayer = underTest.findById("5");
        assertFalse(deletedPlayer.isPresent());
    }

    @Test
    void testShouldUpdatePlayer() {
        FootballPlayers player = underTest.findById("6").get();
        player.setTeam("Manchester United");
        underTest.save(player);
        FootballPlayers updatedPlayer = underTest.findById("6").get();
        assertEquals("Manchester United", updatedPlayer.getTeam());
    }

    @Test
    void testShouldFindBySurname() {
        List<FootballPlayers> players = underTest.findAll().stream()
                .filter(player -> player.getSurname().equals("Ronaldo"))
                .toList();
        assertEquals(1, players.size());
        assertEquals("Cristiano", players.get(0).getName());
    }

    @Test
    void testShouldFindByTeam() {
        List<FootballPlayers> players = underTest.findAll().stream()
                .filter(player -> player.getTeam().equals("Inter Miami"))
                .toList();
        assertEquals(1, players.size());
    }

    @Test
    void testShouldFindPlayersByLeague() {
        List<FootballPlayers> players = underTest.findAll().stream()
                .filter(player -> player.getLeague().equals("Saudi Pro League"))
                .toList();
        assertEquals(2, players.size());
    }

    @Test
    void testShouldFindByCountry() {
        List<FootballPlayers> players = underTest.findAll().stream()
                .filter(player -> player.getCountry().equals("Argentina"))
                .toList();
        assertEquals(1, players.size());
        assertEquals("Lionel", players.get(0).getName());
    }

    @Test
    void testShouldFindPlayersByPosition() {
        List<FootballPlayers> forwards = underTest.findAll().stream()
                .filter(player -> player.getPosition().equals("CAM"))
                .toList();
        assertEquals(1, forwards.size());
    }
}
