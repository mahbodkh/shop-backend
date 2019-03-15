package app.store.web.rest;

import app.store.service.BankService;
import app.store.service.dto.BankDto;
import app.store.web.rest.error.BankAlreadyUsedException;
import app.store.web.rest.error.BankNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class BankResource {
    private final Logger log = LoggerFactory.getLogger(BankResource.class);


    private final BankService bankService;

    public BankResource(BankService bankService) {
        this.bankService = bankService;
    }


    @GetMapping("/bank/{id}")
    public ResponseEntity<BankDto> getBank(@Valid @PathVariable String id) {
        log.debug("REST request to get PaymentMethod : {}", id);
        Optional<BankDto> bank = bankService.getBank(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("bank.get", " "))
                .body(bank.get());
    }

    @PostMapping("/bank")
    public ResponseEntity<String> createBank(BankDto bankDto) throws URISyntaxException {
        log.debug("REST request to save PaymentMethod : {}", bankDto);

        if (bankService.isExistByName(bankDto.getName())) {
            throw new BankAlreadyUsedException();
        }
        String bankId = bankService.createBank(bankDto).get();

        return ResponseEntity.created(new URI("/api/bank/" + bankId))
                .headers(HeaderUtil.createAlert("bank.created", bankId))
                .body(bankId);
    }

    @PutMapping("/bank/{id}")
    public ResponseEntity<BankDto> updateBank(@Valid @RequestBody BankDto bankDto, @Valid @PathVariable String id) throws URISyntaxException {
        log.debug("REST request to update PaymentMethod: {} with id : {}", bankDto, id);
        if (!bankService.isExistById(id))
            throw new BankNotFoundException();
        BankDto result = bankService.updateBank(bankDto, id).get();
        return ResponseEntity.created(new URI("/api/bank/" + result.getId()))
                .headers(HeaderUtil.createAlert("bank.created", ""))
                .body(result);
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<Void> deleteBank(@Valid @PathVariable String id) {
        log.debug("REST request to delete bank: {}", id);
        bankService.deleteBank(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("bank.deleted", "")).build();
    }

    @GetMapping("/bank/all")
    public ResponseEntity<List<BankDto>> getAllBank(Pageable pageable) {
        log.debug("REST request to get all PaymentMethod by pageable: {}", pageable);
        final Page<BankDto> bank = bankService.getAllBank(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(bank, "/api/banks");
        return new ResponseEntity<>(bank.getContent(), headers, HttpStatus.OK);
    }

}
