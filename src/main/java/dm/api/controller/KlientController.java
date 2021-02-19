package dm.api.controller;


import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.response.*;
import dm.api.model.Klient;
import dm.api.service.AdresService;
import dm.api.service.KlientService;
import dm.api.service.OsobaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/klient")
@RestController
@CrossOrigin
public class KlientController {

    Logger logger = LoggerFactory.getLogger(KlientController.class);

    private KlientService klientService;
    private OsobaService osobaService;
    private AdresService adresService;

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
        List<DtoKlientDataResponse> dtoKlientDataResponses = new LinkedList<>();
        for(Klient klient : klientService.findAll()){
           try{
                DtoKlientResponse dtoKlientResponse = new DtoKlientResponse(klient.getIdKlienta(),klient.getLogin(),klient.getHaslo(),klient.getIdOsoby());
                DtoOsobaResponse dtoOsobaResponse = osobaService.findById(klient.getIdOsoby()).map(osoba ->  new DtoOsobaResponse(osoba.getIdOsoby(),osoba.getImie(),osoba.getNazwisko(),osoba.getPesel(),osoba.getDataUrodzenia(),osoba.getEmail(),osoba.getTelefon(),osoba.getIdAdresu())).get();
                DtoAdresResponse dtoAdresResponse = adresService.findById(dtoOsobaResponse.getIdAdresu()).map(adres -> new DtoAdresResponse(adres.getIdAdresu(),adres.getMiejscowosc(),adres.getUlica(),adres.getNrDomu(),adres.getKodPocztowy())).get();
                dtoKlientDataResponses.add(new DtoKlientDataResponse(dtoKlientResponse,dtoOsobaResponse,dtoAdresResponse));
            }catch (EmptyResultDataAccessException e){
               logger.error(e.getMessage());
           }
        }
        return ResponseEntity.ok(dtoKlientDataResponses);
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoKlientResponse>> getAll() {
        logger.info("Get all klient");
        List<DtoKlientResponse> dtoKlientResponseList = new LinkedList<>();
        klientService.findAll().stream().map(k -> new DtoKlientResponse(k.getIdKlienta(), k.getLogin(), k.getHaslo(), k.getIdOsoby())).forEach(dtoKlientResponseList::add);
        return ResponseEntity.ok(dtoKlientResponseList);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addKlient(@RequestBody DtoKlientRequest klientRequest) {
        logger.info("Add klient");
        return ResponseEntity.status(201).body(klientService.save(new Klient(null,klientRequest.getLogin(),klientRequest.getHaslo(),klientRequest.getIdOsoby())));
    }

    @PostMapping(value = "/add-klient")
    public ResponseEntity<Integer> add(@RequestBody DtoAddKlientRequest klientRequest) {
        logger.info("Add klient");
        return ResponseEntity.status(201).body(klientService.add(klientRequest));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoKlientRequest klientRequest) {
        logger.info("Update klient");
        return ResponseEntity.ok(klientService.update(new Klient(id,klientRequest.getLogin(),klientRequest.getHaslo(),klientRequest.getIdOsoby())));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteKlient(@PathVariable(value="id") Integer id) {
        try{
            logger.info("delete adres id ",id);
            return ResponseEntity.ok(klientService.deleteById(id));
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
            Optional<Klient> klient = klientService.finById(id);
            return ResponseEntity.ok(klient.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Klient on id: "+id));
        }
    }
}
