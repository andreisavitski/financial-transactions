package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    UuidDto toUuidDto(Payment payment);

    @Mapping(source = "clientId", target = "client.id")
    Payment toPayment(PaymentRequestDto paymentRequestDto);

    PaymentRequestDto toPaymentRequestDto(Payment payment);
}
