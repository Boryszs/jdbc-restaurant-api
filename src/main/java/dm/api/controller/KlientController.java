package dm.api.controller;


import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.request.DtoUpdateKlientRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoKlientDataResponse;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.service.AdresService;
import dm.api.service.KlientService;
import dm.api.service.OsobaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/klient")
@RestController
@CrossOrigin
public class KlientController {

    Logger logger = LoggerFactory.getLogger(KlientController.class);

    private final KlientService klientService;
    private final OsobaService osobaService;
    private final AdresService adresService;

    @Autowired
    public KlientController(KlientService klientService, OsobaService osobaService, AdresService adresService) {
        this.klientService = klientService;
        this.osobaService = osobaService;
        this.adresService = adresService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count klient entity");
       return ResponseEntity.ok(klientService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all-klient")
    public ResponseEntity<List<DtoKlientDataResponse>> getAllKlient() {
        logger.info("Get all klient data");
        return ResponseEntity.ok(klientService.findAllKlient());
    }

    @ResponseBody
    @GetMapping(value = "/get-klient/{id}")
    public ResponseEntity<DtoKlientDataResponse> getKlientId(@PathVariable(value="id") Integer id) {
        logger.info("Get klient data on id {}", id);
        return ResponseEntity.ok(klientService.findKlientId(id));
    }

    @ResponseBody
    @DeleteMapping(value = "/delete-klient/{id}")
    public ResponseEntity<?> deleteKlientId(@PathVariable(value="id") Integer id) {
        logger.info("Delete klient on {}",id);
        try{
            klientService.deleteKlientById(id);
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoKlientResponse>> getAll() {
        logger.info("Get all klient");
        return ResponseEntity.ok(klientService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addKlient(@RequestBody DtoKlientRequest klientRequest) {
        logger.info("Add klient");
        klientService.save(klientRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-klient")
    public ResponseEntity<Integer> add(@RequestBody DtoAddKlientRequest klientRequest) {
        logger.info("Add klient");
        klientService.add(klientRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoKlientRequest klientRequest) {
        logger.info("Update klient");
        klientService.update(id,klientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update-klient/{id}")
    public ResponseEntity<?> updateKlient(@PathVariable(value="id") Integer id, @RequestBody DtoUpdateKlientRequest klientRequest) {
        try{
            klientService.update(id, klientRequest.getKlient());
            osobaService.update(klientRequest.getKlient().getIdOsoby(),klientRequest.getOsoba());
            adresService.update(klientRequest.getOsoba().getIdAdresu(),klientRequest.getAdres());
            logger.info("Update klient");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Klient on id: "+id));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteKlient(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete klient id ",id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getKlient(@PathVariable(value="id") Integer id) {
        logger.info("Get klient on id {}",id);
        try {
            Optional<DtoKlientResponse> klient = klientService.findById(id);
            return ResponseEntity.ok(klient.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Klient on id: "+id));
        }
    }
}
