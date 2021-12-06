# Capstone project: messaging with RabbitMQ

## Goal:

Getting practical skills in setting up an HA RabbitMQ cluster using Docker and practice in integration with RabbitMQ
from a Spring Boot application.

## Requirements

- Get familiar with clustering in RabbitMQ
- Set up a local dockerized RabbitMQ cluster of 3 nodes
- Create a durable eagerly synchronized mirrored queue with a direct exchange and a route between them
- Develop a Spring Boot application for publishing messages to the exchange at the given rate (i.e. 10 per second)
- Develop a Spring Boot application for listening and logging received messages from the queue.
- Run publishing and listening applications and use RabbitMQ UI to dynamically observe running cluster.
- Shutdown the first RabbitMQ node and make sure the flow keeps working
- Restart the first RabbitMQ node and make sure it joined the cluster

## How to run:

1. Run docker compose
2. Enable federation plugin

``` 
    docker exec -it rabbit-1 rabbitmq-plugins enable rabbitmq_federation
    docker exec -it rabbit-2 rabbitmq-plugins enable rabbitmq_federation
    docker exec -it rabbit-3 rabbitmq-plugins enable rabbitmq_federation
```

3. Set queue mirroring policy- exec in rabbit-1 container

```
    docker exec -it rabbit-1 bash
    
    rabbitmqctl set_policy ha-fed \
        ".*" '{"federation-upstream-set":"all", "ha-sync-mode":"automatic", "ha-mode":"all"
    }' \
    --priority 1 \
    --apply-to queues
    
    exit
```

4. Run consumer application, where the queue and binding is declared
5. Run producer application
6. Enjoy your HA cluster

## Used technologies:

Docker RabbitMQ Spring Boot Java