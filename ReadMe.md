### Simple RESTful api in scala

#### How to use

1. clone the repo

2. cd into project's root directory

3. make sure you have java and sbt installed

4. run `sbt run`

5. application will be published on `port 9000`

6. active routes `/api/ping`, `/api/post/:id`, `/api/post/:id/comments`

7. for now all the posts and comments are hardcoded in `app/repositories/DataRepository`