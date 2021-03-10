## `UserVoice-Server` 

### API Documentation
#### Users
`Register a user:`

url : localhost:8080/register
requestedJson:
```json
{
    "username":"testName",
    "password":"1234",
    "email":"test@test.com"
}
```
Response : 
```json
{
    "id": 1,
    "content": "{\"dbId\":0,\"username\":\"testName\",\"password\":\"1234\",\"email\":\"test\"}",
    "sucess": true
}
```

`Login:`

url : localhost:8080/login
requestedJson:
```json
{
    "email":"test@test.com",
    "password":"1234"
}
```
Response : 
```json
{
    "id": 1,
    "content": "{\"dbId\":1,\"username\":\"testName\",\"email\":\"test@test.com\"}"
}
```

#### Features
`Get a Match of two randow features:`

url : localhost:8080/getMatch
requestedJson:
```json
{
  "apiKey":"38e6d609-0028-4924-b879-fdf88c23c3f6"
}
```
Response : 
```json
{
    "feature1": {
        "id": 2,
        "textFeature": "Mettre un dark mode",
        "authorEmail": "tom.kubasik@gmail.com",
        "mmr": 0,
        "elo": 0
    },
    "feature2": {
        "id": 1,
        "textFeature": "Mettre un light mode",
        "authorEmail": "tom.kubasik@gmail.com",
        "mmr": 0,
        "elo": 0
    }
}
```




