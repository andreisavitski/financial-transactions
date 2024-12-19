package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentMapper {

    @Mapping(source = "id", target = "id")
    UuidDto toUuidDto(Payment payment);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "startDateTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endDateTime", ignore = true)
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "operatorId", target = "operatorId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "writeOffCardId", target = "writeOffCardId")
    Payment toPayment(PaymentRequestDto paymentRequestDto);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "writeOffCardId", target = "writeOffCardId")
    @Mapping(source = "amount", target = "amount")
    PaymentRequestMessageDto toPaymentMessageRequestDto(Payment payment);
}
