package com.banar.labs.repository;

import com.banar.labs.model.FootballPlayers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballPlayersRepository extends MongoRepository<FootballPlayers, String> {
}