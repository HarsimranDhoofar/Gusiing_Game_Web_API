/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author dhoofa
 */
public class Round {
    private int roundID;
    private int userAnswer;
    private String result;
    private  Timestamp time;

    public Round() {
    }

    public Round(int roundID, int userAnswer, String result, Timestamp time) {
        this.roundID = roundID;
        this.userAnswer = userAnswer;
        this.result = result;
        this.time = time;
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.roundID;
        hash = 31 * hash + this.userAnswer;
        hash = 31 * hash + Objects.hashCode(this.result);
        hash = 31 * hash + Objects.hashCode(this.time);
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
        final Round other = (Round) obj;
        if (this.roundID != other.roundID) {
            return false;
        }
        if (this.userAnswer != other.userAnswer) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return Objects.equals(this.time, other.time);
    }

    
    
    
}
