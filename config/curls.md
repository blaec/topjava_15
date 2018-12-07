- _`POST & PUT are not working`_

AdminRestController
===================
### /rest/admin/users
#### GET getAll
```
curl http://localhost:8080/topjava/rest/admin/users

curl --request GET --url http://localhost:8080/topjava/rest/admin/users
```
#### POST createWithLocation
```
curl -d "name=New2&email=new2@yandex.ru&password=passwordNew&roles=[ROLE_USER]" http://localhost:8080/topjava/rest/admin/users

curl --request POST --url http://localhost:8080/topjava/rest/admin/users --header 'Content-Type:application/json' --data '{"name":"New2","email":"new2@yandex.ru","password":"passwordNew","roles":["ROLE_USER"]}'
```
### /rest/admin/users/{id}
#### GET get
```
curl http://localhost:8080/topjava/rest/admin/users/100000
```
#### DELETE delete
```
curl -X "DELETE" http://localhost:8080/topjava/rest/admin/users/100000

curl --request DELETE --url http://localhost:8080/topjava/rest/admin/users/100000
```
#### PUT update
```
curl --request PUT --url http://localhost:8080/topjava/rest/admin/users/100000 --header 'Content-Type:application/json' --data '{"name":"UserUpdated","email":"user@yandex.ru","password":"passwordNew","roles":["ROLE_USER"]}'
```
### /rest/admin/users/by
#### GET getByMail
```
curl http://localhost:8080/topjava/rest/admin/users/by?email=user@yandex.ru
```

MealRestController
===================

### /rest/meals
#### GET getAll
```
curl http://localhost:8080/topjava/rest/meals

curl --request GET --url http://localhost:8080/topjava/rest/meals
```
#### POST createWithLocation
```
curl -H "Content-Type:application/json" --request POST -d '{"dateTime":"2018-05-31T20:00:00","description":"Supper","calories":555}' http://localhost:8080/topjava/rest/meals
curl -X POST -H "Content-Type:application/json" -d '{"dateTime":"2018-05-31T20:00:00","description":"Supper","calories":555}' http://localhost:8080/topjava/rest/meals

curl --request POST --url http://localhost:8080/topjava/rest/meals --header 'Content-Type:application/json' --data ' {"dateTime":"2018-05-31T20:00:00","description":"CreatedSupper","calories":999}'
```
### /rest/meals/{id}
#### GET get
```
curl http://localhost:8080/topjava/rest/meals/100007

curl --request GET --url http://localhost:8080/topjava/rest/meals/100007
```
#### DELETE delete
```
curl -X "DELETE" http://localhost:8080/topjava/rest/meals/100007

curl --request DELETE --url http://localhost:8080/topjava/rest/meals/100007
```
#### PUT update
```
curl --request PUT --url http://localhost:8080/topjava/rest/meals/100007 --header 'Content-Type: application/json' --data '{"dateTime":"2015-05-31T20:00:00","description":"Updated Supper","calories":555}'
```
### /rest/meals/filter
#### GET getBetween
```
curl "http://localhost:8080/topjava/rest/meals/filter?startDate=2015-05-30&startTime=09:00&endDate=2015-05-31&endTime=11:00"
```