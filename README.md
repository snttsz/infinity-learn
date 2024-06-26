# Relatório da criação do projeto:

## Como inicializar o projeto:
`mvn clean install` <br>
`mvn spring-boot:run`

Para acessar a página inicial:

`http://localhost:8081/`

Atualmente, a porta especificada para rodar o servidor é 8081, porém isso pode ser modificado no 
arquivo "src/main/resources/application.properties" com o comando server.port. 

## Instalação dos pacotes:
No pom.xml, na tag dependencies: <br>
    - Adicionada a dependência javax.servlet <br>
    - Adicionada a dependência spring

## Arquitetura das pastas do projeto:

src/ <br>
├── main/ <br>
│ ├── java/ <br>
│ │ ├── spring/ <br>
│ │ |  ├── controller/ <br>
│ │ └── model/ <br>
│ ├── resources/ <br>
│ │ ├── static/ <br>
│ │ |  ├── js/ <br>
│ │ |  ├── css/ <br>
│ │ |  └── img/ <br>
│ │ └── templates/ <br>

<br>
- "src/main/java/spring/controller":
<br>
- "src/main/java/model":
<br>
- "src/main/resources/static/": 

<br><br>
**Alunos:**<br>
TODO: marcar github/nome dos alunos