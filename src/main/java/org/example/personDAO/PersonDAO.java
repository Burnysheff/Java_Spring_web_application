package org.example.personDAO;

import org.example.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/People";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Nick181202";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPerson(int id) {
        /**for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }*/
        return null;
    }

    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String request = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setSalary(resultSet.getInt("salary"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                personList.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    public void addPerson(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (id, name, salary, email, age) VALUES " +
                    "(?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setDouble(3, person.getSalary());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setInt(5, person.getAge());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePerson(int id, Person newPerson) {
        Person person = this.getPerson(id);
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());
        person.setSalary(newPerson.getSalary());
    }

    public void deletePerson(int id) {
        //this.personList.remove(this.getPerson(id));
    }

    public PersonDAO() {
    }
}
