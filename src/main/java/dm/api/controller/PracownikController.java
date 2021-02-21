package dm.api.controller;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.response.*;
import dm.api.model.Osoba;
import dm.api.model.Pracownik;
import dm.api.service.AdresService;
import dm.api.service.OsobaService;
import dm.api.service.PracownikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
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
        List<DtoPracownikDataResponse> dtoKlientDataResponses = new LinkedList<>();
        for(Pracownik pracownik : pracownikService.findAll()){
            try {
                DtoPracownikResponse dtoPracownikResponse = new DtoPracownikResponse(pracownik.getIdPracownika(), pracownik.getPensja(), pracownik.getRola(), pracownik.getIdOsoby());
                DtoOsobaResponse dtoOsobaResponse = osobaService.findById(pracownik.getIdOsoby()).map(osoba -> new DtoOsobaResponse(osoba.getIdOsoby(), osoba.getImie(), osoba.getNazwisko(), osoba.getPesel(), osoba.getDataUrodzenia(), osoba.getEmail(), osoba.getTelefon(), osoba.getIdAdresu())).get();
                DtoAdresResponse dtoAdresResponse = adresService.findById(dtoOsobaResponse.getIdAdresu()).map(adres -> new DtoAdresResponse(adres.getIdAdresu(), adres.getMiejscowosc(), adres.getUlica(), adres.getNrDomu(), adres.getKodPocztowy())).get();
                dtoKlientDataResponses.add(new DtoPracownikDataResponse(dtoPracownikResponse, dtoOsobaResponse, dtoAdresResponse));
            }catch (EmptyResultDataAccessException e){
                logger.error(e.getMessage());
            }
        }
        return ResponseEntity.ok(dtoKlientDataResponses);
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
        List<DtoPracownikResponse> dtoPracownikResponseList = new LinkedList<>();
        pracownikService.findAll().stream().map(k -> new DtoPracownikResponse(k.getIdPracownika(),k.getPensja(),k.getRola(), k.getIdOsoby())).forEach(dtoPracownikResponseList::add);
        return ResponseEntity.ok(dtoPracownikResponseList);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addAdres(@RequestBody DtoPracownikResponse pracownikResponse) {
        logger.info("Add pracownik");
        return ResponseEntity.status(201).body(pracownikService.save(new Pracownik(null,pracownikResponse.getPensja(),pracownikResponse.getRola(),pracownikResponse.getIdOsoby())));
    }

    @PostMapping(value = "/add-pracownik")
    public ResponseEntity<Integer> add(@RequestBody DtoAddPracownikRequest klientRequest) {
        logger.info("Add pracownik");
        logger.info(klientRequest.toString());
        return ResponseEntity.status(201).body(pracownikService.add(klientRequest));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePracownik(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete pracownik id ",id);
            return ResponseEntity.ok(pracownikService.deleteById(id));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoPracownikResponse pracownikResponse) {
        logger.info("Update osoba");
        return ResponseEntity.ok(pracownikService.update(new Pracownik(id,pracownikResponse.getPensja(),pracownikResponse.getRola(),pracownikResponse.getIdOsoby())));
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
