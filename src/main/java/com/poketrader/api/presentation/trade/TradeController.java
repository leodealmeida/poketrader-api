package com.poketrader.api.presentation.trade;

import com.poketrader.api.application.trade.TradeAppService;
import com.poketrader.api.domain.trade.Trade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TradeController {

    private final TradeAppService tradeAppService;

    public TradeController(TradeAppService tradeAppService) {
        this.tradeAppService = tradeAppService;
    }

    @PostMapping("/trade")
    public ResponseEntity<Long> newTrade(@RequestBody @Valid TradeCommand command) {
        Long tradeId = tradeAppService.createNewTrade(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(tradeId);
    }

    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> allTrades = tradeAppService.getAllTrades();
        return ResponseEntity.ok().body(allTrades);
    }

}
