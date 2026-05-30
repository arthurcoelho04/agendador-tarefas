package com.arthurcoelho.agendadortarefas.business;

import com.arthurcoelho.agendadortarefas.business.dto.TarefasDTO;
import com.arthurcoelho.agendadortarefas.business.mapper.TarefasConverter;
import com.arthurcoelho.agendadortarefas.business.mapper.TarefasUpdateConverter;
import com.arthurcoelho.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.arthurcoelho.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.arthurcoelho.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.arthurcoelho.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.arthurcoelho.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;


    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now(ZoneId.systemDefault()));
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);

        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                             LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal,
                        StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefasDTO> buscarTarefasPorEmailUsuario(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String idTarefa) {
        try {
            tarefasRepository.deleteById(idTarefa);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa, id nao existe " + idTarefa,
                    e.getCause());
        }
    }

    public TarefasDTO alterarStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("tarefa nao encontrada" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status" + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("tarefa nao encontrada" +id));
            tarefasUpdateConverter.updateTrefas(dto, entity);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status" + e.getCause());
        }
    }
}
