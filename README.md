# <h1 align="center"> QuesTyme</h1>

![Screenshot (346)](https://user-images.githubusercontent.com/92457968/232373394-2c2fd2e8-1652-4b6b-8db0-79fd147adbe2.png)


<h2>About Project</h2>
This is the Masai Build and Earn collaborative Project.The application provides the features of interview schedulling and one on one session.The highlighting fuctionalities are login/signUp ,calender booking, email notification and reminder and other required functionalities also.

## Tech Stack
![Java](https://img.shields.io/static/v1?label=&message=Java&color=brown&logo=java8&logoColor=FFFFFF)
![SpringBoot](https://img.shields.io/static/v1?label=&message=SpringBoot&color=green&logo=springboot&logoColor=FFFFFF)
![Maven](https://img.shields.io/static/v1?label=&message=Maven&color=brown&logo=apachemaven&logoColor=FFFFFF)
![Mysql](https://img.shields.io/static/v1?label=&message=Mysql&color=lightblue&logo=mysql&logoColor=black)
![Mysql](https://img.shields.io/static/v1?label=&message=Hibernate&color=darkblue&logo=Hibernate&logoColor=FFFFFF)
![SpringSecurity](https://img.shields.io/static/v1?label=&message=SpringSecurity&color=lightGreen&logo=springsecurity&logoColor=FFFFFF)
![PostMan](https://img.shields.io/static/v1?label=&message=PostMan&color=orange&logo=postman&logoColor=FFFFFF)
![JWT](https://img.shields.io/static/v1?label=&message=JWT&color=000000&logo=JSON%20web%20tokens&logoColor=white)
![Aws](https://img.shields.io/static/v1?label=&message=Amazon_AWS&colorFF9900&logo=amazonaws&logoColor=white)

<h2>Contriubuters:</h2>
<p><a href="https://github.com/amolholani">Amol Holani</a> -Engineering Manager BackEnd</p>
<p><a href="https://github.com/TridipRong">Tridip Rong</a> -BackEnd Developer</p>
<p><a href="https://github.com/mdnasirdmt">Md Nasir Uddin</a> -BackEnd Developer</p>
<p><a href="https://github.com/vishal9sep">Vishal Singh</a> -BackEnd Developer</p>
<p><a href="https://github.com/Dks-believer">Deepak kumar singh</a> -BackEnd Developer</p>
<p><a href="https://github.com/yogeshsaini21">Yogesh Saini</a> -BackEnd Developer</p> 
<p><a href="https://github.com/yogeshsaini21">Rahul Chamoli</a> -BackEnd Developer</p> 
<p><a href="https://github.com/yogeshsaini21">Khushi</a> -BackEnd Developer</p> 


## Installation
```
Clone Url : https://github.com/TridipRong/QuesTyme.git
```

### Backend Installation
```
mvn build
```
```
mvn run
```
OR using Spring Boot Suite 4 ide
```
- Import Project In WorkSpace
- Right Click On Project 
- Select Run As 
- Select Spring Boot App
```

## Backend Deployed
- We deployed the backend server on AWS Ubuntu Server
- Deployed service base url 
```
http://35.178.167.63:8888/
```
## Backend API URLs
- API Documentation With Requests Body : [Notion Doc API Documentation](https://night-curtain-9c1.notion.site/Masai-Builds-QuesTyme-APIs-1e5503ff2e4542e3994fbd9d53882d5b)

## Frontend Deployed 
- We deployed the frontend on netlify
```
https://ques-tymes.netlify.app
```


## Features Implemented
- Login(Both)
- Student creation (Single-user/Bulk-user)(Admin)
- Admin dashboard ➖
    - **The Status of All Interviews**
    - **The Status of Interviews in a Particular Batch**
    - **Display Events Based on Meeting Status**
- Create Interviews(Single Interview /Bulk Interview)(Admin)
- Update interview & cancel interview (Admin)
- Create a One-on-One Meeting slot (Admin)
    - Create for a particular date
    - Create solt for some recurring Day
    - View all slots, delete, update
- Status of slots (Admin)
- Students can search instructors by category where they see all available slots for this particular instructor and can book a slot
- Past Interviews & Upcoming Interviews (Both)
- can search interviews by name and batch (Both)
- Student notes & admin feedback

- one can join the inter only 5 mint before the interview time, also there are buttons to start and end the interview for both admin and student every time they are attending the interview they have to do those things to update the interview status, one can add feedback and add a note after the interview started, also an admin cannot end the meeting only when he added the feedback.

### Few Important Pointers:

 

1. after booking the slot the data will be saved in the interview table 
2. if any student or any admin is not registered in the platform interviews cannot be scheduled for them
3. after scheduling the interview email will be sent to both, same as the update and cancel
4. a reminder email will be sent to both 30 minutes before the interview
5. with the cronjob, we will create the recurring sots every day for the same day of the upcoming week

<hr>

## ER Diagram
![questyme](https://user-images.githubusercontent.com/59462773/235342342-1ec5a907-7746-4487-b057-86f60cdeb95b.png)

<p align="center">
  <img src="https://user-images.githubusercontent.com/59462773/224557904-419bda68-dc7c-4b0f-b484-71ac1342e0a7.png" alt="Thank You">
</p>

