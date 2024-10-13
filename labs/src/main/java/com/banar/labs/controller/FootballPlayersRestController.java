package com.banar.labs.controller;

import com.banar.labs.model.FootballPlayers;
import com.banar.labs.repository.FootballPlayersRepository;
import com.banar.labs.service.FootballPlayersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/FootballPlayers/")
@RequiredArgsConstructor
public class FootballPlayersRestController {

    private final FootballPlayersService FootballPlayersService;

    @GetMapping
    public List<FootballPlayers> showAll() {
        return FootballPlayersService.getAll();
    }

    @GetMapping("{id}")
    public FootballPlayers showOneById(@PathVariable String id) {
        return FootballPlayersService.getById(id);
    }

    @PostMapping
    public FootballPlayers insert(@RequestBody FootballPlayers FootballPlayers) {
        return FootballPlayersService.create(FootballPlayers);
    }

    @PutMapping
    public FootballPlayers edit(@RequestBody FootballPlayers FootballPlayers) {
        return FootballPlayersService.update(FootballPlayers);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        FootballPlayersService.delById(id);
    }

}
