package dm.api.service.impl;

import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.response.DtoKlientDataResponse;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.mapper.impl.KlientRowMapper;
import dm.api.model.Adres;
import dm.api.model.Klient;
import dm.api.model.Osoba;
import dm.api.repository.AdresRepository;
import dm.api.repository.KlientRepository;
import dm.api.repository.OsobaRepository;
import dm.api.service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class KlientServiceImpl implements KlientService {

    private KlientRepository klientRepository;
    private OsobaRepository osobaRepository;
    private AdresRepository adresRepository;

    @Autowired
    public KlientServiceImpl(KlientRepository klientRepository, OsobaRepository osobaRepository, AdresRepository adresRepository) {
        this.klientRepository = klientRepository;
        this.osobaRepository = osobaRepository;
        this.adresRepository = adresRepository;
    }

    @Override
    public int count() {
        return klientRepository.count();
    }

    @Override
    public void save(DtoKlientRequest dtoKlientRequest) {
         klientRepository.save(new KlientRowMapper().convert(dtoKlientRequest));
    }

    @Override
    public void update(Integer id,DtoKlientRequest dtoKlientRequest) {
         klientRepository.update(new KlientRowMapper().update(klientRepository.findById(id).get(),dtoKlientRequest));
    }

    @Override
    public void deleteById(int id) {
         klientRepository.deleteById(id);
    }

    @Override
    public void deleteKlientById(Integer id) {
        klientRepository.deleteKlientById(id);
    }

    @Override
    public void add(DtoAddKlientRequest klient) {
       Integer idAdres = adresRepository.save(new Adres(null,klient.getAdres().getMiejscowosc(),klient.getAdres().getUlica(),klient.getAdres().getNrDomu(),klient.getAdres().getKodPocztowy()));
       Integer idOsoba = osobaRepository.save(new Osoba(null,klient.getOsoba().getImie(),klient.getOsoba().getNazwisko(),klient.getOsoba().getPesel(),klient.getOsoba().getDataUrodzenia(),klient.getOsoba().getEmail(),klient.getOsoba().getTelefon(),idAdres));
       klientRepository.save(new Klient(null,klient.getLogin(),klient.getHaslo(),idOsoba));
    }

    @Override
    public List<DtoKlientResponse> findAll() {
        List<DtoKlientResponse> klientResponseList = new LinkedList<>();
        klientRepository.findAll().stream().map(klient -> new KlientRowMapper().toDto(klient)).forEach(klientResponseList::add);
        return klientResponseList;
    }

    @Override
    public List<DtoKlientDataResponse> findAllKlient() {
        return klientRepository.findAllKlient();
    }

    @Override
    public DtoKlientDataResponse findKlientId(int id) {
        return klientRepository.findKlientId(id);
    }

    @Override
    public Optional<DtoKlientResponse> findById(int id) {
        return  Optional.of(new KlientRowMapper().toDto(klientRepository.findById(id).get()));
    }
}
