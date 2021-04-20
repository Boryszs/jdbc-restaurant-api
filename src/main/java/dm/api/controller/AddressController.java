package dm.api.controller;

import dm.api.dto.request.DtoAddressRequest;
import dm.api.dto.response.DtoAddressResponse;
import dm.api.dto.response.DtoError;
import dm.api.service.AddressService;
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
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count address entity");
        return ResponseEntity.ok(addressService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoAddressResponse>> getAll() {
        logger.info("Get all address");
        return ResponseEntity.ok(addressService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addAddress(@RequestBody DtoAddressRequest addressRequest) {
        logger.info("Add address");
        addressService.save(addressRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable(value="id") Integer id, @RequestBody DtoAddressRequest dtoAddressRequest) {
        try{
            logger.info("Update address");
            return ResponseEntity.ok(addressService.update(id,dtoAddressRequest));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find address on id: "+id));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete address id {}",id);
            return ResponseEntity.ok(addressService.deleteById(id));
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find address on id: "+id));
        }
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getAddressId(@PathVariable(value="id") Integer id) {
        logger.info("Get address on id {}",id);
        try {
            Optional<DtoAddressResponse> address = addressService.findById(id);
            return ResponseEntity.ok(address.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find address on id: "+id));
        }
    }

}
