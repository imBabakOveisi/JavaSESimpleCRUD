package model.tools;

import model.entity.Education;
import model.entity.enums.EducationGrade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationMapper implements Mapper<Education> {
    @Override
    public Education mapper(ResultSet resultSet) throws SQLException {
        return Education
                .builder()
                .educationId(resultSet.getInt("education_id"))
                .personId(resultSet.getInt("person_id"))
                .university(resultSet.getString("university"))
                .educationGrade(EducationGrade.valueOf(resultSet.getString("education_grade")))
                .average(resultSet.getDouble("average"))
                .startDate(resultSet.getDate("start_date").toLocalDate())
                .endDate(resultSet.getDate("end_date").toLocalDate())
                .build();
    }
}
