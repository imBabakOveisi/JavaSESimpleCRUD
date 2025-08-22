package model.tools;

import model.entity.Person;
import model.entity.enums.PersonRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements Mapper<Person>{
    @Override
    public Person mapper(ResultSet resultSet) throws SQLException {
        return Person
                .builder()
                .personId(resultSet.getInt("person_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .birthDate(resultSet.getDate("birth_date").toLocalDate())
                .role(PersonRole.valueOf(resultSet.getString("role")))
                .active(resultSet.getBoolean("active"))
                .build();
    }
}
