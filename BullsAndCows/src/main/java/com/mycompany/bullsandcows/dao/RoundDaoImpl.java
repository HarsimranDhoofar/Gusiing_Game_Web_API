/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.Game;
import com.mycompany.bullsandcows.dto.Round;
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

/**
 *
 * @author Harsimran Dhoofar
 */
@Repository
public class RoundDaoImpl implements RoundDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public RoundDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Round playRound(Round newRound, int gameId) {
        final String sql1 = "INSERT INTO round(userAnswer, result, time) VALUES(?,?,?);";
        final String sql2 = "INSERT INTO gameRound(gameID, roundID) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql1, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, newRound.getUserAnswer());
            statement.setString(2, newRound.getResult());
            statement.setTimestamp(3, newRound.getTime());
            return statement;

        }, keyHolder);

        newRound.setRoundID(keyHolder.getKey().intValue());
        
        
         jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql2, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1,gameId );
            statement.setInt(2, keyHolder.getKey().intValue());
            return statement;

        }, keyHolder);
         
        return newRound;
    }

    @Override
    @Transactional
    public List<Round> getAllRoundsById(int gameId) {
        final String sql = "SELECT round.roundID, userAnswer, result, time FROM round "
                + " INNER JOIN gameRound gr ON round.roundID = gr.roundID " 
                + " INNER JOIN game g ON gr.gameID = g.gameID "
                + " WHERE g.gameID = ?"
                + " GROUP BY round.roundID"
                + " ORDER BY time asc;";
         return jdbcTemplate.query(sql, new RoundMapper(),gameId);
    }
    
    @Override
    @Transactional
    public void deleteRound(int roundId){
        
        final String sql1 = "DELETE FROM round WHERE roundID = ?;";
        final String sql2 = "DELETE FROM gameRound WHERE roundID = ?;";
        boolean b = jdbcTemplate.update(sql2, roundId) > 0;
        boolean a = jdbcTemplate.update(sql1, roundId) > 0;
  
        
    }
    @Override
    @Transactional
    public List<Round> getAllRounds() {
         final String sql = "SELECT roundID, userAnswer, result, time "
                + " FROM round;";
         
         return jdbcTemplate.query(sql, new RoundMapper());
    }
    
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(rs.getInt("roundID"));
            round.setUserAnswer(rs.getInt("userAnswer"));
            round.setResult(rs.getString("result"));
            round.setTime(rs.getTimestamp("time"));
            return round;
        }
    }
}
