# Microsserviço de Agendamento de Tarefas

API REST desenvolvida em **Java e Spring Boot**, responsável pelo gerenciamento de tarefas e eventos agendados do Sistema de Agendamento de Tarefas.

## Funcionalidades

- **Gerenciamento de Tarefas:** Cadastro, consulta, atualização e exclusão.
- **Agendamento de Eventos:** Registro de tarefas com data e horário definidos.
- **Consulta por Período:** Busca de tarefas agendadas por intervalo de datas.
- **Controle de Notificações:** Gerenciamento do status das notificações.
- **Autenticação:** Validação de usuários utilizando JWT e Spring Security.
- **Integração entre Microsserviços:** Comunicação com o serviço de usuários utilizando OpenFeign.
- **Persistência de Dados:** Armazenamento das tarefas no MongoDB.

## Tecnologias Utilizadas

- **Java 21:** Linguagem de programação.
- **Spring Boot:** Desenvolvimento da API REST.
- **Spring Security e JWT:** Autenticação e segurança.
- **Spring Data MongoDB:** Persistência de dados.
- **OpenFeign:** Comunicação entre microsserviços.
- **Gradle:** Gerenciamento de dependências.
- **Docker:** Conteinerização da aplicação.

## Como Executar

1. Clone ou baixe o repositório.
2. Inicie o MongoDB na porta `27017`.
3. Configure e inicie o microsserviço de usuários.
4. Execute a aplicação com `./gradlew bootRun`.
5. A API estará disponível em `http://localhost:8081`.

## Endpoints

| Método | Rota | Funcionalidade |
|---|---|---|
| POST | `/tarefas` | Cadastrar tarefa |
| GET | `/tarefas` | Consultar tarefas do usuário |
| GET | `/tarefas/eventos` | Consultar tarefas por período |
| PUT | `/tarefas` | Atualizar tarefa |
| PATCH | `/tarefas` | Alterar status da notificação |
| DELETE | `/tarefas` | Excluir tarefa |

## Projeto Principal

Este microsserviço integra o **Sistema de Agendamento de Tarefas**, desenvolvido com arquitetura de microsserviços em Java e Spring Boot.
