package com.chromamon.analysis;

import org.json.JSONException;
import org.json.JSONObject;

public class MockObject {

    public static JSONObject transformerDataMockModel() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("transformerId", "TR-001");
        object.put("transformerName", "Main Transformer");
        object.put("location", "Substation A");
        object.put("installationDate", "2021-08-14 00:00:00");
        object.put("status", "OPERATING");
        object.put("nominalPower", "150 kVA");
        object.put("primaryVoltage", "15 kV");
        object.put("secondaryVoltage", "3 kV");
        object.put("phase", "THREE_PHASE");
        object.put("configuration", "DELTA_DELTA");
        object.put("nominalCurrent", 16.5);
        object.put("transformerType", "STEP_DOWN");
        object.put("transformerImpedance", 1.2);
        object.put("transformerFrequency", 50);
        object.put("transformerManufacturer", "General Electric");
        object.put("fabricationDate", "2002-11-03 00:00:00");
        object.put("serialNumber", "135498451");
        object.put("lastMaintenanceDate", "2022-11-05 00:00:00");
        object.put("maxOperatingTemperature", 230);
        object.put("refrigerationType", "OIL_NATURAL_AIR_FORCED");
        object.put("isolationType", "VEGETABLE_OIL");
        object.put("actualLoad", 430);
        return object;
    }
}
