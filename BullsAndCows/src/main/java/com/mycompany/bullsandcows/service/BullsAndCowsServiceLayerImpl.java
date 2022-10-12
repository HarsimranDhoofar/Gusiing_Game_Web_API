/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.service;

import com.mycompany.bullsandcows.Game;
import com.mycompany.bullsandcows.dao.GameDao;
import com.mycompany.bullsandcows.dao.GameDaoImpl;
import com.mycompany.bullsandcows.dao.RoundDaoImpl;
import com.mycompany.bullsandcows.dto.Round;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dhoofa
 */
@Repository
public class BullsAndCowsServiceLayerImpl implements BullsAndCowsServiceLayer{
    
    private final GameDao gameDao;
    private final RoundDaoImpl roundDao;

    public BullsAndCowsServiceLayerImpl(GameDao gameDao, RoundDaoImpl roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    @Override
    public Game startGame() {
        Game start = new Game();
        start.setGameStatus("inProgress");
        int computerAns = this.randomNumber();
        start.setComputerAnswer(computerAns);
        gameDao.startGame(start);
        start.setComputerAnswer(0);
        return start;
    }
    
    
    private int randomNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numbers.get(i).toString();
        }
        return Integer.parseInt(result);
    }
    
    @Override
    public Round playRound(int userGuess, int gameId) {
        Round newRound = new Round();
        int e = 0;
        int p = 0;
        newRound.setUserAnswer(userGuess);
        Game currentGame = gameDao.getGamebyId(gameId);
       
        
        int computerAns = currentGame.getComputerAnswer();
        String userGuessString =String.valueOf(userGuess);
        String computerGuessString = String.valueOf(computerAns);
        for (int i = 0; i < 4; i++) {
            if (userGuessString.charAt(i) == computerGuessString.charAt(i)) {
                e++;
            } else if (computerGuessString.contains(userGuessString.charAt(i) + "")) {
                p++;
            }
        }
        if(e == 4){
            currentGame.setGameStatus("finished");
            gameDao.updateGameStatus(currentGame);
        }
        newRound.setResult("e:" + e +":p:"+ p );
        
        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        newRound.setTime(currentTime);
        
        return roundDao.playRound(newRound, gameId);
       
    }

    @Override
    public List<Game> getAllGame() {
        
        List<Game> allGames =  gameDao.getAllGame();
        
        for(int i =0 ; i<allGames.size(); i++){
            if(allGames.get(i).getGameStatus().equals("inProgress")){
                allGames.get(i).setComputerAnswer(0);
            }
        }
        return allGames;
    }

    @Override
    public List<Game> getAllGameById(int gameId) {
        
        List<Game> allGames =  gameDao.getAllGameById(gameId);
        
        for(int i =0 ; i<allGames.size(); i++){
            if(allGames.get(i).getGameStatus().equals("inProgress")){
                allGames.get(i).setComputerAnswer(0);
            }
        }
        return allGames;
    }

    @Override
    public List<Round> getAllRoundsById(int gameId) {
        return roundDao.getAllRoundsById(gameId);
    }
    
}
