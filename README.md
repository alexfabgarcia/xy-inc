# xy-inc
Projeto de API Rest que provê serviços para localização de pontos de interesse (POIs), embasado no framework [Spring Boot](https://projects.spring.io/spring-boot/).

Para persistência, foi utilizada a versão embarcada do banco de dados [MongoDB](https://www.mongodb.com/), simplificando a inicialização e testes do serviço.

## Pré-Requisitos
[JDK 8](http://lmgtfy.com/?q=install+jdk+8) instalado.

## Compilação
O projeto contém a versão wrapped do [Maven](https://maven.apache.org/) que pode ser utilizada para compilação, através do seguinte comando:
```
.\mvnw clean install
```

## Configuração

A porta para execução do serviço pode ser alteradano arquivo `src/main/resources/application.properties`.
```
server.port=8085
```

## Execução
A partir da raíz do projeto, execute:
```
.\mvnw spring-boot:run
```
ou, ainda, acesse a pasta `target` após a compilação do projeto e execute:
```
java -jar .\target\xyinc-0.0.1-SNAPSHOT.jar
```

## Testes
Durante o processo de compilação, os testes unitários e de integração já serão executados. Porém, caso deseje executá-los separadamente, utilize o seguinte comando:
```
.\mvnw clean test
```

## Consumo dos serviços
Qualquer cliente HTTP pode ser utilizado para o consumo dos serviços. A seguir, serão listados exemplos utilizado o utilitário `curl`.

#### Criação de um POI
Realiza o cadastro de um ponto de interesse a partir de 3 atributos: Nome (name), coordenadas x e y:
```
curl -X POST -H "Content-Type: application/json" -d '{"name":"Estadio de Futebol","x":10,"y":20}' http://localhost:8085/pois
```

#### Listagem de POIs
Realiza a listagem de todos os POIs cadastros:
```
curl http://localhost:8085/pois
```

#### Listagem de POIs aproximados
Realiza a listagem de POIs aproximados utilizando um ponto de referência (x, y) e uma distância, aqui chamada de raio (radius):
```
curl http://localhost:8085/pois?x=20&y=10&radius=10
```