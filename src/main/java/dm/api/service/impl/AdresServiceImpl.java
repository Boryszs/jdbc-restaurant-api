package dm.api.service.impl;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.mapper.impl.AdresRowMapper;
import dm.api.repository.AdresRepository;
import dm.api.service.AdresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AdresServiceImpl implements AdresService {

    private final AdresRepository adresRepository;

    @Autowired
    public AdresServiceImpl(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }

    @Override
    public int count() {
        return adresRepository.count();
    }

    @Override
    public int save(DtoAdresRequest dtoAdresRequest) {
        return adresRepository.save(new AdresRowMapper().convert(dtoAdresRequest));
    }

    @Override
    public int update(Integer id, DtoAdresRequest dtoAdresRequest) {
        return adresRepository.update(new AdresRowMapper().update(adresRepository.findById(id).get(),dtoAdresRequest));
    }

    @Override
    public int deleteById(int id) {
        return adresRepository.deleteById(id);
    }

    @Override
    public List<DtoAdresResponse> findAll() {
        List<DtoAdresResponse> adresResponseList = new LinkedList<>();
        adresRepository.findAll().stream().map(adres -> new AdresRowMapper().toDto(adres)).forEach(adresResponseList::add);
        return adresResponseList;
    }

    @Override
    public Optional<DtoAdresResponse> findById(int id) {
        return Optional.of(new AdresRowMapper().toDto(adresRepository.findById(id).get()));
    }
}
