package com.techchallenge4.ms_cliente.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchallenge4.ms_cliente.controller.dto.response.ClienteResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.*;
import static com.techchallenge4.ms_cliente.mock.ClienteDados.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class ClienteStepsDefinition {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private MvcResult resultado;
    private String jsonRequest;
    private ClienteResponse clienteResponse;

    @Dado("que eu tenha os dados válidos do cliente")
    public void que_eu_tenha_os_dados_válidos_do_cliente() throws Exception {
        var clienteRequest = getClienteRequest();
        jsonRequest = objectMapper.writeValueAsString(clienteRequest);
    }

    @Quando("eu envio uma requisição POST para {string}")
    public void eu_envio_uma_requisição_post_para(String url) throws Exception {
        resultado = mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Entao("o cliente deve ser criado com sucesso")
    public void o_cliente_deve_ser_criado_com_sucesso() throws Exception {
        var jsonResponse = resultado.getResponse().getContentAsString();
        clienteResponse = objectMapper.readValue(jsonResponse, ClienteResponse.class);

        assertNotNull(clienteResponse);
    }

    @Entao("a resposta deve conter os dados do cliente criado")
    public void a_resposta_deve_conter_os_dados_do_cliente_criado() {
        assertEquals(getClienteResponse(), clienteResponse);
    }

    @Entao("o status de resposta deve ser {int}")
    public void o_status_de_resposta_deve_ser(Integer status) {
        assertEquals(status, resultado.getResponse().getStatus());
    }

    @Dado("que eu tenha os dados a serem atualizados de um cliente existente")
    public void que_eu_tenha_os_dados_a_serem_atualizados_de_um_cliente_existente() throws Exception {
        var clienteRequest = getClienteRequest();
        clienteRequest.setNome("Nome Atualizado");
        clienteRequest.setEmail("email.atualizado@email.com");

        jsonRequest = objectMapper.writeValueAsString(clienteRequest);
    }

    @Quando("eu envio uma requisição PUT para {string}")
    public void eu_envio_uma_requisição_put_para(String url) throws Exception {
        resultado = mockMvc.perform(MockMvcRequestBuilders
                        .put(url, ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Entao("o cliente deve ser atualizado com sucesso")
    public void o_cliente_deve_ser_atualizado_com_sucesso() throws Exception {
        var jsonResponse = resultado.getResponse().getContentAsString();
        clienteResponse = objectMapper.readValue(jsonResponse, ClienteResponse.class);

        assertNotNull(clienteResponse);
        assertEquals(ID, clienteResponse.getId());
    }

    @Entao("a resposta deve conter os dados atualizados do cliente")
    public void a_resposta_deve_conter_os_dados_atualizados_do_cliente() {
        assertEquals("Nome Atualizado", clienteResponse.getNome());
        assertEquals("email.atualizado@email.com", clienteResponse.getEmail());
    }

    @Dado("que eu tenha um ID de um cliente existente")
    public void que_eu_tenha_um_id_de_um_cliente_existente() {
        assertNotNull(ID);
    }

    @Quando("eu envio uma requisição GET para {string}")
    public void eu_envio_uma_requisição_get_para(String url) throws Exception {
        resultado = mockMvc.perform(MockMvcRequestBuilders
                        .get(url, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Entao("a resposta deve contar os dados atualizados do cliente")
    public void a_resposta_deve_contar_os_dados_atualizados_do_cliente() throws Exception {
        var jsonResponse = resultado.getResponse().getContentAsString();
        clienteResponse = objectMapper.readValue(jsonResponse, ClienteResponse.class);

        assertNotNull(clienteResponse);
        assertEquals(ID, clienteResponse.getId());
    }

    @Dado("que existam clientes cadastrados e ativos no sistema")
    public void que_existam_clientes_cadastrados_e_ativos_no_sistema() {
        // Suponha que os clientes já estão cadastrados e ativos no sistema.
    }

    @Quando("eu envio uma requisição GET para {string} sem parâmetros")
    public void eu_envio_uma_requisição_get_para_sem_parametros(String url) throws Exception {
        resultado = mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Entao("uma lista de clientes deve ser retornada")
    public void uma_lista_de_clientes_deve_ser_retorno() throws Exception {
        var jsonResponse = resultado.getResponse().getContentAsString();
        var clienteList = objectMapper.readValue(jsonResponse, ClienteResponse[].class);

        assertNotNull(clienteList);
        assertTrue(clienteList.length > 0);
    }

    @Quando("eu envio uma requisição DELETE para {string}")
    public void eu_envio_uma_requisição_delete_para(String url) throws Exception {
        resultado = mockMvc.perform(MockMvcRequestBuilders
                        .delete(url, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Entao("o cliente deve ser desativado com sucesso")
    public void o_cliente_deve_ser_desativado_com_sucesso() {
        assertEquals(204, resultado.getResponse().getStatus());
    }

    @Dado("que eu tenha o ID de um cliente existente")
    public void que_eu_tenha_o_id_de_um_cliente_existente() {
        // Suponha que o ID seja obtido de um cliente previamente criado
        assertNotNull(ID);
    }

    @Entao("o status da resposta deve ser {int}")
    public void o_status_da_resposta_deve_ser(Integer statusEsperado) {
        assertEquals(statusEsperado, resultado.getResponse().getStatus());
    }

}
