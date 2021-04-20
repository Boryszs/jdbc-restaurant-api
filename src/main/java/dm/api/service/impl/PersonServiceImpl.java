package dm.api.service.impl;

import dm.api.dto.request.DtoPersonRequest;
import dm.api.dto.response.DtoPersonResponse;
import dm.api.mapper.Convert;
import dm.api.model.Person;
import dm.api.repository.PersonRepository;
import dm.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

   private final PersonRepository personRepository;
   private final Convert<Person, DtoPersonRequest, DtoPersonResponse> person;

   @Autowired
    public PersonServiceImpl(PersonRepository personRepository, Convert<Person, DtoPersonRequest, DtoPersonResponse> person) {
        this.personRepository = personRepository;
        this.person = person;
   }

    @Override
    public int count() {
        return personRepository.count();
    }

    @Override
    public int save(DtoPersonRequest dtoOsobaResponse) {
        return personRepository.save(person.convert(dtoOsobaResponse));
    }

    @Override
    public int update(Integer id, DtoPersonRequest dtoPersonRequest) {
        return personRepository.update(person.update(personRepository.findById(id).get(), dtoPersonRequest));
    }

    @Override
    public int deleteById(int id) {
        return personRepository.deleteById(id);
    }

    @Override
    public List<DtoPersonResponse> findAll() {
        List<DtoPersonResponse> personResponseList = new LinkedList<>();
        personRepository.findAll().stream().map(person -> this.person.toDto(person)).forEach(personResponseList::add);
        return personResponseList;
    }

    @Override
    public Optional<DtoPersonResponse> findById(int id) {
        return Optional.of(person.toDto(personRepository.findById(id).get()));
    }
}
