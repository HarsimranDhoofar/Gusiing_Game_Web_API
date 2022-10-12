package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dhoofa
 */
@Repository
public class GameDaoImpl  implements GameDao{

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public Game startGame(Game start) {
        final String sql = "INSERT INTO game(gameStatus, computerAnswer) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, start.getGameStatus());
            statement.setInt(2, start.getComputerAnswer());
            return statement;

        }, keyHolder);

        start.setGameID(keyHolder.getKey().intValue());

        return start;
    }

    @Override
    @Transactional
    public Game getGamebyId(int gameId) {
         final String sql = "SELECT gameID, gameStatus, computerAnswer "
                + "FROM game WHERE gameID = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

     @Override
     @Transactional
    public List<Game> getAllGame() {
         final String sql = "SELECT gameID, gameStatus, computerAnswer "
                + "FROM game;";
         return jdbcTemplate.query(sql, new GameMapper());
    }
    @Override
    @Transactional
    public List<Game> getAllGameById(int gameId) {
         final String sql = "SELECT gameID, gameStatus, computerAnswer "
                + "FROM game WHERE gameID = ?;";
         return jdbcTemplate.query(sql, new GameMapper(),gameId);
    }

     @Override
     @Transactional
    public void updateGameStatus(Game currentGame) {
        final String sql = "UPDATE game SET "
                + "gameStatus = ? "
                + "WHERE gameID = ?;";

       jdbcTemplate.update(sql,
                currentGame.getGameStatus(),
                currentGame.getGameID());
    }
    @Override
    @Transactional
    public void deleteGame(int gameid){
        final String sql1 = "DELETE FROM game WHERE gameID = ?;";
        final String sql2 = "DELETE FROM gameRound WHERE gameID = ?;";
       // final String sql3 = "DELETE FROM round INNER JOIN gameRound gr ON round.roundID = gr.roundID WHERE gr.gameID = ?;";
        boolean b = jdbcTemplate.update(sql2, gameid) > 0;
        boolean a = jdbcTemplate.update(sql1, gameid) > 0;
        
        
    }
    
     private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("gameID"));
            game.setGameStatus(rs.getString("gameStatus"));
            game.setComputerAnswer(rs.getInt("computerAnswer"));
            return game;
        }
    }
    
}
