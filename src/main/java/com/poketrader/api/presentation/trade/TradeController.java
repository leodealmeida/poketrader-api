package com.poketrader.api.presentation.trade;

import com.poketrader.api.application.trade.TradeAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    private final TradeAppService tradeAppService;

    public TradeController(TradeAppService tradeAppService) {
        this.tradeAppService = tradeAppService;
    }

    @PostMapping("/trade")
    public ResponseEntity<Long> newTrade(@RequestBody TradeCommand command) {
        Long tradeId = tradeAppService.createNewTrade(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(tradeId);
    }

}
