package com.arthurcoelho.agendadortarefas.business;

import com.arthurcoelho.agendadortarefas.business.dto.TarefasDTO;
import com.arthurcoelho.agendadortarefas.business.mapper.TarefasConverter;
import com.arthurcoelho.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.arthurcoelho.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.arthurcoelho.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.arthurcoelho.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);

        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                             LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO>buscarTarefasPorEmailUsuario(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }
}
