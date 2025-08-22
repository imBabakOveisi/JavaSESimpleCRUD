package model.repository;

import model.entity.Person;
import model.tools.ConnectionProvider;
import model.tools.PersonMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<Person, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PersonMapper personMapper = new PersonMapper();

    public PersonRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getConnection();
    }

    @Override
    public void save(Person person) throws SQLException {
        person.setPersonId(ConnectionProvider.getProvider().getNextId("person_seq"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSONS (PERSON_ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, ROLE, ACTIVE)" +
                        "VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, person.getPersonId());
        preparedStatement.setString(2, person.getFirstName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setDate(4, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(5, person.getRole().name());
        preparedStatement.setBoolean(6, person.isActive());
        preparedStatement.execute();
    }

    @Override
    public void update(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSONS SET FIRST_NAME=?, LAST_NAME=?, BIRTH_DATE=?, ROLE=?, ACTIVE=? WHERE PERSON_ID=?"
        );
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setDate(3, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(4, person.getRole().name());
        preparedStatement.setBoolean(5, person.isActive());
        preparedStatement.setInt(6, person.getPersonId());
        preparedStatement.execute();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSONS WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Person> findAll() throws SQLException {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSONS ORDER BY LAST_NAME, FIRST_NAME"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            personList.add(personMapper.mapper(resultSet));
        }
        return personList;
    }

    @Override
    public Person findById(Integer id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSONS WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return personMapper.mapper(resultSet);
        }
        return null;
    }

    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) throws SQLException {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSONS WHERE FIRST_NAME LIKE ? AND LAST_NAME LIKE ? ORDER BY LAST_NAME, FIRST_NAME"
        );
        preparedStatement.setString(1, firstName + "%");
        preparedStatement.setString(2, lastName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            personList.add(personMapper.mapper(resultSet));
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

