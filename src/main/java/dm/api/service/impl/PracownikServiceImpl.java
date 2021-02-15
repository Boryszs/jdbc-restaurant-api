package dm.api.service.impl;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.model.Adres;
import dm.api.model.Osoba;
import dm.api.model.Pracownik;
import dm.api.repository.AdresRepository;
import dm.api.repository.OsobaRepository;
import dm.api.repository.PracownikRepository;
import dm.api.service.PracownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracownikServiceImpl implements PracownikService {

    private PracownikRepository pracownikRepository;
    private OsobaRepository osobaRepository;
    private AdresRepository adresRepository;

    @Autowired
    public PracownikServiceImpl(PracownikRepository pracownikRepository, OsobaRepository osobaRepository, AdresRepository adresRepository) {
        this.pracownikRepository = pracownikRepository;
        this.osobaRepository = osobaRepository;
        this.adresRepository = adresRepository;
    }

    @Override
    public int count() {
        return pracownikRepository.count();
    }

    @Override
    public int save(Pracownik pracownik) {
        return 0;
    }

    @Override
    public int update(Pracownik pracownik) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int add(DtoAddPracownikRequest pracownik) {
        Integer idAdres = adresRepository.save(new Adres(null,pracownik.getAdres().getMiejscowosc(),pracownik.getAdres().getUlica(),pracownik.getAdres().getNrDomu(),pracownik.getAdres().getKodPocztowy()));
        System.out.println(idAdres);
        Integer idOsoba = osobaRepository.save(new Osoba(null,pracownik.getOsoba().getImie(),pracownik.getOsoba().getNazwisko(),pracownik.getOsoba().getPesel(),pracownik.getOsoba().getDataUrodzenia(),pracownik.getOsoba().getEmail(),pracownik.getOsoba().getTelefon(),idAdres));
        System.out.println(idOsoba);
        return pracownikRepository.save(new Pracownik(null,pracownik.getPensja(),pracownik.getRola(),idOsoba));
    }

    @Override
    public List<Pracownik> findAll() {
        return pracownikRepository.findAll();
    }

    @Override
    public Optional<Pracownik> findById(int id) {
        return pracownikRepository.findById(id);
    }
}
