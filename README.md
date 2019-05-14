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
|Check Email availability| GET | /api/user/register/checkEmail
|Check Username availability| GET | /api/user/register/checkUsername

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
