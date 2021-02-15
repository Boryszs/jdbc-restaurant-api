package dm.api.controller;


import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.model.Klient;
import dm.api.service.KlientService;
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

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count klient entity");
       return ResponseEntity.ok(klientService.count());
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
