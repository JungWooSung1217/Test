# Getting Started

# Development Environment
* [Spring Boot 2.1.6 / JAVA8 / MAVEN / H2DB / JPA]

# ���� �ذ�����
* ����� ��/���� �����ݾ� 1:N ���� ����
* QueryDSL GroupBy �� JAVA8 Stream API�� ���� ������ ����
* JWT�� �̿��� Token ���� �� API ȣ��� �ñ״��� ����
* ������Ȱ��(Exponential Smoothing)�� Ȱ���� �������� �ݾ� ���� API �ۼ�

# How to Build And Execution
* ���� �� ����
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
* **[�α���]**
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

* **[��ū �����]**
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
        "instituteName": "���õ��ñ��"
        },
        {
        "instituteCode": 2,
        "instituteName": "��������"
        },
        {
        "instituteCode": 3,
        "instituteName": "�츮����"
        },
        {
        "instituteCode": 4,
        "instituteName": "��������"
        },
        {
        "instituteCode": 5,
        "instituteName": "�ѱ���Ƽ����"
        },
        {
        "instituteCode": 6,
        "instituteName": "�ϳ�����"
        },
        {
        "instituteCode": 7,
        "instituteName": "��������/��������"
        },
        {
        "instituteCode": 8,
        "instituteName": "��ȯ����"
        },
        {
        "instituteCode": 9,
        "instituteName": "��Ÿ����"
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
         "name": "���ñ��� ������Ȳ",
         "resultList":
         [
             {
                 "detail_amount":{"�ϳ�����": 3122, "��������/��������": 1486, "�츮����": 2303, "��������": 13231, "��������": 1815,��},
                 "year": "2005��",
                 "total_amount": 48016
             },
             {
                "detail_amount":{"�ϳ�����": 3443, "��������/��������": 2299, "�츮����": 4134, "��������": 5811, "��������": 1198,��},
                "year": "2006��",
                "total_amount": 41210
             },
            {
            "detail_amount":{"�ϳ�����": 2279, "��������/��������": 3515, "�츮����": 3545, "��������": 8260, "��������": 2497,��},
            "year": "2007��",
            "total_amount": 50893
            },
            {
            "detail_amount":{"�ϳ�����": 1706, "��������/��������": 9630, "�츮����": 4290, "��������": 12786, "��������": 1701,��},
            "year": "2008��",
            "total_amount": 67603
            },
            {
            "detail_amount":{"�ϳ�����": 1226, "��������/��������": 8775, "�츮����": 13105, "��������": 8682, "��������": 3023,��},
            "year": "2009��",
            "total_amount": 96545
            },
            {
            "detail_amount":{"�ϳ�����": 1872, "��������/��������": 10984, "�츮����": 15846, "��������": 16017, "��������": 2724,��},
            "year": "2010��",
            "total_amount": 114903
            },
            {
            "detail_amount":{"�ϳ�����": 9283, "��������/��������": 19847, "�츮����": 29572, "��������": 29118, "��������": 11106,��},
            "year": "2011��",
            "total_amount": 206693
            },
            {
            "detail_amount":{"�ϳ�����": 12534, "��������/��������": 27253, "�츮����": 38278, "��������": 37597, "��������": 21742,��},
            "year": "2012��",
            "total_amount": 275591
            },
            {
            "detail_amount":{"�ϳ�����": 15167, "��������/��������": 17908, "�츮����": 37661, "��������": 33063, "��������": 21330,��},
            "year": "2013��",
            "total_amount": 265805
            },
            {
            "detail_amount":{"�ϳ�����": 20714, "��������/��������": 20861, "�츮����": 52085, "��������": 48338, "��������": 28526,��},
            "year": "2014��",
            "total_amount": 318771
            },
            {
            "detail_amount":{"�ϳ�����": 37263, "��������/��������": 18541, "�츮����": 67999, "��������": 57749, "��������": 39239,��},
            "year": "2015��",
            "total_amount": 374773
            },
            {
            "detail_amount":{"�ϳ�����": 45485, "��������/��������": 23913, "�츮����": 45461, "��������": 61380, "��������": 36767,��},
            "year": "2016��",
            "total_amount": 400971
            },
            {
            "detail_amount":{"�ϳ�����": 35629, "��������/��������": 26969, "�츮����": 38846, "��������": 31480, "��������": 40729,��},
            "year": "2017��",
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
          "bank": "���õ��ñ��"
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
         "bank": "��ȯ����",
         "support_amount":
         [
             {
                 "bank": "��ȯ����",
                 "year": 2015,
                 "amount": 1702
             },
            {
                "bank": "��ȯ����",
                "year": 2017,
                "amount": 0
            }
         ]
     }
     ```

* **[���� API]**
   - **POST** /api/predict/amount HTTP/1.1
   - URL : http://localhost/api/predict/amount
   - Headers
      - Content-Type : application/json;   
      - Accept : application/json
      - Authorization : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaXNzIjoiZGVtb2FwaS5jb20iLCJpYXQiOjE1NjU1ODE2NzIsImV4cCI6MTU2NjQ0NTY3Mn0.v8ICaBEtI8SxBhUow710iUpOYVub9PkBBW2z3nZyHyw
   - Request Body
      ```json
      {"bankName":"��������","month":2}
     ```
   - 200 OK Response Body
     ```json
     {
         "bank": "��������",
         "year": 2018,
         "month": 2,
         "amount": 3339
     }
     ```

