/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.dto.Round;
import java.util.List;

/**
 *
 * @author Harsimran Dhoofar
 */
public interface RoundDao {
    public Round playRound(Round newRound, int gameId);
    public List<Round> getAllRoundsById(int gameId);
    public void deleteRound(int roundId);
    public List<Round> getAllRounds();
}
