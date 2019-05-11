##### Subir um RabbitMQ usando docker:
docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3-management

##### Para acessar o console de administração do RabbitMQ:
http://[docker host IP]:15672/#/

##### Para checar se a aplicação está rodando:
http://localhost:8080/actuator/health

