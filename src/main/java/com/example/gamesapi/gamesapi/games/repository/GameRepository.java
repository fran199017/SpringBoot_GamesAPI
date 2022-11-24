package com.example.gamesapi.gamesapi.games.repository;

import com.example.gamesapi.gamesapi.games.model.Game;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository("gameRepository")
public interface GameRepository extends PagingAndSortingRepository<Game, Integer>, JpaSpecificationExecutor<Game> {

}
