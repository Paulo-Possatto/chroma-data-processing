package com.chromamon.analysis.controller;

import com.chromamon.analysis.model.dto.TransformerDataDTO;
import com.chromamon.analysis.model.request.RequestTransformersByStatus;
import com.chromamon.analysis.model.request.RequestUpdateTransformerStatus;
import com.chromamon.analysis.model.response.ApiResponse;
import com.chromamon.analysis.service.TransformerAnalysisService;
import com.chromamon.analysis.service.TransformerDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transformer")
public class ProcessController {

    @Autowired
    private TransformerDataService transformerDataService;

    @Autowired
    private TransformerAnalysisService transformerAnalysisService;

    @PostMapping(value = "/add-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<TransformerDataDTO>> addTransformerData(@RequestBody @Valid TransformerDataDTO transformerDataDTO){
        TransformerDataDTO response = transformerDataService.addTransformerData(transformerDataDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Transformer " + response.getTransformerId() +" Successfully added!", response));
    }

    @GetMapping(value = "/find-data/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransformerDataDTO> getTransformerData(@PathVariable("id") String transformerId){
        TransformerDataDTO response = transformerDataService.getTransformerDataByIdentification(transformerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping(value = "/data/update-status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<TransformerDataDTO>> updateTransformerStatus(
            @PathVariable("id") String transformerId,
            @Valid @RequestBody RequestUpdateTransformerStatus status){
        TransformerDataDTO response = transformerDataService.updateTransformerStatus(transformerId, status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("Transformer " + transformerId + " status updated", response));
    }

    @PostMapping(value = "/data/find-by-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransformerDataDTO>> findTransformersByStatus(@Valid @RequestBody RequestTransformersByStatus status){
        List<TransformerDataDTO> responses = transformerDataService.findTransformersByStatus(status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }
}
