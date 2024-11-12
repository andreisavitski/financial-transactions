package eu.senla.financialtransactions.scheduler;

import eu.senla.financialtransactions.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static eu.senla.financialtransactions.constant.AppConstants.DELETION_INTERVAL_FOR_UNCOMPLETED_TRANSFERS;
import static eu.senla.financialtransactions.enums.Status.IN_PROGRESS;

@Component
@RequiredArgsConstructor
public class UnsuccessfulTransferRemover {

    private final TransferRepository transferRepository;

    @Transactional
    @Scheduled(cron = DELETION_INTERVAL_FOR_UNCOMPLETED_TRANSFERS)
    public void deleteOldUncompletedTransfer() {
        final LocalDateTime thresholdDate = LocalDateTime.now().minusMinutes(1);
        transferRepository.deleteByStartDateTimeBeforeAndStatus(
                thresholdDate,
                IN_PROGRESS);
    }
}
