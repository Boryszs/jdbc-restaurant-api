package dm.api.controller;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.dto.response.DtoError;
import dm.api.model.Adres;
import dm.api.service.AdresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
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
        List<DtoAdresResponse> dtoAdresResponse = new LinkedList<>();
        adresService.findAll().stream().map(k -> new DtoAdresResponse(k.getIdAdresu(),k.getMiejscowosc(),k.getUlica(),k.getNrDomu(),k.getKodPocztowy())).forEach(dtoAdresResponse::add);
        return ResponseEntity.ok(dtoAdresResponse);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addAdres(@RequestBody DtoAdresRequest adresRequest) {
        logger.info("Add adres");
        return ResponseEntity.ok(adresService.save(new Adres(null,adresRequest.getMiejscowosc(),adresRequest.getUlica(),adresRequest.getNrDomu(),adresRequest.getKodPocztowy())));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> addUpdate(@PathVariable(value="id") Integer id,@RequestBody DtoAdresRequest adresRequest) {
        logger.info("Update adres");
        return ResponseEntity.ok(adresService.update(new Adres(id,adresRequest.getMiejscowosc(),adresRequest.getUlica(),adresRequest.getNrDomu(),adresRequest.getKodPocztowy())));
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getKlient(@PathVariable(value="id") Integer id) {
        logger.info("Get Adres on id {}",id);
        try {
            Optional<Adres> adres = adresService.findById(id);
            return ResponseEntity.ok(adres.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find Adres on id: "+id));
        }
    }

}
