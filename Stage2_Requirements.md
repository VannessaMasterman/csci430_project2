# Stage 2 Requirements - Add additional states
these are the requirements set out by my professor. Writing them here so I can wasily reference them.

**DUE DATE: FRIDAY NOVEMBER 19.**

## What should the Stage 2 UI look like?
The new UI should have 6 states. The OpeningState (or LoginState) will have the option of going to ClientMenuState(user must provide client ID), ClerkMenuState or ManagerMenuState.

## In the ClientMenuState, the Context has stored the ClientID for the current client; all operations are for that ClientID. The state will have operations for the following:
* Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.
 * Show list of products with sale prices. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
 * Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
 * Modify client’s shopping cart (New State). A menu is displayed; actor can view cart cart contents (system displays contents), add product (system prompts for id and qty), remove product(system prompts for id) or change quantity(system prompts for id and qty).
 * Display client’s waitlist.
 * Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClerkMenuState.)

## In the ClerkMenuState, we have the following operations:
 * Add a client. Gets details of new client; calls method on Façade.
 * Show list of products with quantities and sale prices. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
 * Query system about clients (New State). A menu is displayed; actor can choose to display all clients, see list of clients with outstanding balance or see list of clients with no transactions.
 * Become a client. The actor will be asked to input a ClientID; if valid, this ID will be stored in Context, and the system transitions to the ClientMenuState.
 * Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator.
 * Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).
 * Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)

## In the ManagerMenuState, we have the following operations:
 * Add a product
 * Add a supplier
 * Show list of suppliers
 * Show list of suppliers for a product, with purchase prices
 * Show list of products for a supplier, with purchase prices
 * Add a supplier for a product. Actor provides productID, supplierID and purchase price
 * Modify purchase price for a particular product from a particular supplier. Actor provides productID, supplierID and purchase price
 * Become a salesclerk
 * Logout.

# WHAT TO SUBMIT.
Cover sheet in D2L, explaining where to find the code, and how to compile and run it; code in CourseFiles account in a folder labeled Proj2Stage2