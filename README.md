# Welcome to Lemory

Lemory is a school project create by Pascal Abt, Lukas König, Benjamin Schellmann and Nicolas Schüssling. Lemory is a Memory type learning game targeted for elementary school. 


# About the APP

  >Pascal soi do irgendan scheiß schreim über die App.

# About the API

The API is created by Lukas König. The API is an RESTful Web Service which allows any Application supporting the HTTP protocol to access it. It provides endpoints for all needed database operations.

# API Doc

|                |Operation Type   |ENDPOINT
|----------------|-----------------|---------|
|Login           |POST             |/api/user/login
|Register		 |POST	 		   |/api/user/register
|Check Email availability| GET | /api/user/register/checkEmail/`email`
|Check Username availability| GET | /api/user/register/checkUsername/`username`

## Login Endpoint

The Login endpoint requires a body in the following format:
```
{
	username: <username>,
	password: <password>
}
```

As a Callback you'll receive an access Token which needs to be included in the HTTP Header for every further request.

```
{
	token: <token>
}
```
## Register Endpoint
The Register endpoint allows for easy User-Registration. Required are the [Email Availability Check](#emailCheck]) and the [Username Availability Check](#usernameCheck) which both return statuscodes. These Statuscodes need to be included to the body of the call.

Register body format:
```
{
	user_available: <statuscode>,  //CALLBACK of checkUsername
	email_available: <statuscode>, //CALLBACK of checkEmail
	username: <username>,	//MIN 3 MAX 15
	password: <password,	//MIN 5 MAX 30
	email: <email-address>,
	first_name: <first_name>, //MIN 2 MAX 15
	last_name: <last_name>,	//MIN 2 MAX 15
	birth_date: <birth_date> //YYYY-MM-DD
}
```
As a Callback you'll receive an access Token which needs to be included in the HTTP Header for every further request.

```
{
	token: <token>
}
```



## <a name="emailCheck"></a> Check Email availability Endpoint
>Moch i am Abend
## <a name="usernameCheck"></a> Check Username availability Endpoint
>Moch i am Abend
