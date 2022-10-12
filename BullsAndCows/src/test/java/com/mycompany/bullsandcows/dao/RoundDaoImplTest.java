/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.Game;
import com.mycompany.bullsandcows.TestApplicationConfiguration;
import com.mycompany.bullsandcows.dto.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dhoofa
 */
@RunWith( SpringRunner.class )
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoImplTest {
    
    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;
    
    public RoundDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         List<Game> game = gameDao.getAllGame();
        for(Game g : game) {
            gameDao.deleteGame(g.getGameID());
        }
        

        List<Round> round = roundDao.getAllRounds();
       for(Round r : round) {
          roundDao.deleteRound(r.getRoundID());
      }
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of playRound method, of class RoundDaoImpl.
     */
    @Test
    public void testPlayRound() {
        Game game1 = new Game();
        game1.setGameStatus("inProgress");
        game1.setComputerAnswer(4321);
        gameDao.startGame(game1);
        
        Round newRound = new Round();
        newRound.setUserAnswer(1234);
        newRound.setResult("e:0:p2");
        Round result = roundDao.playRound(newRound, game1.getGameID());
        
         assertEquals(result, newRound);
        
    }
        
    
    /**
     * Test of getAllRoundsById method, of class RoundDaoImpl.
     */
    @Test
    public void testGetAllRoundsById() {
        Game game1 = new Game();
        game1.setGameStatus("inProgress");
        game1.setComputerAnswer(4321);
        gameDao.startGame(game1);
        
        Round newRound = new Round();
        newRound.setUserAnswer(1234);
        newRound.setResult("e:0:p2");
        roundDao.playRound(newRound, game1.getGameID());
        List<Round> r = roundDao.getAllRoundsById(game1.getGameID());
        Round result = r.get(0);
        
        assertEquals(result, newRound);
        assertTrue(r.contains(newRound));
        
    }

    /**
     * Test of deleteRound method, of class RoundDaoImpl.
     */
    @Test
    public void testDeleteRound() {
        Game game1 = new Game();
        game1.setGameStatus("inProgress");
        game1.setComputerAnswer(4321);
        gameDao.startGame(game1);
        
        Round newRound = new Round();
        newRound.setUserAnswer(1234);
        newRound.setResult("e:0:p2");
        roundDao.playRound(newRound, game1.getGameID());
        roundDao.deleteRound(newRound.getRoundID());
        
        int result = roundDao.getAllRoundsById(newRound.getRoundID()).size();
        
        assertEquals(result, 0);
        
        
        
    }

    /**
     * Test of getAllRounds method, of class RoundDaoImpl.
     */
    @Test
    public void testGetAllRounds() {
        Game game1 = new Game();
        game1.setGameStatus("inProgress");
        game1.setComputerAnswer(4321);
        gameDao.startGame(game1);
        
        Round newRound1 = new Round();
        newRound1.setUserAnswer(1234);
        newRound1.setResult("e:0:p2");
        roundDao.playRound(newRound1, game1.getGameID());
        
        Round newRound2 = new Round();
        newRound2.setUserAnswer(3432);
        newRound2.setResult("e:0:p3");
        roundDao.playRound(newRound2, game1.getGameID());
        
        List<Round> result = roundDao.getAllRounds();
        
        assertEquals(result.size(), 2);
        assertTrue(result.contains(newRound1));
        assertTrue(result.contains(newRound2));
        
    }
    
}
