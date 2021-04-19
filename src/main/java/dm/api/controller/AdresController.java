package dm.api.controller;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.dto.response.DtoError;
import dm.api.service.AdresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/adres")
@RestController
@CrossOrigin
public class AdresController {

    Logger logger = LoggerFactory.getLogger(AdresController.class);

    private AdresService adresService;
    @Autowired
    public AdresController(AdresService adresService) {
        this.adresService = adresService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count adres entity");
        return ResponseEntity.ok(adresService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoAdresResponse>> getAll() {
        logger.info("Get all adres");
        return ResponseEntity.ok(adresService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addAdres(@RequestBody DtoAdresRequest adresRequest) {
        logger.info("Add adres");
        adresService.save(adresRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoAdresRequest adresRequest) {
        try{
            logger.info("Update adres");
            return ResponseEntity.ok(adresService.update(id,adresRequest));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteAdres(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete adres id {}",id);
            return ResponseEntity.ok(adresService.deleteById(id));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getKlient(@PathVariable(value="id") Integer id) {
        logger.info("Get Adres on id {}",id);
        try {
            Optional<DtoAdresResponse> adres = adresService.findById(id);
            return ResponseEntity.ok(adres.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

}
