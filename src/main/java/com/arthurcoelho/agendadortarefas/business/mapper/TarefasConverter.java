package com.arthurcoelho.agendadortarefas.business.mapper;

import com.arthurcoelho.agendadortarefas.business.dto.TarefasDTO;
import com.arthurcoelho.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDTO(TarefasEntity entity);

}
