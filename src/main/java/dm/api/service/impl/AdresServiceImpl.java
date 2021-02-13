package dm.api.service.impl;

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
    public int save(Adres adres) {
        return adresRepository.save(adres);
    }

    @Override
    public int update(Adres adres) {
        return adresRepository.update(adres);
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Adres> findAll() {
        return adresRepository.findAll();
    }

    @Override
    public Optional<Adres> findById(int id) {
        return adresRepository.findById(id);
    }
}
