##### Subir um RabbitMQ usando docker:
docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3-management

##### Para acessar o console de administração do RabbitMQ:
http://[docker host IP]:15672/#/

##### Para checar se a aplicação está rodando:
http://localhost:8080/actuator/health

#### Usar aplicação com DOCKER

1 - Editar arquivo 'variables.sh' na pasta 'docker' com os valores de accountSid, authToken e o telefone gerado pelo TWILIO

2 - Mudar o valor do atributto RABBITMQ_HOST

3 - Executar o comando 'generate-image.sh' para gerar a imagem do docker

4 - Entrar na pasta 'docker' e executar o comando 'docker-start.sh' para iniciar o container;

OBS: Caso haja mudanças no código, executar 'generate-image.sh' e depois executar o comando 'update-notification.sh'

