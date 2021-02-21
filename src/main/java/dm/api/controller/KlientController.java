package dm.api.controller;


import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.request.DtoUpdateKlientRequest;
import dm.api.dto.response.*;
import dm.api.model.Adres;
import dm.api.model.Klient;
import dm.api.model.Osoba;
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
    @GetMapping(value = "/get-klient/{id}")
    public ResponseEntity<DtoKlientDataResponse> getKlientId(@PathVariable(value="id") Integer id) {
        logger.info("Get klient data on id {}", id);
        DtoKlientDataResponse dtoKlientDataResponses = new DtoKlientDataResponse();
        Klient klient = klientService.finById(id).get();
            try{
                DtoKlientResponse dtoKlientResponse = new DtoKlientResponse(klient.getIdKlienta(),klient.getLogin(),klient.getHaslo(),klient.getIdOsoby());
                DtoOsobaResponse dtoOsobaResponse = osobaService.findById(klient.getIdOsoby()).map(osoba ->  new DtoOsobaResponse(osoba.getIdOsoby(),osoba.getImie(),osoba.getNazwisko(),osoba.getPesel(),osoba.getDataUrodzenia(),osoba.getEmail(),osoba.getTelefon(),osoba.getIdAdresu())).get();
                DtoAdresResponse dtoAdresResponse = adresService.findById(dtoOsobaResponse.getIdAdresu()).map(adres -> new DtoAdresResponse(adres.getIdAdresu(),adres.getMiejscowosc(),adres.getUlica(),adres.getNrDomu(),adres.getKodPocztowy())).get();
                dtoKlientDataResponses = new DtoKlientDataResponse(dtoKlientResponse,dtoOsobaResponse,dtoAdresResponse);
            }catch (EmptyResultDataAccessException e){
                logger.error(e.getMessage());
            }
        return ResponseEntity.ok(dtoKlientDataResponses);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete-klient/{id}")
    public ResponseEntity<?> deleteKlientId(@PathVariable(value="id") Integer id) {
        logger.info("Delete klient on {}",id);
        try{
            Klient klient = klientService.finById(id).get();
            Osoba osoba = osobaService.findById(klient.getIdOsoby()).get();
            klientService.deleteById(klient.getIdKlienta());
            osobaService.deleteById(osoba.getIdOsoby());
            adresService.deleteById(osoba.getIdAdresu());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(true);
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
        logger.info("Add klient {}",klientRequest.toString());
        return ResponseEntity.status(201).body(klientService.add(klientRequest));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoKlientRequest klientRequest) {
        logger.info("Update klient");
        return ResponseEntity.ok(klientService.update(new Klient(id,klientRequest.getLogin(),klientRequest.getHaslo(),klientRequest.getIdOsoby())));
    }

    @PutMapping(value = "/update-klient/{id}")
    public ResponseEntity<?> updateKlient(@PathVariable(value="id") Integer id, @RequestBody DtoUpdateKlientRequest klientRequest) {
        try{
            System.out.println(klientRequest);
            Klient klient = new Klient(id,klientRequest.getKlient().getLogin(),klientRequest.getKlient().getHaslo(),klientRequest.getKlient().getIdOsoby());
            Osoba osoba = new Osoba(klientRequest.getKlient().getIdOsoby(),klientRequest.getOsoba().getImie(),klientRequest.getOsoba().getNazwisko(),klientRequest.getOsoba().getPesel(),klientRequest.getOsoba().getDataUrodzenia(),klientRequest.getOsoba().getEmail(),klientRequest.getOsoba().getTelefon(),klientRequest.getOsoba().getIdAdresu());
            Adres adres = new Adres(klientRequest.getOsoba().getIdAdresu(),klientRequest.getAdres().getMiejscowosc(),klientRequest.getAdres().getUlica(),klientRequest.getAdres().getNrDomu(),klientRequest.getAdres().getKodPocztowy());
            klientService.update(klient);
            osobaService.update(osoba);
            adresService.update(adres);
            logger.info("Update klient");
            return ResponseEntity.ok(klient);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Klient on id: "+id));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteKlient(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete klient id ",id);
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
