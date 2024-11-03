package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.TransferExecuteResponseDto;
import eu.senla.financialtransactions.service.CardService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class CardRestController {

    private final CardService cardService;

    @PostMapping("/get")
    @NotNull
//    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForGetClientCard)")
//    @PreAuthorize("hasAuthority('viewMyCards')")
    public TransferExecuteResponseDto getCardByClientId(@RequestBody @NotNull ClientCardRequestDto clientCardRequestDto) {
        return cardService.getClientCard(clientCardRequestDto.getId());
    }
}
