# apix2019-microservice-notification-kotlin :iphone:

### Como usar com docker:

1 - Execute o script 'generate-image.sh', presente na pasta raiz do projeto, para gerar a imagem docker da aplicação;

2 - Entre na pasta 'docker' e edite o arquivo 'variables.sh' com as informações TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN e TWILIO_FROM_PHONE com as informações presentes em sua conta do Twilio para que o envio de mensagens seja realizado corretamente;

3- Execute o comando 'docker-start.sh' para iniciar o container da aplicação;

OBS: Caso tenha feito alguma alteração de código, gere novamente a imagem docker usando o comando 'generate-image.sh' e depois use o comando 'update-notification.sh', na pasta 'docker', para subir um novo container do docker. 

### Como usar localmente:

1 - Edite o arquivo 'variables.sh' presente dentro da pasta 'docker' com os valores das variáveis TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN e TWILIO_FROM_PHONE com as informações presentes em sua conta do Twilio para que o envio de mensagens seja realizado corretamente;

2 - Edite também o valor da variável RABBITMQ_HOST para 'localhost';

3 - Dentro da pasta do projeto, no terminal, execute o comando `source variables.sh` para carregar as variáveis de ambiente na máquina;

4 - Na pasta raiz do projeto, execute o comando `mvn clean install` para gerar o jar do projeto;

5 - Após a carga das variáveis, execute o comando 'java -jar ./target/apix2019-microservice-notification-kotlin-0.0.1-SNAPSHOT.jar' para iniciar a aplicação;

OBS: Caso haja problemas com porta já alocada, entre no arquivo 'application.yml' dentro da pasta 'resources' dentro do projeto e coloque o valor: 
`server:
    port: numero_da_porta`

### Como realizar debug da aplicação:

1 - Realizar os passos da seção "Como usar localmente" exceto o item 5;

2 - No IntelliJ, aperte as teclas 'Shift+Alt+F10' e depois selecione a opção 0 para abrir uma nova tela;

3 - Dentro da tela de edição de configurações de Debug (Run/Debug Configurations), click no "+" e selecione a opção Remote;

4 - Insira um nome qualquer no campo 'Name', deixe o campo 'Host' como localhost e a porta de Debug como 8000 (ou a que você preferir);

5 - No terminal, dentro da pasta raiz do projeto, execute o comando:
`java -jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n ./target/apix2019-microservice-notification-kotlin-0.0.1-SNAPSHOT.jar`. Lembre-se de colocar o número correto da porta de debug no atributo 'address' deste comando de acordo com o configurado na IDE;

##### Para acessar o console de administração do RabbitMQ:
http://[docker host IP]:15672/#/

##### Para checar se a aplicação está rodando:
http://localhost:8080/actuator/health

