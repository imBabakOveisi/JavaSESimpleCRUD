package model.repository;

import model.entity.Education;
import model.tools.ConnectionProvider;
import model.tools.EducationMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationRepository implements Repository<Education, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final EducationMapper educationMapper = new EducationMapper();

    public EducationRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Education education) throws SQLException {
        education.setEducationId(ConnectionProvider.getProvider().getNextId("education_seq"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO EDUCATIONS (EDUCATION_ID, PERSON_ID, UNIVERSITY, EDUCATION_GRADE, AVERAGE, START_DATE, END_DATE)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, education.getEducationId());
        preparedStatement.setInt(2, education.getPersonId());
        preparedStatement.setString(3, education.getUniversity());
        preparedStatement.setString(4, education.getEducationGrade().name());
        preparedStatement.setDouble(5, education.getAverage());
        preparedStatement.setDate(6, Date.valueOf(education.getStartDate()));
        preparedStatement.setDate(7, Date.valueOf(education.getEndDate()));
        preparedStatement.execute();
    }

    @Override
    public void update(Education education) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "UPDATE EDUCATIONS SET UNIVERSITY=?, EDUCATION_GRADE=?, AVERAGE=?, START_DATE=?, END_DATE=? WHERE EDUCATION_ID=?"
        );
        preparedStatement.setString(1, education.getUniversity());
        preparedStatement.setString(2, education.getEducationGrade().name());
        preparedStatement.setDouble(3, education.getAverage());
        preparedStatement.setDate(4, Date.valueOf(education.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(education.getEndDate()));
        preparedStatement.setInt(6, education.getEducationId());
        preparedStatement.execute();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM EDUCATIONS WHERE EDUCATION_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Education> findAll() throws SQLException {
        List<Education> educationList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS ORDER BY UNIVERSITY, EDUCATION_GRADE"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            educationList.add(educationMapper.mapper(resultSet));
        }
        return educationList;
    }

    @Override
    public Education findById(Integer id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE EDUCATION_ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return educationMapper.mapper(resultSet);
        }
        return null;
    }

    public List<Education> findByUniversityAndEducationGrade(String university, String educationGrade) throws SQLException {
        List<Education> educationList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM EDUCATIONS WHERE UNIVERSITY LIKE ? AND EDUCATION_GRADE LIKE ?"
        );
        preparedStatement.setString(1, university + "%");
        preparedStatement.setString(2, educationGrade + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            educationList.add(educationMapper.mapper(resultSet));
        }
        return educationList;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
