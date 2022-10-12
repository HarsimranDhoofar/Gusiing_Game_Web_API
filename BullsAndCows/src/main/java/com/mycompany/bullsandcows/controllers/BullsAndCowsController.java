/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.controllers;

import com.mycompany.bullsandcows.Game;
import com.mycompany.bullsandcows.dao.GameDaoImpl;
import com.mycompany.bullsandcows.dto.Round;
import com.mycompany.bullsandcows.service.BullsAndCowsServiceLayerImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dhoofa
 */
@Repository
@RestController
@RequestMapping("/api/bullsAndCows")
public class BullsAndCowsController {
    private final BullsAndCowsServiceLayerImpl service;
    
    public BullsAndCowsController(BullsAndCowsServiceLayerImpl service) {
      this.service = service;
    }
    
@PostMapping("/begin")
@ResponseStatus(HttpStatus.CREATED)
 public Game startGame() {
   return service.startGame();
}

 @PostMapping("/guess")
 public ResponseEntity<Round> playRound(int userGuess, int gameId){
     if(service.getAllGameById(gameId).isEmpty()){
         return new ResponseEntity("No game available with gameid: "+ gameId +". Please try another number", HttpStatus.NOT_FOUND);
     }
     if(userGuess <1000 || userGuess >9999){
         return new ResponseEntity("UserGuess value should be between 1000 and 9999", HttpStatus.BAD_REQUEST);
     }
     if(hasDistinctDigits(userGuess) == false){
         return new ResponseEntity("UserGuess value should not have duplicate values", HttpStatus.BAD_REQUEST);
     }
     if(service.getAllGameById(gameId).get(0).getGameStatus().equals("finished")){
          return new ResponseEntity("The game is already Finished. Please try with another gameId", HttpStatus.BAD_REQUEST);
      }
     return ResponseEntity.ok(service.playRound(userGuess,gameId));   
 }
 

 
  @GetMapping("/game")
    public List<Game> allGame() {
        return service.getAllGame();
    }
 
  @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Game>> allGameById(@PathVariable int gameId) {
        if(service.getAllGameById(gameId).isEmpty()){
            return new ResponseEntity("No game available with gameid: "+ gameId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(service.getAllGameById(gameId));
    }
 
  @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> allRoundsById(@PathVariable int gameId) {
        if(service.getAllRoundsById(gameId).isEmpty()){
            return new ResponseEntity("No round available with gameid: "+ gameId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(service.getAllRoundsById(gameId));
    }
    
    
    
    
     private static boolean hasDistinctDigits(int number) {
     int numMask = 0;
     int numDigits = (int) Math.ceil(Math.log10(number+1));
     for (int digitIdx = 0; digitIdx < numDigits; digitIdx++) {
         int curDigit = (int)(number / Math.pow(10,digitIdx)) % 10;
         int digitMask = (int)Math.pow(2, curDigit);             
         if ((numMask & digitMask) > 0) return false;
         numMask = numMask | digitMask;
     }
     return true;
 }
}