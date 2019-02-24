package app.store.web.rest;

import app.store.service.AuditEventService;
import app.store.web.rest.util.PaginationUtil;
import app.store.web.rest.util.ResponseUtil;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@RestController
@RequestMapping("/management/audits")
public class AuditResource {

    private final AuditEventService auditEventService;

    public AuditResource(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
    }


    @GetMapping
    public ResponseEntity<List<AuditEvent>> getAll(Pageable pageable) {
        Page<AuditEvent> page = auditEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping(params = {"fromDate", "toDate"})
    public ResponseEntity<List<AuditEvent>> getByDates(
            @RequestParam(value = "fromDate") LocalDate fromDate,
            @RequestParam(value = "toDate") LocalDate toDate,
            Pageable pageable) {

        Page<AuditEvent> page = auditEventService.findByDates(
                fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(),
                pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/management/audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/{id:.+}")
    public ResponseEntity<AuditEvent> get(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(auditEventService.find(id));
    }
}
