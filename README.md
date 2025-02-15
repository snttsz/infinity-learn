# Demo login page

https://snttsz.github.io/login-page/

# Relatório da criação do projeto:

## Como inicializar o projeto:
Back-end: <br>
`mvn clean install` <br>
`mvn spring-boot:run` <br><br>

Front-end: <br>
`ng build` <br>
`ng s` <br>

Para acessar a página inicial:

`http://localhost:4200/`

Atualmente, a porta especificada para rodar o servidor (back-end) é 8081, porém isso pode ser modificado no 
arquivo "src/main/resources/application.properties" com o comando server.port. 

## Instalação dos pacotes:
No pom.xml, na tag dependencies: <br>
    - Adicionada a dependência javax.servlet <br>
    - Adicionada a dependência spring

## Arquitetura das pastas do projeto:

Front-end: <br>
src/app/ <br>
├─ classes/ <br>
├─ components/ <br>
├─ enums/ <br>
└─ pages/ <br>

<br>
- "src/app/classes":
<br>
- "src/app/components":
<br>
- "src/app/enums":
<br>
- "src/app/pages": 
<br><br>

Back-end: <br>
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
