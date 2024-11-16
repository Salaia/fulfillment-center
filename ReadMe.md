## Инструкция по развёртыванию ▶️
1) Склонируйте репозиторий: https://github.com/Salaia/fulfillment-center.git
2) Откройте программу Docker
3) В терминале или командной строке перейдите в папку проекта (где лежит файл docker-compose.yml) и выполните команду: docker-compose up
4) В программе Docker должны появиться 2 контейнера
5) Программа доступна по ниже описанному API по адресу: http://localhost:8081

## API

Во время работы проекта API доступно по ссылке: http://localhost:8081/swagger-ui/index.html

* GET /products - Get a list of all products
* POST /products - Add a new product
* PATCH /products - Update product information
* DELETE /products/{productId} - Delete product by productId
* GET /products/{status} - Get a list of products with given status
* GET /products/sellable - Get total value of all sellable products
* GET /products/totalValue/{fulfillmentCenterId} - Get total value of products placed in particular Fulfillment Center by Id

## Предложения по улучшению
* По ТЗ у нас 1 таблица, но хорошее ли это решение? По правилам нормализации БД Fulfillment Center должен быть отдельной сущностью (отдельной таблицей в базе), на которую будет прописана связь через foreign key. Таким образом, если у центра выполнения поменяется код, то его нужно будет поменять только в своей таблице один раз, а не в каждой строке таблицы Product с соответствующим центром
