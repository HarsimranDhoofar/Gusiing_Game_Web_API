/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.Game;
import java.util.List;

/**
 *
 * @author Harsimran Dhoofar
 */
public interface GameDao {
    public Game startGame(Game start);
    public Game getGamebyId(int gameId);
    public List<Game> getAllGame();
    public List<Game> getAllGameById(int gameId);
    public void updateGameStatus(Game currentGame);
    public void deleteGame(int gameid);
}
