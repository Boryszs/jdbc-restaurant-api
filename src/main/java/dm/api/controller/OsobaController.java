package dm.api.controller;

import dm.api.dto.request.DtoOsobaRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoOsobaResponse;
import dm.api.service.OsobaService;
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
public class OsobaController {

    Logger logger = LoggerFactory.getLogger(OsobaController.class);

    private OsobaService osobaService;

    @Autowired
    public OsobaController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count osoba entity");
        return ResponseEntity.ok(osobaService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoOsobaResponse>> getAll() {
        logger.info("Get all osoba");
        return ResponseEntity.ok(osobaService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addAdres(@RequestBody DtoOsobaRequest osobaRequest) {
        logger.info("Add osoba");
        return ResponseEntity.status(201).body(osobaService.save(osobaRequest));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoOsobaRequest osobaRequest) {
        logger.info("Update osoba");
        return ResponseEntity.ok(osobaService.update(id,osobaRequest));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteKlient(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete osoba id ",id);
            return ResponseEntity.ok(osobaService.deleteById(id));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getKlient(@PathVariable(value="id") Integer id) {
        logger.info("Get osoba on id {}",id);
        try {
            Optional<DtoOsobaResponse> osoba = osobaService.findById(id);
            return ResponseEntity.ok(osoba.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find osoba on id: "+id));
        }
    }

}
