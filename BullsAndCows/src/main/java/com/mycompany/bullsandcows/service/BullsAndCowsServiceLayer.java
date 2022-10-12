/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.service;

import com.mycompany.bullsandcows.Game;
import com.mycompany.bullsandcows.dto.Round;
import java.util.List;

/**
 *
 * @author Harsimran Dhoofar
 */
public interface BullsAndCowsServiceLayer {
    public Game startGame();
    public Round playRound(int userGuess, int gameId) ;
    public List<Game> getAllGame();
    public List<Game> getAllGameById(int gameId);
    public List<Round> getAllRoundsById(int gameId);
}
