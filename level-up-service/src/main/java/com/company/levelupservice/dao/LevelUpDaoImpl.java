package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoImpl implements LevelUpDao {

    private static final String INSERT_LEVEL_UP_SQL =
            "INSERT INTO level_up (customer_id, points, member_date) VALUES (?, ?, ?)";

    private static final String SELECT_LEVEL_UP_SQL =
            "SELECT * FROM level_up WHERE level_up_id = ?";

    private static final String SELECT_ALL_LEVEL_UPS_SQL =
            "SELECT * FROM level_up";

    private static final String UPDATE_LEVEL_UP_SQL =
            "UPDATE level_up SET customer_id = ?, points = ?, member_date = ? WHERE level_up_id = ?";

    private static final String DELETE_LEVEL_UP_SQL =
            "DELETE FROM level_up WHERE level_up_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LevelUpDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public LevelUp addLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(INSERT_LEVEL_UP_SQL,
                levelUp.getCustomer_id(),
                levelUp.getPoints(),
                levelUp.getMember_date()
                );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        levelUp.setLevel_up_id(id);

        return levelUp;
    }

    @Override
    public LevelUp getLevelUp(int level_up_id) {
        return jdbcTemplate.queryForObject(SELECT_LEVEL_UP_SQL, this::mapRowToLevelUp, level_up_id);
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return jdbcTemplate.query(SELECT_ALL_LEVEL_UPS_SQL, this::mapRowToLevelUp);
    }

    @Override
    public void updateLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(UPDATE_LEVEL_UP_SQL,
                levelUp.getCustomer_id(),
                levelUp.getPoints(),
                levelUp.getMember_date(),
                levelUp.getLevel_up_id()
                );
    }

    @Override
    public void deleteLevelUp(int level_up_id) {
        jdbcTemplate.update(DELETE_LEVEL_UP_SQL, level_up_id);
    }

    private LevelUp mapRowToLevelUp(ResultSet rs, int rowNum) throws SQLException {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(rs.getInt("level_up_id"));
        levelUp.setCustomer_id(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMember_date(rs.getDate("member_date").toLocalDate());
        return levelUp;
    }
}
