package com.mycompany.bullsandcows;

import java.util.List;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dhoofa
 */
public class Game {
    
    private int gameID;
    private String gameStatus;
    private int computerAnswer;

    public Game() {
    }

    public Game(int gameID, String gameStatus, int computerAnswer) {
        this.gameID = gameID;
        this.gameStatus = gameStatus;
        this.computerAnswer = computerAnswer;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

  

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getComputerAnswer() {
        return computerAnswer;
    }

    public void setComputerAnswer(int computerAnswer) {
        this.computerAnswer = computerAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.gameID;
        hash = 19 * hash + Objects.hashCode(this.gameStatus);
        hash = 19 * hash + this.computerAnswer;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameID != other.gameID) {
            return false;
        }
        if (this.computerAnswer != other.computerAnswer) {
            return false;
        }
        return Objects.equals(this.gameStatus, other.gameStatus);
    }
    
    

  
    
}
