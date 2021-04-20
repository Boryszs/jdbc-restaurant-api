package dm.api.controller;

import dm.api.dto.request.DtoPersonRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoPersonResponse;
import dm.api.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/osoba")
@RestController
@CrossOrigin
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count person entity");
        return ResponseEntity.ok(personService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoPersonResponse>> getAll() {
        logger.info("Get all person");
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addPerson(@RequestBody DtoPersonRequest dtoPersonRequest) {
        logger.info("Add person");
        return ResponseEntity.status(201).body(personService.save(dtoPersonRequest));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> updatePerson(@PathVariable(value="id") Integer id, @RequestBody DtoPersonRequest dtoPersonRequest) {
        logger.info("Update person");
        return ResponseEntity.ok(personService.update(id,dtoPersonRequest));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePersonId(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete person id ",id);
            return ResponseEntity.ok(personService.deleteById(id));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find person on id: "+id));
        }
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getPersonId(@PathVariable(value="id") Integer id) {
        logger.info("Get person on id {}",id);
        try {
            Optional<DtoPersonResponse> person = personService.findById(id);
            return ResponseEntity.ok(person.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find person on id: "+id));
        }
    }

}
