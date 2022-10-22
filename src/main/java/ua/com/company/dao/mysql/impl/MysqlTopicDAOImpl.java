package ua.com.company.dao.mysql.impl;

import ua.com.company.DBConstants;
import ua.com.company.dao.TopicDAO;
import ua.com.company.entity.Topic;
import ua.com.company.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlTopicDAOImpl implements TopicDAO {

    @Override
    public int create(Topic topic) throws DBException {
        int id = -1;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.CREATE_TOPIC, Statement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            stmt.setString(++index, topic.getTitle());
            int count = stmt.executeUpdate();
            if (count > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);        }
        return id;
    }

    @Override
    public void update(Topic topic) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.UPDATE_TOPIC)) {
            int index = 0;
            stmt.setString(++index, topic.getTitle());
            stmt.setInt(++index, topic.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void delete(int id) throws DBException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.DELETE_TOPIC)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public Optional<Topic> findById(int id) throws DBException {
       Optional<Topic>topic = Optional.empty();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_TOPIC_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                topic = Optional.of(mapTopic(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return topic;
    }

    @Override
    public List<Topic> findAll() throws DBException {
        List<Topic> topics = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeQuery(DBConstants.FIND_ALL_TOPICS);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                topics.add(mapTopic(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
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
    public boolean IsExistByTitle(String title) throws DBException {
        int count = 0;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.COUNT_TOPIC_BY_TITLE)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return count == 1;
    }

    @Override
    public List<Topic> findAllByPublicationId(int pubId) throws DBException {
        List<Topic> topicList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_ALL_TOPIC_BY_PUBLICATION_ID)) {
            stmt.setInt(1, pubId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                topicList.add(mapTopic(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);        }
        return topicList;    }
}
