# Spring-Citrix-Coding-Test
Citrix Coding Test solution with Spring Boot
A Coding Excercise during Citrix recruitement proccess

# Assignment Description

In your language of choice create a server application that implements a HTTP based REST
API with the following functions:
1. Users can subscribe to channels and unsubscribe from channels
2. Users can publish messages to a channel
3. Users can retrieve (read) messages published to a channel
It is expected that the server application will never have any downtime (because all servers
in Citrix never suffer hardware problems), so it is enough to keep the
messages/subscriptions in memory; they do not need to be persisted across server restarts.
The result of your work will be handed over to an operations team who will also need
instructions how to install and run your application. Before installing your application they
also want to be confident that your program is properly tested (but they are actually too
lazy to test it themselves).
In the rest of this document blue text is sent to the server (and needs to be processed by
your program); red text is received from the server (sent by your program).

API calls
Subscribing to a channel
HTTP request: POST /<channel>/<username>
Subscribes the user to the channel. All messages posted to the channel after the subscription
can be read by the user.
Expected result:
Status: 200 Subscription succeeded
Unsubscribing from a channel
HTTP request: DELETE /<channel>/<username>
Unsubscribes the user from the channel. Messages posted after unsubscription should not be
read by the user.
Expected results:
On success:
Status: 200 Unsubscribe succeeded
If user was not subscribed:
Status: 404 The subscription does not exist
Reading a message
HTTP request: GET /<channel>/<username>
Returns the next message on the specified channel for the user.
Expected results:
On success:
Status: 200 Retrieval succeeded
Body: the content of the message
When user is not subscribed to the channel:
Status: 404 The subscription does not exist
When no more messages to read by the user
Status: 204 There are no messages available for this topic on
this user
Sending a message to the channel
HTTP request: POST /<channel>
Body: the content of the message
Sends a message to the channel. All users subscribed to that channel will receive the message.
Expected result:
Status: 200 Publish Succeeded
Example
GET /jokes/alice
404 The subscription does not exist
POST /jokes/alice
200 Subscription succeeded
POST /jokes
This is a joke
200 Publish Succeeded
POST /jokes/bob
200 Subscription succeeded
POST /jokes
This is a better joke
200 Publish Succeeded
GET /jokes/alice
200 Retrieval succeeded
This is a joke
GET /jokes/bob
200 Retrieval succeeded
This is a better joke
GET /jokes/bob
204 There are no messages available for this topic on this user
GET /help/alice
404 The subscription does not exist
GET /jokes/alice
200 Retrieval succeeded
This is a better joke
GET /jokes/alice
204 There are no messages available for this topic on this user
DELETE /jokes/alice
200 Unsubscribe succeeded
DELETE /jokes/alice
404 The subscription does not exist
