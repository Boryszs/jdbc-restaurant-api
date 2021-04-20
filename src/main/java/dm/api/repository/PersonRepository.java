package dm.api.repository;

import dm.api.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    int count();
    int save (Person person);
    int update (Person person);
    int deleteById (Integer id);
    List<Person> findAll();
    Optional<Person> findById(int id);
}
