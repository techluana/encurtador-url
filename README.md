# encurtador-url

#### API Rest para encurtar url's

### Requisitos

* Java 8
* Maven
* PostgreSQL

### Configurar

Criar um database na sua instalação Postgres

Ajustar variáveis do arquivo application.properties 
<pre><code>spring.datasource.url=${ENDERECO_BD}/${NOME_BD_CRIADO}
spring.datasource.username=${USUARIO_BD}
spring.datasource.password=${SENHA_BD}</code></pre>

### Instalar

Rodar como aplicação Java a classe EncurtadorAppApplication.java

### Especificação

Link para acessar as específicações com swagger-ui:
<pre><code>http://localhost:8080/swagger-ui.html</code></pre>
