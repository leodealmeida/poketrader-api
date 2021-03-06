package com.poketrader.api.presentation.trade;

import com.poketrader.api.application.trade.TradeAppService;
import com.poketrader.api.domain.trade.TradeRepository;
import com.poketrader.api.presentation.pokemon.PokemonResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeAppService tradeAppService;

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    void whenGetAllPokemonEndpoint_thenReturnNonEmptyList() throws Exception {
        //setup
        var cmd = new TradeCommand(
                asList(new PokemonResource(999L, "pokemonA", 111)),
                asList(new PokemonResource(998L, "pokemonB", 100)));
        tradeAppService.createNewTrade(cmd);

        //execute
        mockMvc.perform(get("/trades")
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        //verify
        int size = tradeRepository.findAll().size();
        assertThat(size).isGreaterThan(0);

    }

    @Test
    void whenPostInvalidCommand_thenReturnBadRequest() throws Exception {
        //setup
        var cmd = new TradeCommand(emptyList(), emptyList());

        //execute + verify
        mockMvc.perform(post("/trade")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

}