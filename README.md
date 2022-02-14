# DESAFIO-BACK-VOTOS

>***Descrição do desafio:***
https://github.com/rh-southsystem/desafio-back-votos

>***Link para a documentação da minha demo:***
http://165.232.140.33:8080/vote.api/swagger-ui/index.html

>***Dependências**:*
>[Docker](https://www.docker.com/)
>[Docker Compose](https://docs.docker.com/compose/install/)
PS -> Para um melhor aproveitamento da revisão, recomendo que tenha o [Lombok](https://projectlombok.org/) instalado na IDE.

>***Instalação**:*
```
// dentro do diretório do projeto
$ docker-compose up 
```

![docs](https://ibb.co/HBxQxT4)


## Tecnologias Utilizadas
- CI Github Actions
- DigitalOcean Droplet
- Docker & Docker Compose
- Java & SpringBoot
- Swagger
- Lombok
- Junit e H2 para o setup de testes
- Hateoas
- Kafka
- Postgres e JPA 

## Detalhes

#### Mensageria e filas

Internamente, para a execução de tarefas assíncronas, utilizei a api **ScheduledExecutorService**, e implementei o **Kafka** para a notificação de serviços externos à aplicação através de tópicos.

#### Performance

Cache (descontinuado), execução de tarefas assíncronas e mensageria, compressão de mensagens.

#### Versionamento da API

Adotei uma combinação de duas estratégias para o versionamento. Uma minor version (pelos headers) e outra major version (pelas rotas).
