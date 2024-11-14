package eu.senla.financialtransactions.mapper;

import eu.senla.financialtransactions.dto.PaymentRequestDto;
import eu.senla.financialtransactions.dto.PaymentRequestMessageDto;
import eu.senla.financialtransactions.dto.UuidDto;
import eu.senla.financialtransactions.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static eu.senla.financialtransactions.constant.AppConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentMapper {

    UuidDto toUuidDto(Payment payment);

    @Mapping(source = "clientId", target = "client.id")
    Payment toPayment(PaymentRequestDto paymentRequestDto);

    @Mapping(source = "client.id", target = "clientId")
    PaymentRequestMessageDto toPaymentMessageRequestDto(Payment payment);
}
