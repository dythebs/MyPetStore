# MyPetStore

This sample is a web application built on MyBatis, Spring Boot(Spring MVC, Spring Security) and Thymeleaf. This is another implementation of MyBatis JPetStore sample application (https://github.com/mybatis/jpetstore-6) ,and some [additional features](#additional-features) are added.

## Index Page

![](https://user-images.githubusercontent.com/37578699/43036096-26b2f834-8d2d-11e8-89d5-8c35ead68a60.png)

## Requirements

* Java 8+ (JDK 1.8+)

## Stacks

* MyBatis
* Spring Boot
* Thymeleaf
* MySQL
* Tomcat

## Default active accounts (ID/PASSWORD)

* j2ee/j2ee
* ACID/ACID

## Project Structure

```
.
│  .gitattributes
│  .gitignore
│  jpetstore.sql  //Create Database Script
│  mvnw
│  mvnw.cmd
│  pom.xml
│  readme.md
│
├─.mvn
│
└─src
    ├─main
    │  ├─java
    │  │  └─org
    │  │      └─csu
    │  │          └─mypetstore
    │  │              │  MypetstoreApplication.java
    │  │              │
    │  │              ├─control
    │  │              │
    │  │              ├─dao
    │  │              │
    │  │              ├─domain
    │  │              │
    │  │              └─service
    │  │                  │
    │  │                  └─impl
    │  │
    │  └─resources
    │      │  application.properties   //Database Configuration
    │      │
    │      ├─mapper
    │      │
    │      ├─static
    │      │  ├─css
    │      │  │
    │      │  ├─images
    │      │  │
    │      │  └─js
    │      │
    │      └─templates
    │          │
    │          ├─account
    │          │
    │          ├─catalog
    │          │
    │          ├─common
    │          │
    │          ├─footprint
    │          │
    │          └─order
    │
    └─test

```

## Additional Features

* Add captcha functionality on **sign up** and **sign in**
![](https://user-images.githubusercontent.com/37578699/43036099-27d135f0-8d2d-11e8-9e64-5c2f10531725.png)
* Add internationalization on **sign up** and **sign in**
![](https://user-images.githubusercontent.com/37578699/43036100-28150a28-8d2d-11e8-8d5f-37c1ce7ecbe8.png)
* Add ajax on **sign up** and **cart**
![](https://user-images.githubusercontent.com/37578699/43036163-65de0b1a-8d2e-11e8-8009-e404a874be05.png)
* Record the user's browsing behavior
![](https://user-images.githubusercontent.com/37578699/43036102-290b7c96-8d2d-11e8-82bb-47a2cb923aa8.png)
