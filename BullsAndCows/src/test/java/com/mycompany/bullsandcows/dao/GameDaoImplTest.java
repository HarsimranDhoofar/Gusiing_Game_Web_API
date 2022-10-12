/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.Game;
import com.mycompany.bullsandcows.TestApplicationConfiguration;
import com.mycompany.bullsandcows.dto.Round;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Harsimran Dhoofar
 */
@RunWith( SpringRunner.class )
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDaoImplTest {
    
    
    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;
    public GameDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
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
    public void tearDown() throws Exception {
    }

    @Test
    public void testStartGame() {
          
        Game start = new Game();
        start.setGameStatus("inProgress");
        start.setComputerAnswer(6543);


        Game expResult = gameDao.startGame(start);

        assertEquals(expResult, start);
    }

    @Test
    public void testGetGamebyId() {
        //prepration
        Game prep = new Game();
        prep.setGameStatus("inProgress");
        prep.setComputerAnswer(4321);
        gameDao.startGame(prep);
      
        Game returned = gameDao.getGamebyId(prep.getGameID());
        
        assertEquals(returned, prep);
    }

    @Test
    public void testGetAllGame() {
        
        List<Game> gameList = new ArrayList();
    
        Game game1 = new Game();
        game1.setGameStatus("inProgress");
        game1.setComputerAnswer(4321);
        gameDao.startGame(game1);
        gameList.add(game1);
        
        Game game2 = new Game();
        game2.setGameStatus("inProgress");
        game2.setComputerAnswer(1234);
        gameDao.startGame(game2);
        gameList.add(game2);
        
        
        List<Game> result = gameDao.getAllGame();
        assertEquals(result.size(), gameList.size());
        
        
    }

    /**
     * Test of getAllGameById method, of class GameDaoImpl.
     */
    @Test
    public void testGetAllGameById() {
         Game prep = new Game();
        prep.setGameStatus("inProgress");
        prep.setComputerAnswer(4321);
        gameDao.startGame(prep);
        
        
        List<Game> r = gameDao.getAllGameById(prep.getGameID());
        Game returned = r.get(0);
        assertEquals(returned, prep);
        
    }

    /**
     * Test of updateGameStatus method, of class GameDaoImpl.
     */
    @Test
    public void testUpdateGameStatus() {
        Game prep = new Game();
        prep.setGameStatus("inProgress");
        prep.setComputerAnswer(4321);
        gameDao.startGame(prep);
        
        Game updateTo = new Game();
        updateTo.setGameID(prep.getGameID());
        updateTo.setGameStatus("Finished");
        updateTo.setComputerAnswer(4321);
        
        gameDao.updateGameStatus(updateTo);
        
        Game result = gameDao.getGamebyId(prep.getGameID());
        assertEquals(result, updateTo);
        assertNotEquals(result,prep);
        
    }


    
}
