package com.arthurcoelho.agendadortarefas.business.mapper;

import com.arthurcoelho.agendadortarefas.business.dto.TarefasDTO;
import com.arthurcoelho.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")

    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDTO(TarefasEntity entity);
    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dto);
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);


}
