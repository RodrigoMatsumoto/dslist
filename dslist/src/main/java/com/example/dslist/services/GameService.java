package com.example.dslist.services;

import com.example.dslist.dtos.GameDTO;
import com.example.dslist.dtos.GameMinDTO;
import com.example.dslist.entities.Game;
import com.example.dslist.projections.GameMinProjection;
import com.example.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
  
  @Autowired
  private GameRepository gameRepository;
  
  @Transactional(readOnly = true)
  public GameDTO findById(Long id) {
    Game result = gameRepository.findById(id).orElseThrow();
    
    return new GameDTO(result);
  }
  
  @Transactional(readOnly = true)
  public List<GameMinDTO> findAll() {
    List<Game> result = gameRepository.findAll();
    
    return result.stream().map(GameMinDTO::new).toList();
  }
  
  @Transactional(readOnly = true)
  public List<GameMinDTO> findByList(Long listId) {
    List<GameMinProjection> result = gameRepository.searchByList(listId);
    
    return result.stream().map(GameMinDTO::new).toList();
  }
}