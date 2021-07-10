package com.poketrader.api.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poketrader.api.application.PokemonAppService;
import com.poketrader.api.domain.PokemonRepository;
import com.poketrader.api.infrastructure.exception.PokemonNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PokemonAppService pokemonAppService;

    @MockBean
    PokemonRepository pokemonRepository;

    @Test
    void whenPokemonNameIsValid_thenReturn200() throws Exception {
        //setup
        var pokemonName = "ditto";
        PokemonResource expectedResponseBody =
                new PokemonResource(132L, pokemonName, 101);
        given(pokemonAppService
                .createNewPokemon(pokemonName))
                .willReturn(expectedResponseBody);

        //execute
        MvcResult mvcResult = mockMvc.perform(post("/pokemon")
                .contentType("application/json")
                .param("name", pokemonName))
                .andExpect(status().isOk()).andReturn();

        //verify
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedResponseBody));
    }

    @Test
    void whenPokemonNameIsEmpty_thenReturnBadRequest() throws Exception {
        //setup
        var pokemonName = "";

        //execute + verify
        mockMvc.perform(post("/pokemon")
                .contentType("application/json")
                .param("name", pokemonName))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void whenGivenPokemonNameDoNotExist_thenThrowNotFound() throws Exception {
        //setup
        var dontExist = "IDontExist";
        given(pokemonAppService.createNewPokemon(dontExist)).willThrow(new PokemonNotFoundException());
        given(pokemonRepository.findByName(dontExist)).willReturn(null);

        // execute + verify
        mockMvc.perform(post("/pokemon")
                .contentType("application/json")
                .param("name", dontExist))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof PokemonNotFoundException));
    }

}