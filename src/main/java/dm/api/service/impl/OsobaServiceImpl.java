package dm.api.service.impl;

import dm.api.dto.request.DtoOsobaRequest;
import dm.api.dto.response.DtoOsobaResponse;
import dm.api.mapper.impl.OsobaRowMapper;
import dm.api.repository.OsobaRepository;
import dm.api.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OsobaServiceImpl implements OsobaService {

   private final OsobaRepository osobaRepository;

   @Autowired
    public OsobaServiceImpl(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    @Override
    public int count() {
        return osobaRepository.count();
    }

    @Override
    public int save(DtoOsobaRequest dtoOsobaResponse) {
        return osobaRepository.save(new OsobaRowMapper().convert(dtoOsobaResponse));
    }

    @Override
    public int update(Integer id, DtoOsobaRequest dtoOsobaRequest) {
        return osobaRepository.update(new OsobaRowMapper().update(osobaRepository.findById(id).get(),dtoOsobaRequest));
    }

    @Override
    public int deleteById(int id) {
        return osobaRepository.deleteById(id);
    }

    @Override
    public List<DtoOsobaResponse> findAll() {
        List<DtoOsobaResponse> osobaResponseList = new LinkedList<>();
        osobaRepository.findAll().stream().map(osoba -> new OsobaRowMapper().toDto(osoba)).forEach(osobaResponseList::add);
        return osobaResponseList;
    }

    @Override
    public Optional<DtoOsobaResponse> findById(int id) {
        return Optional.of(new OsobaRowMapper().toDto(osobaRepository.findById(id).get()));
    }
}
