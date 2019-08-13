# Getting Started

# Development Environment
* [Spring Boot 2.1.6 / JAVA8 / MAVEN / H2DB / JPA]

# 문제 해결전략
* 기관과 년/월별 지원금액 1:N 관계 설정
* QueryDSL GroupBy 및 JAVA8 Stream API를 통한 데이터 가공
* JWT를 이용한 Token 발행 및 API 호출시 시그니쳐 검증
* 지수평활법(Exponential Smoothing)을 활용한 금융지원 금액 예측 API 작성

# How to Build And Execution
* 빌드 및 실행
  1. mvn clean compile spring-boot:run 
  2. java -jar apidemo-0.0.1-SNAPSHOT.jar
  
# Connect H2DB console
  - URL : http://localhost/console/
  - Login
    - Driver class : org.h2.Driver
    - JDBC URL : jdbc:h2:~/apps/h2db/apidemo;AUTO_SERVER=TRUE
    - User Name : sa
    - Password : 
    
# JWT Structure
  - HEADER
     ```json
     {
         "typ": "JWT",
         "alg": "HS256"
     }
    ```
  - PayLoad
    ```json
    {
        "sub": "test111",
        "iss": "demoapi.com",
        "iat": 1565581672,
        "exp": 1566445672
    }
    ```          
  - Signiture
    <pre><code>
    HMACSHA256(
        base64UrlEncode(header) + "." +
        base64UrlEncode(payload),
        secretKey
    )
    </code></pre>
# RESTful URLs
* **[SignUP]**
   - **PUT** /auth/signup HTTP/1.1
   - URL : http://localhost/auth/signup
   - Headers
      - Content-Type : application/json;
      - Accept           : application/json;
   - Request Body
      - {"username":"test", "password":"test"}
   - 200 OK Response Body
      ```json
      {
          "isSuccess": "true",
          "message": "Signup was successful"
      }
     ```
* **[로그인]**
   - **POST** /auth/login HTTP/1.1
   - URL : http://localhost/auth/login
   - Headers
      - Content-Type : application/json;
      - Accept           : application/json;
   - Request Body
      ```json
      {"username":"test", "password":"test"}
     ```
   - 200 OK Response Body
     ```json
     {
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw",
        "tokenType": "Bearer"
     }
    ```

* **[토큰 재발행]**
   - **POST** /refreshtoken HTTP/1.1
   - URL : http://localhost/refreshtoken
   - Headers
      - Accept           : application/json;
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - 200 OK Response Body
     ```json
     {
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE3NTgsImV4cCI6MTU2NjQ0NTc1OH0.U-OPJZ0yD7rkxfYqEmX6Fo0sl7C0al7vmAcFqWGRosg",
        "tokenType": "Bearer"
     }
    ```
    
* **[csv File to H2DB]**
   - **POST** /api/save/csv HTTP/1.1
   - URL : http://localhost/api/save/csv
   - Headers
      - Content-Type : multipart/form-data
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - Form Body
     Content-Disposition: form-data; name="file"; filename="test.csv"
     Content-Type: application/vnd.ms-excel
   - 200 OK Response Body
      ```json
      {
          "isSuccess": "success",
          "message": "The file was saved successful"
      }
      ```

* **[Bank List API]**
   - **GET** /api/save/csv HTTP/1.1
   - URL : http://localhost/api/banklist
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - 200 OK Response Body
      ```json
      [
        {
        "instituteCode": 1,
        "instituteName": "주택도시기금"
        },
        {
        "instituteCode": 2,
        "instituteName": "국민은행"
        },
        {
        "instituteCode": 3,
        "instituteName": "우리은행"
        },
        {
        "instituteCode": 4,
        "instituteName": "신한은행"
        },
        {
        "instituteCode": 5,
        "instituteName": "한국시티은행"
        },
        {
        "instituteCode": 6,
        "instituteName": "하나은행"
        },
        {
        "instituteCode": 7,
        "instituteName": "농협은행/수협은행"
        },
        {
        "instituteCode": 8,
        "instituteName": "외환은행"
        },
        {
        "instituteCode": 9,
        "instituteName": "기타은행"
        }
      ]
      ```  
      
* **[API 1]**
   - **GET** /api/total/year HTTP/1.1
   - URL : http://localhost/api/total/year
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - 200 OK Response Body
      ```json
     {
         "name": "주택금융 공급현황",
         "resultList":
         [
             {
                 "detail_amount":{"하나은행": 3122, "농협은행/수협은행": 1486, "우리은행": 2303, "국민은행": 13231, "신한은행": 1815,…},
                 "year": "2005년",
                 "total_amount": 48016
             },
             {
                "detail_amount":{"하나은행": 3443, "농협은행/수협은행": 2299, "우리은행": 4134, "국민은행": 5811, "신한은행": 1198,…},
                "year": "2006년",
                "total_amount": 41210
             },
            {
            "detail_amount":{"하나은행": 2279, "농협은행/수협은행": 3515, "우리은행": 3545, "국민은행": 8260, "신한은행": 2497,…},
            "year": "2007년",
            "total_amount": 50893
            },
            {
            "detail_amount":{"하나은행": 1706, "농협은행/수협은행": 9630, "우리은행": 4290, "국민은행": 12786, "신한은행": 1701,…},
            "year": "2008년",
            "total_amount": 67603
            },
            {
            "detail_amount":{"하나은행": 1226, "농협은행/수협은행": 8775, "우리은행": 13105, "국민은행": 8682, "신한은행": 3023,…},
            "year": "2009년",
            "total_amount": 96545
            },
            {
            "detail_amount":{"하나은행": 1872, "농협은행/수협은행": 10984, "우리은행": 15846, "국민은행": 16017, "신한은행": 2724,…},
            "year": "2010년",
            "total_amount": 114903
            },
            {
            "detail_amount":{"하나은행": 9283, "농협은행/수협은행": 19847, "우리은행": 29572, "국민은행": 29118, "신한은행": 11106,…},
            "year": "2011년",
            "total_amount": 206693
            },
            {
            "detail_amount":{"하나은행": 12534, "농협은행/수협은행": 27253, "우리은행": 38278, "국민은행": 37597, "신한은행": 21742,…},
            "year": "2012년",
            "total_amount": 275591
            },
            {
            "detail_amount":{"하나은행": 15167, "농협은행/수협은행": 17908, "우리은행": 37661, "국민은행": 33063, "신한은행": 21330,…},
            "year": "2013년",
            "total_amount": 265805
            },
            {
            "detail_amount":{"하나은행": 20714, "농협은행/수협은행": 20861, "우리은행": 52085, "국민은행": 48338, "신한은행": 28526,…},
            "year": "2014년",
            "total_amount": 318771
            },
            {
            "detail_amount":{"하나은행": 37263, "농협은행/수협은행": 18541, "우리은행": 67999, "국민은행": 57749, "신한은행": 39239,…},
            "year": "2015년",
            "total_amount": 374773
            },
            {
            "detail_amount":{"하나은행": 45485, "농협은행/수협은행": 23913, "우리은행": 45461, "국민은행": 61380, "신한은행": 36767,…},
            "year": "2016년",
            "total_amount": 400971
            },
            {
            "detail_amount":{"하나은행": 35629, "농협은행/수협은행": 26969, "우리은행": 38846, "국민은행": 31480, "신한은행": 40729,…},
            "year": "2017년",
            "total_amount": 295126
            }
        ]
     }
     ``` 
* **[API 2]**
   - **GET** /api/max/year HTTP/1.1
   - URL : http://localhost/api/total/year
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - 200 OK Response Body
      ```json
      {
          "year": 2017,
          "bank": "주택도시기금"
      }
     ```
* **[API 3]**
   - **GET** /api/minmax/year HTTP/1.1
   - URL : http://localhost/api/minmax/year
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - 200 OK Response Body
      ```json
     {
         "bank": "외환은행",
         "support_amount":
         [
             {
                 "bank": "외환은행",
                 "year": 2015,
                 "amount": 1702
             },
            {
                "bank": "외환은행",
                "year": 2017,
                "amount": 0
            }
         ]
     }
     ```

* **[예측 API]**
   - **POST** /api/predict/amount HTTP/1.1
   - URL : http://localhost/api/predict/amount
   - Headers
      - Content-Type : application/json;   
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - Request Body
      ```json
      {"bankName":"국민은행","month":2}
     ```
   - 200 OK Response Body
     ```json
     {
         "bank": "국민은행",
         "year": 2018,
         "month": 2,
         "amount": 3339
     }
     ```

