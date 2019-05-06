package app.store.service;

import app.store.persistence.domain.Invoice;
import app.store.persistence.domain.enums.CartStatus;
import app.store.persistence.domain.enums.InvoiceStatus;
import app.store.persistence.repository.CartRepository;
import app.store.persistence.repository.InvoiceRepository;
import app.store.service.mapper.InvoiceMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class InvoiceService {
    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CartRepository cartRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CartRepository cartRepository) {

        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.cartRepository = cartRepository;
    }


    public Optional<Invoice> createInvoice(ObjectId userId, ObjectId orderId) {
        cartRepository.findOneByUserIdAndStatus(userId, CartStatus.COMPLETE).map(cart -> {

            return null;
        }).orElseThrow(null);


        Invoice invoice = new Invoice();
        invoice.setStatus(InvoiceStatus.ENTERED);
        invoice.setUserId(userId);
        invoice.setPersistedTime(Instant.now());

        Invoice save = invoiceRepository.save(invoice);
        log.debug("Invoice has been created by: {} and the invoice: {}", userId.toString(), save.getId());
        return Optional.of(save);
    }

    public Optional<Invoice> updateInvoiceAfterPayment(ObjectId userId, ObjectId paymentId, Instant checkout) {
//        invoiceRepository.findAllByUserIdAndStatus(userId, InvoiceStatus.ENTERED);


//        InvoiceStatus.CHECKOUT;
        return null;
    }


//    public Page<InvoiceDto> getInvoiceEntered() {
//        return invoiceRepository.findAllByStatus(InvoiceStatus.ENTERED)
//                .map(invoiceMapper::toDto);
//    }

//    public Page<InvoiceDto> getInvoiceApprovedWithUserId(String userId) {
//        return invoiceRepository.findAllByUserIdAndStatus(new ObjectId(userId), InvoiceStatus.ENTERED)
//                .map(invoiceMapper::toDto);
//    }
}
