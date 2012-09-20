package com.codimage.image;

import com.khmelyuk.core.utils.collections.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    @Value("${image.nextImage}")
    private String nextImageQuery;

    @Value("${image.prevImage}")
    private String prevImageQuery;

    @Value("${image.deleteAll}")
    private String deleteAllQuery;

    @Autowired
    public ImageJdbcDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Transactional
    @Override
    public void create(final Image image) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement =
                        con.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, image.getUrl());
                return statement;
            }
        }, keyHolder);

        image.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(deleteAllQuery);
    }

    @Override
    public Image findRandomImage() {
        return findImageByQuery(nextRandomQuery);
    }

    /**
     * We actually loop over all images.
     * For instance, we have images 1, 2, 3, 4. So, when
     * * imageId is 2, then image 3 is returned,
     * * imageId is 4, then image 1 is returned.
     *
     * @param imageId the image id.
     * @return the next image.
     */
    @Override
    public Image findNextImage(long imageId) {
        return findImageByQuery(nextImageQuery, imageId);
    }

    /**
     * We actually loop over all images on backward order.
     * For instance, we have images 1, 2, 3, 4. So, when
     * * imageId is 2, then image 1 is returned,
     * * imageId is 1, then image 4 is returned.
     *
     * @param imageId the image id.
     * @return the next image.
     */
    @Override
    public Image findPrevImage(long imageId) {
        return findImageByQuery(prevImageQuery, imageId);
    }

    /**
     * Finds single image by query and parameters.
     * @param query the query.
     * @param params the array of query parameters.
     * @return the found image or null.
     */
    private Image findImageByQuery(String query, Object... params) {
        try {
            return getJdbcTemplate().queryForObject(query, params, IMAGE_ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
