package app.store.service.mapper;

import app.store.persistence.domain.Bank;
import app.store.service.dto.BankDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface BankMapper extends EntityMapper<BankDto, Bank> {

    @Override
    Bank toEntity(BankDto dto);

    @Override
    BankDto toDto(Bank entity);
}
