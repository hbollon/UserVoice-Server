## `UserVoice-Server` 

### API Documentation
#### Users
`Register a user:`

url : localhost:8080/user/register

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

url : localhost:8080/user/login

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

`Add a feature:`

url : localhost:8080/addFeature

requestedJson:
```json
{
    "apiKey":"38e6d609-0028-4924-b879-fdf88c23c3f6",
    "feature":{
        "textFeature":"Changer la police",
        "authorEmail":"tom.kubasik@gmail.com"
    }
}
```
Response : 
```json
{
    "id": 1,
    "content": "{\"id\":0,\"textFeature\":\"Changer la police\",\"ELO\":0,\"MMR\":0,\"authorEmail\":\"tom.kubasik@gmail.com\"}",
    "sucess": true
}
```

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

`Get all proposed features of the same author on a site:`

url : localhost:8080/getFeatureByAuthor

requestedJson:
```json
{
    "email":"tom.kubasik@gmail.com",
    "tableName":"testfeatures"   
}
```
Response : 
```json
{
    "features": [
        {
            "id": 1,
            "textFeature": "Mettre un light mode",
            "authorEmail": "tom.kubasik@gmail.com",
            "elo": 0,
            "mmr": 0
        },
        {
            "id": 2,
            "textFeature": "Mettre un dark mode",
            "authorEmail": "tom.kubasik@gmail.com",
            "elo": 0,
            "mmr": 0
        }
    ]
}
```


`Get all proposed features on a site:`

url : localhost:8080/getFeatureByTable

requestedJson:
```json
{
    "tableName":"testfeatures"   
}
```
Response : 
```json
{
    "features": [
        {
            "id": 1,
            "textFeature": "Mettre un light mode",
            "authorEmail": "tom.kubasik@gmail.com",
            "mmr": 0,
            "elo": 0
        },
        {
            "id": 2,
            "textFeature": "Mettre un dark mode",
            "authorEmail": "tom.kubasik@gmail.com",
            "mmr": 0,
            "elo": 0
        }
    ]
}
```

#### Admin

`Login:`

url : localhost:8080/admin/login

requestedJson:
```json
{
    "email":"test@gmail.com",
    "password":"1234"
}
```
Response : 
```json
{
    "id": 1,
    "content": "{\"dbId\":1,\"company\":\"test\",\"email\":\"test@gmail.com\",\"apiKey\":\"38e6d609-0028-4924-b879-fdf88c23c3f6\",\"tableFeatures\":\"testfeatures\"}"
}
```

`Register:`

url : localhost:8080/admin/register

requestedJson:
```json
{
    "email":"testCompany@gmail.com",
    "company":"company",
    "password":"1234"
}
```
Response : 
```json
{
    "id": 1,
    "content": {
        "dbId":0,
        "company":"company",
        "email":"testCompany@gmail.com",
        "apiKey":"6774cbd4-fb05-43cf-bf61-238c8661b1de",
        "tableFeatures":"companyfeatures"
    },
    "sucess": true
}
```





