package dm.api.service.impl;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.request.DtoPracownikRequest;
import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.mapper.Convert;
import dm.api.model.Adres;
import dm.api.model.Osoba;
import dm.api.model.Pracownik;
import dm.api.repository.AdresRepository;
import dm.api.repository.OsobaRepository;
import dm.api.repository.PracownikRepository;
import dm.api.service.PracownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PracownikServiceImpl implements PracownikService {

    private final PracownikRepository pracownikRepository;
    private final OsobaRepository osobaRepository;
    private final AdresRepository adresRepository;
    private final Convert<Pracownik, DtoPracownikRequest, DtoPracownikResponse> pracownikMapper;

    @Autowired
    public PracownikServiceImpl(PracownikRepository pracownikRepository, OsobaRepository osobaRepository, AdresRepository adresRepository, Convert<Pracownik, DtoPracownikRequest, DtoPracownikResponse> pracownikMapper) {
        this.pracownikRepository = pracownikRepository;
        this.osobaRepository = osobaRepository;
        this.adresRepository = adresRepository;
        this.pracownikMapper = pracownikMapper;
    }

    @Override
    public int count() {
        return pracownikRepository.count();
    }

    @Override
    public void save(DtoPracownikRequest dtoPracownikResponse) {
        pracownikRepository.save(pracownikMapper.convert(dtoPracownikResponse));
    }

    @Override
    public void update(Integer id,DtoPracownikRequest dtoPracownikRequest) {
         pracownikRepository.update(pracownikMapper.update(pracownikRepository.findById(id).get(),dtoPracownikRequest));
    }

    @Override
    public void deleteById(int id) {
         pracownikRepository.deleteById(id);
    }

    @Override
    public void add(DtoAddPracownikRequest pracownik) {
        Integer idAdres = adresRepository.save(new Adres(null,pracownik.getAdres().getMiejscowosc(),pracownik.getAdres().getUlica(),pracownik.getAdres().getNrDomu(),pracownik.getAdres().getKodPocztowy()));
        Integer idOsoba = osobaRepository.save(new Osoba(null,pracownik.getOsoba().getImie(),pracownik.getOsoba().getNazwisko(),pracownik.getOsoba().getPesel(),pracownik.getOsoba().getDataUrodzenia(),pracownik.getOsoba().getEmail(),pracownik.getOsoba().getTelefon(),idAdres));
        pracownikRepository.save(new Pracownik(null,pracownik.getPensja(),pracownik.getRola(),idOsoba));
    }

    @Override
    public List<DtoPracownikResponse> findAll() {
        List<DtoPracownikResponse> pracownikResponseList = new LinkedList<>();
        pracownikRepository.findAll().stream().map(pracownik -> pracownikMapper.toDto(pracownik)).forEach(pracownikResponseList::add);
        return pracownikResponseList;
    }

    @Override
    public List<DtoPracownikDataResponse> findAllPracownik() {
        return pracownikRepository.findAllPracownik();
    }

    @Override
    public DtoPracownikDataResponse findPracownikById(int id) {
        return pracownikRepository.findPracownikById(id);
    }

    @Override
    public void deletePracownikById(Integer id) {
        pracownikRepository.deletePracownikById(id);
    }

    @Override
    public Optional<Pracownik> findById(int id) {
        return pracownikRepository.findById(id);
    }
}
