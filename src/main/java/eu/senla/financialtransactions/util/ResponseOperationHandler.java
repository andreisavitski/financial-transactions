package eu.senla.financialtransactions.util;

import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.entity.Operation;
import eu.senla.financialtransactions.entity.Payment;
import eu.senla.financialtransactions.entity.Transfer;
import eu.senla.financialtransactions.repository.PaymentRepository;
import eu.senla.financialtransactions.repository.TransferRepository;
import eu.senla.financialtransactions.service.ActionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static eu.senla.financialtransactions.constant.AppConstants.OK;
import static eu.senla.financialtransactions.util.OperationDataSetter.setDataAfterExecute;

@Component
@RequiredArgsConstructor
public class ResponseOperationHandler {

    private final ActionService actionService;

    private final TransferRepository transferRepository;

    private final PaymentRepository paymentRepository;

    @NotNull
    public MessageResponseDto saveData(@NotNull MessageResponseDto responseDto,
                                       @NotNull Operation operation) {
        if (responseDto.getStatus().equals(OK)) {
            setDataAfterExecute(operation);
            CompletableFuture<Void> saveOperation = new CompletableFuture<>();
            if (operation instanceof Transfer transfer) {
                saveOperation = CompletableFuture.runAsync(() -> transferRepository.save(transfer));
            } else if (operation instanceof Payment payment) {
                saveOperation = CompletableFuture.runAsync(() -> paymentRepository.save(payment));
            }
            final CompletableFuture<Void> saveAction = CompletableFuture.runAsync(
                    () -> actionService.save(operation));
            CompletableFuture.allOf(saveOperation, saveAction).join();
        }
        return responseDto;
    }
}
