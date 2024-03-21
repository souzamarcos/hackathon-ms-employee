# Hackathon - ms-employee
Serviço para cadastros dos funcionários

# Repositórios relacionados
* [Serviço de funcionários](https://github.com/souzamarcos/hackathon-ms-employee)
* [Serviço de entrada de ponto](https://github.com/souzamarcos/hackathon-ms-working-hours-entry)
* [Serviço de registro de ponto](https://github.com/souzamarcos/hackathon-ms-working-hours-registry)
* [Serviço de relatório](https://github.com/souzamarcos/hackathon-ms-report)
* [Infraestrutura Terraform](https://github.com/souzamarcos/hackathon-terraform)
* [Configuração do Kubernetes](https://github.com/souzamarcos/hackathon-kubernetes)

## Dependências
* [IntelliJ IDEA (Opcional)](https://www.jetbrains.com/idea/download/#section=windows)
* [Java JDK 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
* [Spring Boot 3.1.0](https://spring.io/projects/spring-boot)
* [Gradle 7.6.1](https://gradle.org/)
* [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)

## Endpoints

Para visualizar os endpoints disponíveis na aplicação basta acessar o swagger em [http://localhost:8080/swagger](http://localhost:8080/swagger)


## Desenvolvimento

Para executar a aplicação localmente sem depender de recursos externos à máquina a aplicação deve rodar com a variável de ambiente `SPRING_PROFILES_ACTIVE` com valor **diferente** de `production` ou **vazia**.


### Executando somente dependências

Para executar somente dependências externas (Mysql, RabbitMQ, etc) da aplicação para o ambiente de desevolvimento local basta executar o comando abaixo:

```bash
docker-compose -f docker-compose-without-application.yml up --build
```

A aplicação será exposta na porta 8080.

### Localstack
A aplicação está com o dynamodb configurado no localstack para simular a AWS localmente.
Os recursos estão sendo criados ao iniciar a imagem do localstack através do arquivo [init-aws.sh](config/localstack/init-aws.sh).

Para listar recursos do localstack use o comando `aws` com o parâmetro `--endpoint-url=http://localhost:4566`, como no exemplo abaixo:

```bash
aws --endpoint-url=http://localhost:4566 dynamodb list-tables
```

### Versionamento de libs gradle

Estamos usando um arquivo [.toml para versionar](https://docs.gradle.org/current/userguide/platforms.html#sub::toml-dependencies-format) as libs utilizadas pelo gradle. Para saber mais acesse o arquivo [libs.versions.toml](gradle/libs.versions.toml).


### Executando aplicação completa com docker

Execute o comando abaixo para iniciar os containers com a base de dados e executar a aplicação localmente.

```bash
docker-compose up --build
```
