package dm.api.service.impl;

import dm.api.model.Klient;
import dm.api.repository.KlientRepository;
import dm.api.service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlientServiceImpl implements KlientService {

    private KlientRepository klientRepository;

    @Autowired
    public KlientServiceImpl(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
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
    public List<Klient> findAll() {
        return klientRepository.findAll();
    }

    @Override
    public Optional<Klient> finById(int id) {
        return klientRepository.findById(id);
    }
}
