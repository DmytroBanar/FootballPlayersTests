package com.banar.labs.service;

import com.banar.labs.controller.FootballPlayersRestController;
import com.banar.labs.model.FootballPlayers;
import com.banar.labs.repository.FootballPlayersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FootballPlayersService {

    private final FootballPlayersRepository FootballPlayersRepository;

    private List<FootballPlayers> players = new ArrayList<>();
    {
        players.add(new FootballPlayers("1","Dias", "Raphinha", 11, 27, "La Liga", "FC Barcelona", "Brazil", "RW"));
        players.add(new FootballPlayers("2","Robert","Lewandowski", 9, 36, "La Liga", "FC Barcelona", "Poland", "ST"));
        players.add(new FootballPlayers("3","Cole", "Palmer", 20,  22, "EPL", "Chelsea", "England", "CAM"));
        players.add(new FootballPlayers("4","Raphael", "Leao", 12,  25, "Serie A", "Milan", "Portugal", "LW"));

    }

    @PostConstruct
    void init() {
        FootballPlayersRepository.deleteAll();
        FootballPlayersRepository.saveAll(players);
    }
    //  CRUD   - create read update delete

    public List<FootballPlayers> getAll() {
        return FootballPlayersRepository.findAll();
    }

    public FootballPlayers getById(String id) {
        return FootballPlayersRepository.findById(id).orElse(null);
    }

    public FootballPlayers create(FootballPlayers footballPlayers) {
        return FootballPlayersRepository.save(footballPlayers);
    }

    public  FootballPlayers update(FootballPlayers footballPlayers) {
        return FootballPlayersRepository.save(footballPlayers);
    }

    public void delById(String id) {
        FootballPlayersRepository.deleteById(id);
    }


}