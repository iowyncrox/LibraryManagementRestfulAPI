library-management-restful-api
============

A Basic Spark Java framework API with an RESTful architecture

Prerequisites
-------------
You will need to following tools in order to work with this project and code

* git 
* JDK 1.8+ 
* Maven 3.x+ 
* Postman
* An IDE of your choice.  (Eclipse, IntelliJ, Spring STS, Netbeans, etc.)


Getting Started
---------------
To run this project locally, perform the following steps.

* Clone project to your machine using git.
* Import the project into your IDE using the maven pom.xml.
* Press play in the package io.spark.librarymanagement.resource.MainEntryPoint


Assumptions
---------------

User has the following properties:

* id
* firstName (alphabets)
* middleName (alphabets, optional)
* lastName (alphabets)
* age (valid non zero positive number)
* gender (M or F)
* phone (10-digit positive number)
* zip (optional)

Book has the following properties:

* id
* name
* authors (can have multiple authors)
* checkedOutBy (user that has this book checked out)


Sample JSON
---------------

* User:
{
    "firstName": "apple",
    "lastName": "orange",
    "age": 7,
    "gender": "M",
    "phone": "1234567890"
}


* Book:
{
    "name": "Java",
    "author": [
        {
            "author": "hello"
        },
        {
            "author": "world"
        }
    ]
}