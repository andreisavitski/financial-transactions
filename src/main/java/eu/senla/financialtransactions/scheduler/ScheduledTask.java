package eu.senla.financialtransactions.scheduler;

import eu.senla.financialtransactions.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static eu.senla.financialtransactions.enums.TransferStatus.IN_PROGRESS;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final TransferRepository transferRepository;

    @Transactional
    @Scheduled(cron = "* */5 * * * *")
    public void deleteOldUncompletedTransfer() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusMinutes(1);
        transferRepository.deleteByTransferStartDateTimeBeforeAndStatus(
                thresholdDate,
                IN_PROGRESS);
    }
}
