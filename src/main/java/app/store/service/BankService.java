package app.store.service;

import app.store.persistence.repository.BankRepository;
import app.store.service.mapper.BankMapper;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    public BankService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    public void getBankById(String bankId) {

    }
}
