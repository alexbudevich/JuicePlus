Create a Spring Boot application in Java to implement a simple shopping cart microservice - it should provide the following functions as REST endpoints:
•	View cart
•	Add item to cart
•	Remove item from cart
•	Checkout (output of this can simply be a log message to the console: eg "Cart XYZ was checked out")

Some further functional requirements (in order of priority):
•	Different users should have separate shopping carts
•	Carts can contain multiple, integer quantities of the same product
•	Service should provide a total cart price in the view cart response
•	A cart can only be checked out once

A product should have an ID, description and a price (assume £UKP). The user ID will be provided in an http header in the request. Assume there are no taxes or delivery charges.

For the data store a simple in-memory one is sufficient. The products list can be hardcoded within the service.

We understand your time is precious so we anticipate you only spending around an hour on the task. Please write the code and any associated tests as you see fit.

If you run out of time please say what you think is missing from your solution. Also mention any assumptions made, if the requirements are not completely clear.

Once complete please zip up all of the source files including the maven or gradle build files and place on a shared drive so it can be downloaded.
