### Домашнее задание от 09.04.2023

Вам предстоит реализовать каждому свой эндпоинт.
Распределение следующее:

| Разработчик | Эндпоинт |
|-------------|----------|
| Аброськин   | 4        |
| Адмакин     | 5        |
| Булатов     | 6        |
| Власова     | 1        |
| Врублевская | 2        |
| Жеронкина   | 3        |


Всего будет 6 эндпоинтов:
1. Просмотр всех комментариев конкретного поста 
```bash
GET /posts/{id}/comments
Входные параметры : PathVariable - id поста (uuid)

Пример выходных данных:
[
  {
    "commentId": "uuid",
    "postId": "uuid",
    "author_id": "uuid",
    "text": "string",
    "date": "string"
  },
  {
    "commentId": "uuid",
    "postId": "uuid",
    "authorId": "uuid",
    "text": "string",
    "date": "string"
  },
  ...
]
```
2. Добавление комментария к конкретному посту:
```bash
POST /posts/{id}/comments
Входные параметры: id поста в PathVariable (uuid) 
Request body: 
{
  "authorId": "uuid"
  "text": "string"
}

Пример выходных данных:
{
  "commentId": "uuid",
  "postId": "uuid",
  "authorId": "uuid"
  "text": "string",
  "date": "string"
}
```

3. Добавление лайка к посту (учесть, чтобы нельзя было дважды поставить лайк)
```bash
POST /posts/{id}/like
Входные данные: id поста в PathVariable (uuid)

Пример выходных данных:
{
  "userId": "uuid"
}
```
4. Получение статистики пользователя (сколько постов он оставил,
  сколько комментариев он писал суммарно, сколько собрал лайков на всех постах)
  ```bash
  GET /user/{id}/stat
  Входные параметры : PathVariable - id юзера (uuid)
  Пример выходных данных:
  {
    "userId": "uuid",
    "nickname": "string",
    "posts": 30,
    "comments": 40,
    "likes": 120 
  }
  ```

5. Получение статистики поста (сколько лайков на посте, сколько комментариев, количество слов в посте)
```bash
GET /posts/{id}/stat
Входные данные: id поста(uuid)
Пример выходных данных:
{
  "postId": "uuid",
  "likes": 30,
  "comments": 40,
  "words": 34
}
```

6. Получение сегодняшних постов, упорядоченных по убыванию количества лайков
```bash
GET /posts/today
Пример выходных данных:
[
  {
    "postId": "uuid",
    "title": "string",
    "text": "string",
    "authorId": "uuid",
    "date": "date"
  },
  {
    "postId": "uuid",
    "title": "string",
    "text": "string",
    "authorId": "uuid",
    "date": "date"
  },
  {
    "postId": "uuid",
    "title": "string",
    "text": "string",
    "authorId": "uuid",
    "date": "date"
  }
]

```