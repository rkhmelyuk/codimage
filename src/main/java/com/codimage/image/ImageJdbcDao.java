package com.codimage.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DAO implementation to work with image.
 *
 * @author Ruslan Khmelyuk
 */
@Repository
public class ImageJdbcDao extends JdbcDaoSupport implements ImageDao {

    private static RowMapper<Image> IMAGE_ROW_MAPPER = new RowMapper<Image>() {

        @Override
        public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Image image = new Image();

            image.setId(rs.getLong(1));
            image.setUrl(rs.getString(2));

            return image;
        }
    };

    @Value("${image.insert}")
    private String insertQuery;

    @Value("${image.nextRandom}")
    private String nextRandomQuery;

    @Value("${image.deleteAll}")
    private String deleteAllQuery;

    @Autowired
    public ImageJdbcDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Transactional
    @Override
    public void create(Image image) {
        getJdbcTemplate().update(insertQuery, image.getUrl());
        // TODO - return id of created row
    }

    @Override
    public Image findRandomImage() {
        try {
            return getJdbcTemplate().queryForObject(nextRandomQuery, IMAGE_ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(deleteAllQuery);
    }
}
