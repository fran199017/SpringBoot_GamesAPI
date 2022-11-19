package com.example.gamesapi.gamesapi.games.repository;

import com.example.gamesapi.gamesapi.games.model.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("gameRepository")
public interface GameRepository extends PagingAndSortingRepository<Game, Integer>{

    @Query(value = "select g from Game g order by g.id")
    Optional<List<Game>> findAllGamesOrderById();

}
