package app.store.service;

import app.store.persistence.domain.Description;
import app.store.persistence.domain.Vendor;
import app.store.persistence.repository.VendorRepository;
import app.store.service.dto.DescriptionDto;
import app.store.service.dto.VendorDto;
import app.store.service.mapper.VendorMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class VendorService {
    private final Logger log = LoggerFactory.getLogger(VendorService.class);


    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorService(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    public Optional<String> createVendor(VendorDto vendorDto) {
        return Optional.of(vendorMapper.toEntity(vendorDto))
                .map(vendor -> {
                    Vendor result = vendorRepository.save(vendor);
                    log.debug("Save Information for Vendor: {}", vendorDto);
                    return result.getId().toString();
                });
    }

    public Optional<VendorDto> getVendor(String id) {
        log.debug("Get vendor by id: {} ", id);
        return Optional.of(vendorRepository.findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(vendorMapper::toDto);
    }

    public Optional<VendorDto> updateVendor(VendorDto vendorDto, String id) {
        return Optional.of(vendorRepository.findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(vendor -> {
                    vendor.setName(vendorDto.getName());
                    vendor.setDescription(getDescription(vendorDto.getDescription()));
                    vendor.setCover(vendorDto.getCover());

                    Vendor result = vendorRepository.save(vendor);
                    log.debug("Changed Information for Vendor: {}", result);
                    return result;
                }).map(vendorMapper::toDto);
    }

    public void deleteVendor(String id) {
        vendorRepository.findOneById(new ObjectId(id))
                .ifPresent(vendor -> {

                    vendorRepository.delete(vendor);
                    log.debug("Deleted Vendor: {}", vendor);
                });
    }


    private Description getDescription(DescriptionDto DescriptionDto) {
        Description description = new Description();
        description.setName(DescriptionDto.getName());
        description.setLanguage(DescriptionDto.getLanguage());
        description.setShortDescribe(DescriptionDto.getShortDescribe());
        description.setLongDescribe(DescriptionDto.getLongDescribe());
        return description;
    }

    public Optional<List<VendorDto>> isExistByName(String name) {
        CompletableFuture<List<Vendor>> allByName = vendorRepository.findAllByName(name);
        try {
            List<Vendor> vendors = allByName.get();
            return Optional.of(vendorMapper.toDto(vendors));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean isExistById(String id) {
        return vendorRepository.existsById(new ObjectId(id));
    }

    public Page<VendorDto> getAllVendor(Pageable pageable) {
        return vendorRepository.findAll(pageable)
                .map(vendorMapper::toDto);
    }
}
