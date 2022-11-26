# EFT Store
E-commerce servlets website inspired by Escape From Tarkov video game

## 🛠 Used technologies
- Bootstrap and JS for frontend
- Java Servlets, JSTL, FTL, PostgreSQL for backend

## 👀 Features
### User registration and authentication
Before accessing the store user should register and/or log in

Sign up page

<img src="https://i.imgur.com/vzNKMJf.png" alt="Sign up page">

Sign in page

<img src="https://i.imgur.com/0INCsmF.png" alt="Sign in page">

### Profile page
After creating an account, user can view it here, edit its credentials and upload or delete avatar

Here is profile with default avatar

<img src="https://i.imgur.com/yNkQwsi.png" alt="Profile with default avatar">

And here is with uploaded one

<img src="https://i.imgur.com/3684XM1.png" alt="With uploaded one">

Change password page

<img src="https://i.imgur.com/6dV2lJB.png" alt="Change password page">

Upload avatar page

<img src="https://i.imgur.com/c0t3ej2.png" alt="Upload avatar page">

### Store catalog
Website's home page, where user can view items for sale and add them to cart

<img src="https://i.imgur.com/uOeCoPI.png" alt="Store catalog">

### Cart
User can view items he added to cart and their total cost here, do checkout or just clear the cart

<img src="https://i.imgur.com/HfIOTjn.png" alt="Cart">

Empty cart with disabled buttons

<img src="https://i.imgur.com/MYdHX0E.png" alt="Empty cart">

### Item details
User can open item details by clicking on it in store catalog or cart

<img src="https://i.imgur.com/V1bVlm3.png" alt="Item details">

### I18n
The website fully supports both English and Russian languages, which can be switched in the top navigation bar

<img src="https://i.imgur.com/sCc4V5R.png" alt="Store page in Russian">

## 🌐 Deployment
- Clone the repository
- Create PostgreSQL database and tables using the scripts in `data`
- Create `app.properties` file in `src/main/resources` and add there following properties:
  - `db.url` - database URL
  - `db.master_username` - database master username
  - `db.master_password` - database master password
  - `avatars.path` - path to the folder where users avatars will be stored
- Deploy the project to Tomcat 9 server