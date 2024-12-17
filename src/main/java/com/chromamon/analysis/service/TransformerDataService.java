package com.chromamon.analysis.service;

import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.constants.TransformerIsolationTypeEnum;
import com.chromamon.analysis.constants.TransformerPhaseEnum;
import com.chromamon.analysis.constants.TransformerRefrigerationTypeEnum;
import com.chromamon.analysis.exception.*;
import com.chromamon.analysis.mapper.TransformerDataMapper;
import com.chromamon.analysis.model.dto.TransformerDataDTO;
import com.chromamon.analysis.model.entity.TransformerDataEntity;
import com.chromamon.analysis.model.request.RequestTransformersByStatus;
import com.chromamon.analysis.model.request.RequestUpdateTransformerStatus;
import com.chromamon.analysis.repository.TransformerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransformerDataService{

    @Autowired
    private TransformerDataRepository transformerDataRepository;

    public TransformerDataDTO addTransformerData(TransformerDataDTO transformerDataDTO) {
        try {
            if (transformerDataRepository.isTransformerInDatabase(transformerDataDTO.getTransformerId())) {
                throw new TransformerAlreadyExistsException("The transformer " +
                        transformerDataDTO.getTransformerId() +
                        " is already saved in the database");
            }

            if(transformerDataDTO.getPhase().equals(TransformerPhaseEnum.SINGLE_PHASE) &&
                    (transformerDataDTO.getConfiguration() != null)){
                throw new TransformerPhaseConfigurationException("Single phase transformers doesn't need configuration");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(PatternConstants.DATE_FORMAT);
            Date fabricationDateOnFormat = dateFormat.parse(transformerDataDTO.getFabricationDate());
            Date installationDateOnFormat = dateFormat.parse(transformerDataDTO.getFabricationDate());
            Date maintenanceDateOnFormat = dateFormat.parse(transformerDataDTO.getLastMaintenanceDate());

            if(installationDateOnFormat.before(fabricationDateOnFormat)){
                throw new TransformerDateNotLogicalException("The transformer installation date must not be before the transformer fabrication date");
            }
            if(maintenanceDateOnFormat.before(installationDateOnFormat)){
                throw new TransformerDateNotLogicalException("The transformer maintenance date must not be before the transformer installation date");
            }

            if(transformerDataDTO.getIsolationType().equals(TransformerIsolationTypeEnum.SOLID)){
                if(!transformerDataDTO.getRefrigerationType().equals(TransformerRefrigerationTypeEnum.AIR_FORCE) &&
                        !transformerDataDTO.getRefrigerationType().equals(TransformerRefrigerationTypeEnum.AIR_NATURAL)){
                    throw new TransformerIsolationRefrigerationException("Transformers that has Oil as an isolation type, must have Oil-related refrigeration type");
                }
            }
            if(fabricationDateOnFormat.after(new Date())){
                throw new TransformerDateNotLogicalException("The transformer fabrication date must not be after then today");
            }
            if(installationDateOnFormat.after(new Date())){
                throw new TransformerDateNotLogicalException("The transformer installation date must not be after then today");
            }
            if(maintenanceDateOnFormat.after(new Date())){
                throw new TransformerDateNotLogicalException("The transformer maintenance date must not be after then today");
            }
            return TransformerDataMapper.toDTO(
                    transformerDataRepository.save(
                            TransformerDataMapper.toEntity(transformerDataDTO
                            )
                    )
            );
        } catch (ParseException e){
            throw new RuntimeException("Error parsing date for transformer " + transformerDataDTO.getTransformerId());
        }
    }

    public TransformerDataDTO getTransformerDataByIdentification(String identification){
        Optional<TransformerDataDTO> response = transformerDataRepository.findTransformerDataByIdentification(identification)
                .map(TransformerDataMapper::toDTO);
        return response.orElseThrow(() -> new TransformerNotFoundException(
                "Transformer with identification " + identification + " was not found"
        ));
    }

    public TransformerDataDTO updateTransformerStatus(String transformerId, RequestUpdateTransformerStatus status){
        TransformerDataEntity transformer = transformerDataRepository.findTransformerDataByIdentification(transformerId)
                .orElseThrow(() -> new TransformerNotFoundException("No Transformer with identification " + transformerId + " was found"));

        transformer.setStatus(status.getStatus());
        transformer.setLastMaintenanceDate(status.getLastMaintenanceDate());

        TransformerDataEntity updatedTransformer = transformerDataRepository.save(transformer);

        return TransformerDataMapper.toDTO(updatedTransformer);
    }

    //TODO: Use Paginator
    public List<TransformerDataDTO> findTransformersByStatus(RequestTransformersByStatus status){
        List<TransformerDataDTO> results = transformerDataRepository.findTransformerByStatus(status.getStatus()).stream()
                .map(TransformerDataMapper::toDTO)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            throw new TransformerNotFoundException("No transformers found with status: " + status.getStatus());
        }

        return results;
    }
}
