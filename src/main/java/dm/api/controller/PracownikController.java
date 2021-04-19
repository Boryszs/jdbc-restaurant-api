package dm.api.controller;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.request.DtoPracownikRequest;
import dm.api.dto.request.DtoUpdatePracownikRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.model.Pracownik;
import dm.api.service.AdresService;
import dm.api.service.OsobaService;
import dm.api.service.PracownikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/pracownik")
@RestController
@CrossOrigin
public class PracownikController {

    Logger logger = LoggerFactory.getLogger(PracownikController.class);

    private final PracownikService pracownikService;
    private final OsobaService osobaService;
    private final AdresService adresService;

    @Autowired
    public PracownikController(PracownikService pracownikService, OsobaService osobaService, AdresService adresService) {
        this.pracownikService = pracownikService;
        this.osobaService = osobaService;
        this.adresService = adresService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count pracownik entity");
        return ResponseEntity.ok(pracownikService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all-pracownik")
    public ResponseEntity<List<DtoPracownikDataResponse>> getAllKlient() {
        logger.info("Get all pracownik data");
        return ResponseEntity.ok(pracownikService.findAllPracownik());
    }

    @ResponseBody
    @GetMapping(value = "/get-pracownik/{id}")
    public ResponseEntity<DtoPracownikDataResponse> getAllKlient(@PathVariable(value = "id") Integer id) {
        logger.info("Get all pracownik data");
        return ResponseEntity.ok(pracownikService.findPracownikById(id));
    }

    @ResponseBody
    @DeleteMapping(value = "/delete-pracownik/{id}")
    public ResponseEntity<?> deletePracownikId(@PathVariable(value = "id") Integer id) {
        logger.info("Delete pracownik on {}", id);
        try {
            pracownikService.deletePracownikById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoPracownikResponse>> getAll() {
        logger.info("Get all pracownik");
        return ResponseEntity.ok(pracownikService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addAdres(@RequestBody DtoPracownikRequest dtoPracownikRequest) {
        logger.info("Add pracownik");
        pracownikService.save(dtoPracownikRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/add-pracownik")
    public ResponseEntity add(@RequestBody DtoAddPracownikRequest klientRequest) {
        logger.info("Add pracownik");
        logger.info(klientRequest.toString());
        pracownikService.add(klientRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePracownik(@PathVariable(value = "id") Integer id) {
        try {
            logger.info("Delete pracownik id ", id);
            pracownikService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: " + id));
        }
    }

    @PutMapping(value = "/update-pracownik/{id}")
    public ResponseEntity<?> updatePracownik(@PathVariable(value = "id") Integer id, @RequestBody DtoUpdatePracownikRequest pracownikRequest) {
        try {
            pracownikService.update(id,pracownikRequest.getPracownik());
            osobaService.update(pracownikRequest.getPracownik().getIdOsoby(),pracownikRequest.getOsoba());
            adresService.update(pracownikRequest.getOsoba().getIdAdresu(),pracownikRequest.getAdres());
            logger.info("Update pracownik");
            return ResponseEntity.ok(pracownikRequest);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Klient on id: " + id));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value = "id") Integer id, @RequestBody DtoPracownikRequest dtoPracownikRequest ) {
        logger.info("Update osoba");
        pracownikService.update(id,dtoPracownikRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getKlient(@PathVariable(value = "id") Integer id) {
        logger.info("Get pracownik on id {}", id);
        try {
            Optional<Pracownik> pracownik = pracownikService.findById(id);
            return ResponseEntity.ok(pracownik.get());
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find osoba on id: " + id));
        }
    }
}
