package eu.senla.financialtransactions.controller;

import eu.senla.financialtransactions.dto.Card;
import eu.senla.financialtransactions.dto.ClientCardRequest;
import eu.senla.financialtransactions.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class CardRestController {

    private final CardService cardService;

    @PostMapping("/get")
//    @PreAuthorize("hasAuthority(@permissionProvider.getPermissionForGetClientCard)")
//    @PreAuthorize("hasAuthority('viewMyCards')")
    public List<Card> getCardByClientId(@RequestBody ClientCardRequest clientCardRequest) {
        return cardService.getClientCard(clientCardRequest.getId());
    }
}
