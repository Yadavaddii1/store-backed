📦 Wheat Store Backend – API CURL Documentation

🔐 AUTH APIs -----

 https://store-backed.onrender.com

1️⃣ SIGN-UP (Register User)

curl -X POST 'http://localhost:8080/api/auth/signup' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "First customer",
  "userName": "1customer",
  "password": "customer1",
  "confirmPassword": "customer1",
  "phoneNumber": "9999999999"
}'



2️⃣ LOGIN (Get JWT Token)

curl -X POST https://store-backed.onrender.com/api/auth/login \
-H "Content-Type: application/json" \
-d '{
  "userName": "testuser",
  "password": "Password@123"
}'

📌 Response:

{
  "token": eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBZGl0eWF5YWRhdjEiLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE3NzIxOTA2MzgsImV4cCI6MTc3MjI3NzAzOH0.V1un8XctmlFWHCk4Enmn1c6OtsdU-MvmcFq1Kt0xLrk
}

Save this token.



👑 ADMIN PRODUCT APIs -----

Requires ADMIN token


ADMIN_TOKEN

with your real token.


3️⃣ CREATE PRODUCT

curl -X POST http://localhost:8080/api/admin/products \
-H "Content-Type: application/json" \
-H "Authorization: Bearer ADMIN_TOKEN" \
-d '{
  "productName": "Sharbati Wheat",
  "productDescription": "Premium quality wheat",
  "brand": "Aditya Farms",
  "rating": 4.5,
  "variants": [
    {
      "weight": "1kg",
      "price": 50,
      "stock": 100
    },
    {
      "weight": "5kg",
      "price": 230,
      "stock": 50
    }
  ]
}'


4️⃣ UPDATE PRODUCT

curl -X PUT http://localhost:8080/api/admin/products/{productId} \
-H "Content-Type: application/json" \
-H "Authorization: Bearer ADMIN_TOKEN" \
-d '{
  "productName": "Updated Wheat",
  "productDescription": "Updated description",
  "brand": "Aditya Farms",
  "rating": 4.8
}'

Replace {productId} with real ID.


🛍 PUBLIC PRODUCT APIs -----

No token required (if permitAll enabled)


6️⃣ GET ALL PRODUCTS (Basic)

curl http://localhost:8080/api/products


7️⃣ GET SINGLE PRODUCT BY ID

curl http://localhost:8080/api/products/{productId}


📄 PAGINATION --------

8️⃣ PAGE 0, SIZE 5

curl "http://localhost:8080/api/products?page=0&size=5"


9️⃣ PAGE 1, SIZE 3

curl "http://localhost:8080/api/products?page=1&size=3"



📊 SORTING -------

🔟 SORT BY RATING DECENDING

curl "http://localhost:8080/api/products?sortBy=rating&direction=desc"


1️⃣1️⃣ SORT BY NAME ASCENDING

curl "http://localhost:8080/api/products?sortBy=name&direction=asc"



🔍 SEARCH -----

1️⃣2️⃣ SEARCH BY NAME

curl "http://localhost:8080/api/products/search?name=wheat"


1️⃣3️⃣ SEARCH + PAGINATION

curl "http://localhost:8080/api/products?search=atta&page=0&size=3"


1️⃣4️⃣ SEARCH + SORT

curl "http://localhost:8080/api/products?search=wheat&sortBy=rating&direction=desc"




🔐 If Products Require Authentication ----

Use:

curl -H "Authorization: Bearer USER_TOKEN" \
http://localhost:8080/api/products





FULL WORKING CURLS CART ----- 

🟢 VIEW CART ----

curl -H "Authorization: Bearer USER_TOKEN" \
http://localhost:8080/api/cart


🟢 ADD TO CART ----

curl -X POST http://localhost:8080/api/cart/add \
-H "Authorization: Bearer USER_TOKEN" \
-H "Content-Type: application/json" \
-d '{
  "variantId": "UUID_OF_VARIANT",
  "quantity": 2
}'


🟢 REMOVE ITEM ----

curl -X DELETE http://localhost:8080/api/cart/remove/1 \
-H "Authorization: Bearer USER_TOKEN"



🟢 CLEAR CART

curl -X DELETE http://localhost:8080/api/cart/clear \
-H "Authorization: Bearer USER_TOKEN"





1) Checkout (address + cart -> order) 

curl -X POST http://localhost:8080/api/order/checkout \
-H "Authorization: Bearer YOUR_TOKEN" \
-H "Content-Type: application/json" \
-d '{"address":"My Address, Prayagraj"}'

Order applying to proceeding to the payment 




2) Pay for created order

curl -X POST http://localhost:8080/api/order/pay/ORDER_ID \
-H "Authorization: Bearer YOUR_TOKEN" \
-H "Content-Type: application/json" \
-d '{"method":"COD"}'

After payment data


3) GET my orders details 

curl --location 'http://localhost:8080/api/order/my-orders' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEZW1vLTEiLCJyb2xlIjoiVVNFUiIsImlhdCI6MTc3NTU0NjAyMywiZXhwIjoxNzc1NjMyNDIzfQ.QhZhA7sMuw3yHEi7yNVz0qMhXxyjKSu5-6YDtTfAWi4'