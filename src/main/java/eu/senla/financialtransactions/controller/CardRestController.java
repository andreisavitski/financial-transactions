package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.ClientCardRequestDto;
import eu.senla.financialtransactions.dto.MessageResponseDto;
import eu.senla.financialtransactions.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class CardRestController {

    private final CardService cardService;

    @GetMapping("/get")
    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForGetClientCard)")
    public MessageResponseDto getCardByClientId(
            @RequestBody ClientCardRequestDto clientCardRequestDto) {
        return cardService.getClientCard(clientCardRequestDto.getId());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority(@permissionProvider.getPermissionForAddCard)")
    public MessageResponseDto addCard(
            @RequestBody ClientCardRequestDto clientCardRequestDto) {
        return cardService.addCard(clientCardRequestDto);
    }
}
