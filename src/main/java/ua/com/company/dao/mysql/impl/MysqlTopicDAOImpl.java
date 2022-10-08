package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
import ua.com.company.config.impl.DBDataSourceImpl;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlTopicDAOImpl implements TopicDAO {

    @Override
    public int create(Topic topic) {
        int id = -1;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_TOPIC, Statement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            stmt.setString(++index, topic.getTitle());
            int count = stmt.executeUpdate();
            if (count > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id=rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Topic topic) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_TOPIC)) {
            int index = 0;
            stmt.setString(++index, topic.getTitle());
            stmt.setInt(++index, topic.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_TOPIC)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Topic> findById(int id) {
        Topic topic = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_TOPIC_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                topic = mapTopic(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(topic);

    }

    @Override
    public List<Topic> findAll() {
        List<Topic> topics = new ArrayList<>();
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeQuery(DBConstants.FIND_ALL_TOPICS);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                topics.add(mapTopic(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return topics;
    }

    private Topic mapTopic(ResultSet rs) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt(DBConstants.F_TOPIC_ID));
        topic.setTitle(rs.getString(DBConstants.F_TOPIC_TITLE));
//        topic.setCreateDate(rs.getTimestamp(DBConstants.F_TOPIC_CREATE_DATE));
//        topic.setUpdateDate(rs.getTimestamp(DBConstants.F_TOPIC_UPDATE_DATE));
        return topic;
    }

    @Override
    public Optional<Topic> findByTitle(String title) {
        Topic topic = null;
        try (Connection con = DBDataSourceImpl.getInstance().getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_TOPIC_BY_TITLE)) {
            stmt.setString(1, title);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                topic = mapTopic(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(topic);

    }
}