package dm.api.service.impl;

import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.model.Adres;
import dm.api.model.Klient;
import dm.api.model.Osoba;
import dm.api.repository.AdresRepository;
import dm.api.repository.KlientRepository;
import dm.api.repository.OsobaRepository;
import dm.api.service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int save(Klient klient) {
        return klientRepository.save(klient);
    }

    @Override
    public int update(Klient klient) {
        return klientRepository.update(klient);
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int add(DtoAddKlientRequest klient) {
       Integer idAdres = adresRepository.save(new Adres(null,klient.getAdres().getMiejscowosc(),klient.getAdres().getUlica(),klient.getAdres().getNrDomu(),klient.getAdres().getKodPocztowy()));
       Integer idOsoba = osobaRepository.save(new Osoba(null,klient.getOsoba().getImie(),klient.getOsoba().getNazwisko(),klient.getOsoba().getPesel(),klient.getOsoba().getDataUrodzenia(),klient.getOsoba().getEmail(),klient.getOsoba().getTelefon(),idAdres));
       return klientRepository.save(new Klient(null,klient.getLogin(),klient.getHaslo(),idOsoba));
    }

    @Override
    public List<Klient> findAll() {
        return klientRepository.findAll();
    }

    @Override
    public Optional<Klient> finById(int id) {
        return klientRepository.findById(id);
    }
}
