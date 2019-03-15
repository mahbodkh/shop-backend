package app.store.service;

import app.store.persistence.domain.Bank;
import app.store.persistence.repository.BankRepository;
import app.store.service.dto.BankDto;
import app.store.service.mapper.BankMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {
    private final Logger log = LoggerFactory.getLogger(BankService.class);

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    public BankService(BankRepository bankRepository, BankMapper bankMapper) {

        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    public Optional<String> createBank(BankDto bankDto) {
        return Optional.of(bankMapper.toEntity(bankDto))
                .map(bank -> {
                    Bank result = bankRepository.save(bank);
                    log.debug("Save Information for PaymentMethod: {}", bank);
                    return result.getId().toString();
                });
    }

    public Optional<BankDto> getBank(String id) {
        return Optional.of(bankRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(bankMapper::toDto);
    }

    public Optional<BankDto> updateBank(BankDto bankDto, String id) {
        return Optional.of(bankRepository.findById(new ObjectId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(bank -> {
                    bank.setName(bankDto.getName());
                    bank.setMobile(bankDto.getMobile());
                    bank.setUsername(bankDto.getUsername());
                    bank.setPassword(bankDto.getPassword());
                    bank.setTerminalId(bankDto.getTerminalId());
                    bank.setMerchant(bankDto.getMerchant());
                    bank.setGatewayUrl(bankDto.getGatewayUrl());
                    bank.setGatewayPostfix(bankDto.getGatewayPostfix());
                    bank.setGatewayPostfixWeb(bankDto.getGatewayPostfixWeb());
                    bank.setGatewayPostfixMobile(bankDto.getGatewayPostfixMobile());

                    Bank result = bankRepository.save(bank);
                    log.debug("Changed Information for PaymentMethod: {}", result);
                    return result;
                }).map(bankMapper::toDto);
    }

    public void deleteBank(String id) {
        bankRepository.findById(new ObjectId(id))
                .ifPresent(bank -> {
                    bankRepository.save(bank);
                    log.debug("Deleted PaymentMethod: {}", bank);
                });
    }

    public Page<BankDto> getAllBank(Pageable pageable) {
        return bankRepository.findAll(pageable)
                .map(bankMapper::toDto);
    }


    public boolean isExistByName(String name) {
        return bankRepository.findBankByName(name)
                .isPresent();
    }

    public boolean isExistById(String id) {
        return bankRepository.existsById(new ObjectId(id));
    }


}
