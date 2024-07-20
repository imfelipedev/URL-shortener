# Simple URL shortening API

This project was developed solely for study and practice purposes.

## Installation

1. Clone the repository:

```bash
$ git clone https://github.com/imfelipedev/URL-shortener
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints

#### Shorten URL.

```http
POST /shorten
```

| Parâmetro | Tipo     | Descrição      |
| :-------- | :------- | :------------- |
| `url`     | `string` | URL to shorten |

#### Get URL/Redirect.

```http
GET /redirect/${id}
```

| Parâmetro | Tipo     | Descrição     |
| :-------- | :------- | :------------ |
| `id`      | `string` | Shortened URL |

## License

[MIT](https://choosealicense.com/licenses/mit/)
