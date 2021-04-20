package dm.api.service.impl;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.mapper.Convert;
import dm.api.model.Adres;
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
    private final Convert<Adres, DtoAdresRequest, DtoAdresResponse> addreMapper;

    @Autowired
    public AdresServiceImpl(AdresRepository adresRepository, Convert<Adres, DtoAdresRequest, DtoAdresResponse> addreMapper) {
        this.adresRepository = adresRepository;
        this.addreMapper = addreMapper;
    }

    @Override
    public int count() {
        return adresRepository.count();
    }

    @Override
    public int save(DtoAdresRequest dtoAdresRequest) {
        return adresRepository.save(addreMapper.convert(dtoAdresRequest));
    }

    @Override
    public int update(Integer id, DtoAdresRequest dtoAdresRequest) {
        return adresRepository.update(addreMapper.update(adresRepository.findById(id).get(),dtoAdresRequest));
    }

    @Override
    public int deleteById(int id) {
        return adresRepository.deleteById(id);
    }

    @Override
    public List<DtoAdresResponse> findAll() {
        List<DtoAdresResponse> adresResponseList = new LinkedList<>();
        adresRepository.findAll().stream().map(adres -> addreMapper.toDto(adres)).forEach(adresResponseList::add);
        return adresResponseList;
    }

    @Override
    public Optional<DtoAdresResponse> findById(int id) {
        return Optional.of(addreMapper.toDto(adresRepository.findById(id).get()));
    }
}
