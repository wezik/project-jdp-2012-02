# Ecommerce Restful API

## Description

This project is our team effort at creating a backend ecommerce service.

## Requirements

Java 8

## How to build

Use `/gradlew build` in terminal

## Endpoints

<details><summary>Product</summary>
  
***

**1. GET TARGET PRODUCT<br>**

**GET** http://yourDeploymentAdress/v1/product/getProduct/{productId}

*This endpoint requires one argument - value of product ID in shop's database<br>*
*Example: http://yourDeploymentAdress/v1/product/getProduct/1294*
*<br>Returns JSON data with product details:*

<details><summary>Example response</summary>

```
{
    "id": 1294,
    "name": "TV",
    "description": "65 inches",
    "price": 3000.00,
    "groupId": 1293
}
```

</details>

**2. GET ALL PRODUCTS<br>**

**GET** http://yourDeploymentAdress/v1/product/getProducts

*This endpoint requires no arguments or body.<br> Returns list of all products, which are saved in shop's database.*

<details><summary>Example response</summary>

```
[
    {
        "id": 1294,
        "name": "TV",
        "description": "65 inches",
        "price": 3000.00,
        "groupId": 1293
    },
    {
        "id": 1295,
        "name": "Computer",
        "description": "MacBook Air",
        "price": 4000.00,
        "groupId": 1293
    }
]
```

</details>

**3. ADD A NEW PRODUCT TO SHOP'S DATABASE**

**POST** http://yourDeploymentAdress/v1/product/createProduct

*This endpoint requires specific body in correct order:<br><br>name<br>description<br>price<br>groupId*

**Before You create a new product, You must create group of products first and assign this product to target group ID !**

<details><summary>Example body</summary>

```
{
    "name": "Computer",
    "description": "MacBook Air",
    "price": 4000,
    "groupId": 1293
}
```

</details>

*ID for this new product in the database is assign automatically.*

<details><summary>Example response</summary>

```
{
    "id": 1294,
    "name": "TV",
    "description": "65 inches",
    "price": 3000.00,
    "groupId": 1293
}
```

</details>

**4. UPDATE PRODUCT IN SHOP'S DATABASE**

**PUT** http://yourDeploymentAdress/v1/product/updateProduct

*This endpoint requires specific body, almost the same as ```createProduct```<br>
The only difference is that You must put product ID (got from database) at the first place.*

<details><summary>Example body</summary>

```
{
    "id": 1294,
    "name": "TV (changed)",
    "description": "55 inches (changed)",
    "price": 2500.00,
    "groupId": 1293
}
```

</details>

*In response You will get JSON data with updated product details.*

<details><summary>Example response</summary>

```
{
    "id": 1294,
    "name": "TV (changed)",
    "description": "55 inches (changed)",
    "price": 2500.00,
    "groupId": 1293
}
```

</details>

**5. DELETE PRODUCT FROM SHOP'S DATABASE**

**DELETE** http://yourDeploymentAdress/v1/product/deleteProduct/{productId}

*This endpoint requires one argument - value of product ID, which You want to remove from shop's database.<br>*
*Example: http://yourDeploymentAdress/v1/product/deleteProduct/1294*
*<br>It doesn't return anything.*

***

</details>

<details><summary>Group</summary>
  
***

**1. GET TARGET GROUP**

**GET** http://yourDeploymentAdress/v1/group/getGroup/{groupId}

*This endpoint requires one argument - value of group ID in shop's database<br>*
*Example: http://yourDeploymentAdress/v1/group/getGroup/12*
*<br>Returns JSON data with group details:*

<details><summary>Example response</summary>

```
{
    "groupId": "12",
    "groupName": "Electronics"
}
```

</details>

**2. GET ALL GROUPS**

**GET** http://yourDeploymentAdress/v1/group/getGroups

*This endpoint requires no arguments or body.<br> Returns list of all groups, which are saved in shop's database.*

<details><summary>Example response</summary>

```
[
    {
        "groupId": "12",
        "groupName": "Electronics"
    },
    {
        "groupId": "13",
        "groupName": "Furnitures"
    },
]
```

</details>

**3. ADD A NEW GROUP TO SHOP'S DATABASE**

**POST** http://yourDeploymentAdress/v1/group/addGroup

*This endpoint requires specific body in correct order:<br><br>groupName<br>*

<details><summary>Example body</summary>

```
{
    "groupName": "Electronics"
}
```

</details>

<details><summary>Example response</summary>

```
{
    "groupId": 16,
    "groupName": "Electronics"
}
```

</details>

**4. UPDATE GROUP IN SHOP'S DATABASE**

**PUT** http://yourDeploymentAdress/v1/group/updateGroup

*This endpoint requires specific body in correct order:<br><br>groupId<br>groupName*

<details><summary>Example body</summary>

```
{
    "groupId": 16,
    "groupName": "Electronics"
}
```

</details>

*In response You will get JSON data with updated group details.*

<details><summary>Example response</summary>

```
{
    "groupId": 16,
    "groupName": "Electronics"
}
```

</details>

***

</details>


<details><summary>Cart</summary>
  
  ***
  
**1. CREATE A NEW EMPTY CART**
  
**POST** http://yourDeploymentAdress/v1/cart/createCart

*This endpoint requires no arguments or body and creates a cart with ID and empty cart entry list.<br>
It returns JSON data with created cart details:*

<details><summary>Example response</summary>

```
{
    "id": 1296,
    "cartEntryList": []
}
```

</details>

**2. GET ALL PRODUCTS FROM TARGET CART**

**GET** http://yourDeploymentAdress/v1/cart/getProducts/{cartId}

*This endpoint requires one argument - value of cart ID.<br>
It returns JSON data with list of cart entries, which belongs to target cart.<br>*
*Example: http://yourDeploymentAdress/v1/cart/getProducts/1296*

<details><summary>Example response</summary>

```
[
    {
        "cartEntryId": 1297,
        "cartId": 1296,
        "productDetails": {
            "id": 1294,
            "name": "TV (changed)",
            "description": "55 inches (changed)",
            "price": 2500.00,
            "groupId": 1293
        },
        "quantity": 2
    },
    {
        "cartEntryId": 1298,
        "cartId": 1296,
        "productDetails": {
            "id": 1295,
            "name": "Computer",
            "description": "MacBook Air",
            "price": 4000.00,
            "groupId": 1293
        },
        "quantity": 2
    }
]
```

</details>

**3. ADD NEW ENTRY TO TARGET CART**

**POST** http://yourDeploymentAdress/v1/cart/addProduct

*This endpoint requires specific body in correct order:<br><br>cartId<br>productId<br>quantity<br>*

<details><summary>Example body</summary>

```
{
    "cartId": 1296,
    "productId": 1295,
    "quantity": 2
}
```

</details>

*It returns JSON data with new cart entry details: entry ID, ID of cart, which contains this entry, product details and quantity.*

<details><summary>Example response</summary>

```
{
    "cartEntryId": 1298,
    "cartId": 1296,
    "productDetails": {
        "id": 1295,
        "name": "Computer",
        "description": "MacBook Air",
        "price": 4000.00,
        "groupId": 1293
    },
    "quantity": 2
}
```

</details>

**4. DELETE ENTRY FROM TARGET CART**

**DELETE** http://yourDeploymentAdress/v1/cart/deleteProduct/{cartEntryId}

*This endpoint requires one argument - value of cart entry ID, which You want to remove.*
*Example: http://yourDeploymentAdress/v1/cart/deleteProduct/1298*
*<br>It doesn't return anything.*

**5. DELETE CART**

**DELETE** http://yourDeploymentAdress/v1/cart/deleteProduct/{cartId}

*This endpoint requires one argument - value of cart ID, which You want to remove.*
*Example: http://yourDeploymentAdress/v1/cart/deleteProduct/1296*
*<br>It doesn't return anything.*

***

</details>

<details><summary>Order</summary>
  
  ***

**GET** http://endpoint/v1/loremipsum

*description*

<details><summary>Body</summary>

```
{


}
```

</details>

**GET** http://endpoint/v1/loremipsum

*description*

<details><summary>Body</summary>

```
{


}
```

</details>

**PUT** http://endpoint/v1/loremipsum

*description*

<details><summary>Body</summary>

```
{


}
```

</details>

**POST** http://endpoint/v1/loremipsum

*description*

<details><summary>Body</summary>

```
{


}
```

</details>


**DELETE** http://endpoint/v1/loremipsum

*description*

***

</details>

<details><summary>User</summary>
  
  ***

**1. GET ALL USER**

**GET** http://yourDeploymentAdress/v1/user/getUsers

*This endpoint requires no arguments or body.<br> Returns list of all users, which are saved in shop's database.*

<details><summary>Body</summary>

```
[
    {
        "id": 1,
        "username": "Piotr",
        "status": "1",
        "userKey": 59403
    },
    {
        "id": 2,
        "username": "Admin",
        "status": "1",
        "userKey": 96997
    },
    {
        "id": 3,
        "username": "User",
        "status": "0",
        "userKey": 31251
    }
]
```

</details>

**2. GET USER BY ID**

**GET** http://yourDeploymentAdress/v1/user/getUser/{id}

*This endpoint requires one argument - value of user ID in shop's database<br>*
*Example: http://yourDeploymentAdress/v1/user/getUser/3*
*<br>Returns JSON data with user details:*

<details><summary>Body</summary>

```
{
    "id": 3,
    "username": "User",
    "status": "0",
    "userKey": 31251
}
```

</details>

**3. UPDATE PRODUCT IN SHOP'S DATABASE**

**PUT** http://yourDeploymentAdress/v1/user/updateUser

*This endpoint requires specific body, almost the same as ```createUser```<br>
The only difference is that You must put user ID (got from database) at the first place.*

<details><summary>Example body</summary>

```
{
    "id": 2,
    "username": "Admin - update",
    "status": "1",
    "userKey": 96997
}
```

</details>

*In response You will get JSON data with updated user details.*

<details><summary>Example response</summary>

```
{
    "id": 2,
    "username": "Admin - update",
    "status": "1",
    "userKey": 96997
}
```

</details>

**4. ADD A NEW USER TO SHOP'S DATABASE**

**POST** http://yourDeploymentAdress/v1/user/createUser

*This endpoint requires specific body in correct order:<br><br>username<br>status<br>userKey*

<details><summary>Example body</summary>

```
{
    "username": "User",
    "status": "0",
    "userKey": 31251
}
```

</details>

*ID for this new user in the database is assign automatically.*

<details><summary>Body</summary>

```
{
    "id": 3,
    "username": "User",
    "status": "0",
    "userKey": 31251
}
```

</details>

**5. DELETE USER FROM SHOP'S DATABASE**

**DELETE** http://yourDeploymentAdress/v1/user/deleteUser/{id}

*This endpoint requires one argument - value of user ID, which You want to remove from shop's database.<br>*
*Example: http://yourDeploymentAdress/v1/user/deleteUser/1*
*<br>It doesn't return anything.*

***

</details>

## Troubleshooting
_Common problems and how to solve them_
