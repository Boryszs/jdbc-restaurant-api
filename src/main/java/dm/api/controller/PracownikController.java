package dm.api.controller;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.model.Pracownik;
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

    @Autowired
    public PracownikController(PracownikService pracownikService) {
        this.pracownikService = pracownikService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count pracownik entity");
        return ResponseEntity.ok(pracownikService.count());
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
        return ResponseEntity.ok(pracownikService.save(new Pracownik(null,pracownikResponse.getPensja(),pracownikResponse.getRola(),pracownikResponse.getIdOsoby())));
    }

    @PostMapping(value = "/add-pracownik")
    public ResponseEntity<Integer> add(@RequestBody DtoAddPracownikRequest klientRequest) {
        logger.info("Add klient");
        logger.info(klientRequest.toString());
        return ResponseEntity.ok(pracownikService.add(klientRequest));
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
