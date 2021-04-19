package dm.api.controller;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.request.DtoUpdatePracownikRequest;
import dm.api.dto.response.*;
import dm.api.model.Adres;
import dm.api.model.Osoba;
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

    private PracownikService pracownikService;
    private OsobaService osobaService;
    private AdresService adresService;

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
        return ResponseEntity.ok(pracownikService.findAllEmployee());
    }

    @ResponseBody
    @GetMapping(value = "/get-pracownik/{id}")
    public ResponseEntity<DtoPracownikDataResponse> getAllKlient(@PathVariable(value="id") Integer id) {
        logger.info("Get all pracownik data");
        DtoPracownikDataResponse dtoKlientDataResponses = new DtoPracownikDataResponse();
        Pracownik pracownik = pracownikService.findById(id).get();
            try {
                DtoPracownikResponse dtoPracownikResponse = new DtoPracownikResponse(pracownik.getIdPracownika(), pracownik.getPensja(), pracownik.getRola(), pracownik.getIdOsoby());
                DtoOsobaResponse dtoOsobaResponse = osobaService.findById(pracownik.getIdOsoby()).map(osoba -> new DtoOsobaResponse(osoba.getIdOsoby(), osoba.getImie(), osoba.getNazwisko(), osoba.getPesel(), osoba.getDataUrodzenia(), osoba.getEmail(), osoba.getTelefon(), osoba.getIdAdresu())).get();
                DtoAdresResponse dtoAdresResponse = adresService.findById(dtoOsobaResponse.getIdAdresu()).map(adres -> new DtoAdresResponse(adres.getIdAdresu(), adres.getMiejscowosc(), adres.getUlica(), adres.getNrDomu(), adres.getKodPocztowy())).get();
                dtoKlientDataResponses =  new DtoPracownikDataResponse(dtoPracownikResponse, dtoOsobaResponse, dtoAdresResponse);
            }catch (EmptyResultDataAccessException e){
                logger.error(e.getMessage());
            }
        return ResponseEntity.ok(dtoKlientDataResponses);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete-pracownik/{id}")
    public ResponseEntity<?> deletePracownikId(@PathVariable(value="id") Integer id) {
        logger.info("Delete pracownik on {}",id);
        try{
            Pracownik pracownik = pracownikService.findById(id).get();
            Osoba osoba = osobaService.findById(pracownik.getIdOsoby()).get();
            pracownikService.deleteById(pracownik.getIdPracownika());
            osobaService.deleteById(osoba.getIdOsoby());
            adresService.deleteById(osoba.getIdAdresu());
        }catch (EmptyResultDataAccessException e){
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
    public ResponseEntity<Integer> addAdres(@RequestBody DtoPracownikResponse pracownikResponse) {
        logger.info("Add pracownik");
        pracownikService.save(new Pracownik(null,pracownikResponse.getPensja(),pracownikResponse.getRola(),pracownikResponse.getIdOsoby()));
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
    public ResponseEntity<?> deletePracownik(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete pracownik id ",id);
            pracownikService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

    @PutMapping(value = "/update-pracownik/{id}")
    public ResponseEntity<?> updatePracownik(@PathVariable(value="id") Integer id, @RequestBody DtoUpdatePracownikRequest pracownikRequest) {
        try{
            Pracownik pracownik = new Pracownik(id,pracownikRequest.getPracownik().getPensja(),pracownikRequest.getPracownik().getRola(),pracownikRequest.getPracownik().getIdOsoby());
            Osoba osoba = new Osoba(pracownikRequest.getPracownik().getIdOsoby(),pracownikRequest.getOsoba().getImie(),pracownikRequest.getOsoba().getNazwisko(),pracownikRequest.getOsoba().getPesel(),pracownikRequest.getOsoba().getDataUrodzenia(),pracownikRequest.getOsoba().getEmail(),pracownikRequest.getOsoba().getTelefon(),pracownikRequest.getOsoba().getIdAdresu());
            Adres adres = new Adres(pracownikRequest.getOsoba().getIdAdresu(),pracownikRequest.getAdres().getMiejscowosc(),pracownikRequest.getAdres().getUlica(),pracownikRequest.getAdres().getNrDomu(),pracownikRequest.getAdres().getKodPocztowy());
            pracownikService.update(pracownik);
            osobaService.update(osoba);
            adresService.update(adres);
            logger.info("Update pracownik");
            return ResponseEntity.ok(pracownikRequest);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Klient on id: "+id));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoPracownikResponse pracownikResponse) {
        logger.info("Update osoba");
        pracownikService.update(new Pracownik(id,pracownikResponse.getPensja(),pracownikResponse.getRola(),pracownikResponse.getIdOsoby()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getKlient(@PathVariable(value="id") Integer id) {
        logger.info("Get pracownik on id {}",id);
        try {
            Optional<Pracownik> pracownik = pracownikService.findById(id);
            return ResponseEntity.ok(pracownik.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find osoba on id: "+id));
        }
    }
}
