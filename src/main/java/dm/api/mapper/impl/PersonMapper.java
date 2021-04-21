package dm.api.mapper.impl;

import dm.api.dto.request.DtoPersonRequest;
import dm.api.dto.response.DtoPersonResponse;
import dm.api.mapper.Convert;
import dm.api.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements Convert<Person, DtoPersonRequest, DtoPersonResponse> {

    @Override
    public Person convert(DtoPersonRequest osobaRequest) {

        return new Person().builder()
                .name(osobaRequest.getName())
                .surname(osobaRequest.getSurname())
                .pesel(osobaRequest.getPesel())
                .dateBirthday(osobaRequest.getDateBirthday())
                .email(osobaRequest.getEmail())
                .telephone(osobaRequest.getTelephone())
                .build();
    }

    @Override
    public Person update(Person person, DtoPersonRequest osobaRequest) {
        person.setName(osobaRequest.getName());
        person.setSurname(osobaRequest.getSurname());
        person.setPesel(osobaRequest.getPesel());
        person.setDateBirthday(osobaRequest.getDateBirthday());
        person.setEmail(osobaRequest.getEmail());
        person.setTelephone(osobaRequest.getTelephone());

        return person;

    }

    @Override
    public DtoPersonResponse toDto(Person person) {
        return new DtoPersonResponse().builder()
                .idPerson(person.getIdPerson())
                .name(person.getName())
                .surname(person.getSurname())
                .pesel(person.getPesel())
                .dateBirthday(person.getDateBirthday())
                .email(person.getEmail())
                .telephone(person.getTelephone())
                .idAddress(person.getAddress().getIdAddress())
                .build();
    }
}
