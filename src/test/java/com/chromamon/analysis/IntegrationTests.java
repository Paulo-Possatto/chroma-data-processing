package com.chromamon.analysis;

import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Test
    @Transactional
    public void checkResponseForOkRequest() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(post("/transformer/add-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MockObject.transformerDataMockModel().toString()))
                        .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Transformer TR-001 Successfully added!"))
                .andExpect(jsonPath("$.data.transformerId").value("TR-001"))
                .andExpect(jsonPath("$.data.transformerName").value("Main Transformer"))
                .andExpect(jsonPath("$.data.location").value("Substation A"))
                .andExpect(jsonPath("$.data.installationDate").value("2021-08-14 00:00:00"))
                .andExpect(jsonPath("$.data.status").value("OPERATING"))
                .andExpect(jsonPath("$.data.nominalPower").value("150 kVA"))
                .andExpect(jsonPath("$.data.primaryVoltage").value("15 kV"))
                .andExpect(jsonPath("$.data.secondaryVoltage").value("3 kV"))
                .andExpect(jsonPath("$.data.phase").value("THREE_PHASE"))
                .andExpect(jsonPath("$.data.configuration").value("DELTA_DELTA"))
                .andExpect(jsonPath("$.data.nominalCurrent").value(16.5))
                .andExpect(jsonPath("$.data.transformerType").value("STEP_DOWN"))
                .andExpect(jsonPath("$.data.transformerImpedance").value(1.2))
                .andExpect(jsonPath("$.data.transformerFrequency").value(50))
                .andExpect(jsonPath("$.data.transformerManufacturer").value("General Electric"))
                .andExpect(jsonPath("$.data.serialNumber").value("135498451"))
                .andExpect(jsonPath("$.data.lastMaintenanceDate").value("2022-11-05 00:00:00"))
                .andExpect(jsonPath("$.data.maxOperatingTemperature").value(230))
                .andExpect(jsonPath("$.data.refrigerationType").value("OIL_NATURAL_AIR_FORCED"))
                .andExpect(jsonPath("$.data.isolationType").value("VEGETABLE_OIL"))
                .andExpect(jsonPath("$.data.actualLoad").value(430));
    }

    @Test
    public void checkResponseWhenTransformerIdIsEmpty() throws Exception{

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        JSONObject request = MockObject.transformerDataMockModel();
        request.put("transformerId", "");
        mockMvc.perform(post("/transformer/add-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.transformerId").value("The transformer identification cannot be empty"));
    }
}
