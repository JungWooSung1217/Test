# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


# ���� �����ӿ�ũ �� ����ȯ��
* [Spring Boot 2.1.6 / JAVA8 / MAVEN / H2DB / JPA]

# ���� �ذ�����
* []

# ���� �� ������
* [�����ϱ�]
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
* [�α���]
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
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqd3MxMzI1NiIsImlhdCI6MTU2NTUyMjY1MSwiZXhwIjoxNTY2Mzg2NjUxfQ.tsS2dkAGXFq33AGQf7viZuf2QBJHw2NF9yL7wF2UNtk",
        "tokenType": "Bearer"
     }
    ```
* [csv File to H2DB]
   - **POST** /api/save/csv HTTP/1.1
   - URL : http://localhost/api/save/csv
   - Headers
      - Content-Type : multipart/form-data
      - Accept : application/json
      - Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaWF0IjoxNTY1NTY3NDc1LCJleHAiOjE1NjY0MzE0NzV9.0ctIZ2yuvHCjGtvlYiltkyA5JDYh4otyML7QMwv7Lqs
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
* [API 1]
   - **GET** /api/total/year HTTP/1.1
   - URL : http://localhost/api/total/year
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaWF0IjoxNTY1NTY3NDc1LCJleHAiOjE1NjY0MzE0NzV9.0ctIZ2yuvHCjGtvlYiltkyA5JDYh4otyML7QMwv7Lqs
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
* [API 2]
   - **GET** /api/max/year HTTP/1.1
   - URL : http://localhost/api/total/year
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaWF0IjoxNTY1NTY3NDc1LCJleHAiOjE1NjY0MzE0NzV9.0ctIZ2yuvHCjGtvlYiltkyA5JDYh4otyML7QMwv7Lqs
   - 200 OK Response Body
      ```json
      {
          "year": 2017,
          "bank": "���õ��ñ��"
      }
     ```
* [API 3]
   - **GET** /api/minmax/year HTTP/1.1
   - URL : http://localhost/api/minmax/year
   - Headers
      - Accept : application/json
      - Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTExIiwiaWF0IjoxNTY1NTY3NDc1LCJleHAiOjE1NjY0MzE0NzV9.0ctIZ2yuvHCjGtvlYiltkyA5JDYh4otyML7QMwv7Lqs
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

