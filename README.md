![Literalura Logo](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/83d22992-6cc7-4c63-a618-2d731db66b43)
# Introduction
This is the challenge of Alura Oracle Next Education, the purpose is to practise and use the Gutendex API to save to our database in MySQL using Spring Data


## Quickstart
[Last fixes or bugs](List-of-fixes-or-bugs)

[Folder Structure](#folder-structure)

[Technologies](#technologies)

[Demo images](#demo)

## List of fixes or bugs
### Hotfixes:
<b>HotFix v1.01 (11 June 2024):</b>

- Fixed showing language as a code, now showing full language title
  - Ex. When getting the book from Gutendex it was showing Idioma with code language: 'en', 'es', 'fr'.
  - Updated using getFullLanguageName(languageCode) static method to convert the language code to the full language  title in spanish.  
- Fixed when retrieving Books, Authors, or Top Books, there was an empty repose. Forgot to write the else to send a message there were no elements in our database.

<b>HotFix v1.01b (13 June 2024):</b>
- Fixed when a book was already registered, the language was shown as code but language that language is not defined in our program, so we filter as N/A.
- Fixed when an author birth and death year was shown as null, we fixed with custom message.
- Fixed retreiving books writed by author


### Limited:
First Release V1.00:
- As mainly the majority books are written in English, Spanish or French. The application is limited for these three languages but no worries if the book is written in another language it will show as N/A.
## Folder Structure
```

src
│
├── entities ───────── Entities for our database
│
├── menu ───────────── Main menu of the application, a menu list messages and a validator
│
├── models ─────────── Models for API
│    │
│    └── dto ───────── Filter to send the data from dto not entities.
│
├── repository ─────── Applying Spring Data JPA to make the queries.
│
└── service ────────── All our persistence logics and api consumption.

```

## Technologies
- Java

- Spring Boot

- Spring Data JPA

- Hibernate ORM

- MySQL

- Gutendex API

# Demo

#### Main menu
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/595fba29-8475-493d-98b6-51733bbc0113)

## Option 1
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/abe1db27-dab0-489a-a3a9-1bbbcda5e10f)  
### Option 2
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/09cf8776-af2c-4950-bab0-d68927c671b6)
### Option 3
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/603ff1f1-2ff7-4488-b813-298b6f22499a)
### Option 4
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/4fde35fa-e987-4b1e-93ed-61c1d9f2fb06)
### Option 5
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/5f5ac7bb-d04f-40b4-bfea-f2cc99365d97)
### Option 6
![image](https://github.com/fr4ncisx/Literalura-Challenge/assets/103074521/f06fc394-d668-479a-9281-5354549951d1)








