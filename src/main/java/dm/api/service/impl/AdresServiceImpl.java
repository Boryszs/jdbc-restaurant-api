package dm.api.service.impl;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.mapper.AdresRowMapper;
import dm.api.model.Adres;
import dm.api.repository.AdresRepository;
import dm.api.service.AdresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresServiceImpl implements AdresService {

    private AdresRepository adresRepository;

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
    public int update(Adres adres) {
        return adresRepository.update(adres);
    }

    @Override
    public int deleteById(int id) {
        return adresRepository.deleteById(id);
    }

    @Override
    public List<DtoAdresResponse> findAll() {
        return adresRepository.findAll();
    }

    @Override
    public Optional<DtoAdresResponse> findById(int id) {
        return adresRepository.findById(id);
    }
}
