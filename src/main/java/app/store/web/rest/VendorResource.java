package app.store.web.rest;

import app.store.service.VendorService;
import app.store.service.dto.VendorDto;
import app.store.web.rest.error.VendorAlreadyUsedException;
import app.store.web.rest.error.VendorNotFoundException;
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
public class VendorResource {
    private final Logger log = LoggerFactory.getLogger(VendorResource.class);

    private final VendorService vendorService;

    public VendorResource(VendorService vendorService) {

        this.vendorService = vendorService;
    }

    @GetMapping("/vendor/{id}")
    public ResponseEntity<VendorDto> getVendor(@Valid @PathVariable String id) {
        log.debug("REST request to get Vendor : {}", id);
        Optional<VendorDto> vendor = vendorService.getVendor(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("vendor.get", " "))
                .body(vendor.get());
    }

    @PostMapping("/vendor")
    public ResponseEntity<String> createVendor(@Valid @RequestBody VendorDto vendorDto) throws URISyntaxException {
        log.debug("REST request to save Vendor : {}", vendorDto);

        if (vendorService.isExistByName(vendorDto.getName()).isPresent()) {
            throw new VendorAlreadyUsedException();
        }
        String vendorId = vendorService.createVendor(vendorDto).get();

        return ResponseEntity.created(new URI("/api/vendor/" + vendorId))
                .headers(HeaderUtil.createAlert("vendor.created", vendorId))
                .body(vendorId);
    }

    @PutMapping("/vendor/{id}")
    public ResponseEntity<VendorDto> updateVendor(@Valid @RequestBody VendorDto vendorDto, @Valid @PathVariable String id) throws URISyntaxException {
        log.debug("REST request to update Vendor: {} with id : {}", vendorDto, id);
        if (!vendorService.isExistById(id))
            throw new VendorNotFoundException();

        VendorDto result = vendorService.updateVendor(vendorDto, id).get();
        return ResponseEntity.created(new URI("/api/vendor/" + result.getId()))
                .headers(HeaderUtil.createAlert("vendor.created", ""))
                .body(result);
    }

    @DeleteMapping("/vendor/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String id) {
        log.debug("REST request to delete vendor: {}", id);
        vendorService.deleteVendor(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("vendor.deleted", "")).build();
    }

    @GetMapping("/vendor/all")
    public ResponseEntity<List<VendorDto>> getAllVendor(Pageable pageable) {
        log.debug("REST request to get all Vendor by pageable: {}", pageable);
        final Page<VendorDto> vendor = vendorService.getAllVendor(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(vendor, "/api/vendors");
        return new ResponseEntity<>(vendor.getContent(), headers, HttpStatus.OK);
    }

}
