# Speedy

[[POST] Add a new user](#add-user)

[[POST] Add a new car](#add-car)

[[POST] Create a new reservation](#add-reservation)

## <a name="add-user"></a>Add a new user

Adds a new user based on the given first name, last name, email and date of birth. 

### Request

`POST /user/new`

#### Request Body

| Parameter     | Type     | Description                         |
| ------------- | ------   | ----------------------------------- |
| first_name    | string   | First name of the created user      |
| last_name     | string   | Last name of the created user       |
| user_email    | string   | E-mail address of the created user  |
| date_of_birth | string   | Date of birth of the created user   |

#### Example

```json
{
    "first_name": "pietje",
    "last_name": "puk",
    "user_email": "pietjepuk@example.com",
    "date_of_birth": "09-09-1996",
}
```

## <a name="add-car"></a>Add a new car

Adds a new car based on the given license plate. 

### Request

`POST /car/new`

#### Request Body

| Parameter     | Type     | Description                            |
| ------------- | ------   | -------------------------------------- |
| license_plate | string   | License plate of the car being created |

#### Example

```json
{
    "license_plate": "XX-123-YY",
}
```

## <a name="add-reservation"></a>Create a new reservation

Create a new reservation for a user with the given user ID and car license plate, with the specified start and end dates.

### Request

`POST /reservation/new`

#### Request Body

| Parameter      | Type   | Description                                        |
| ----------     | ------ | -------------------------------------------------- |
| user_id        | long   | ID of the user making the reservation              |
| license_plate  | string | License plate of the car being reserved            |
| start_date     | string | Start date of the reservation (format: dd-MM-yyyy) |
| end_date       | string | End date of the reservation (format: dd-MM-yyyy)   |

#### Example

```json
{
    "user_id": 123,
    "license_plate": "XX-123-YY",
    "start_date": "01-05-2023",
    "end_date": "10-05-2023"
}
```

