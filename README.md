# face-recognition-app
Full stack JavaScript SPA, done with React, Node.js, Express.js, PostgreSQL, using Clarify API and deployed on Github Pages and Heroku.

Test the appp here:
https://rickyriosp.github.io/face-recognition-app/

Features:

- Register: validates and sanitized inputs. Name should have at least 2 characters, password at least 3 and email should contain @ and . characters. If values are valid, and the email does not exist, they are stored in a database, encrypting the password. If register fails, an error message is displayed.

- Sigin: validates and sanitizes inputs. Checks if user-password pair exists in database. If sign in succedes, the corresponding user profile is displayed, else an error message is displayed.

- Face recognition: Once the profile is displayed, the user can enter the url of an image. If the image contains human faces, the user entries are incremented by 1. In case the url is not valid, an error message is displayed. Face detection is done using Clarifai API.

If you want to see the backend API, check face-recognition-app-api repository on:
https://github.com/rickyriosp/face-recognition-app-api
