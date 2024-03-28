# Запуск backend

Из корневой папки проекта создаем docker образ:
### `docker build -t simulator-backend .`

Запуск образа:
### `docker run -p 8080:8080 -t simulator-backend`
