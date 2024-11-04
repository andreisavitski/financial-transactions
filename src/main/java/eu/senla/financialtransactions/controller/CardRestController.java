package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.dto.MessageResponseDtoTest;
import eu.senla.financialtransactions.service.CardService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForGetClientCard)")
    public MessageResponseDtoTest getCardByClientId(
            @RequestBody @NotNull ClientCardRequestDto clientCardRequestDto) {
        return cardService.getClientCardTest(clientCardRequestDto.getId());
    }
}
