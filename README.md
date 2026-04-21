# w2120164-csa\_cw



Coursework for Client-Server Architecture Module: Developing a JAX-RS RESTful Service



The project must be hosted in a public GitHub repository. Your reposi-

tory must contain a README.md that provides:

\- An overview of your API design.

\- Explicit, step-by-step instructions on how to build the project and launch the server.

\- At least five sample curl commands demonstrating successful interactions with dif-

ferent parts of the API.



=============================================================================

&#x20;- OVERVIEW OF API DESIGN -

=============================================================================



1.1

\------------------------------



=============================================================================

&#x20;- INSTRUCTIONS TO BUILD PROJECT AND LAUNCH SERVER -

=============================================================================



1.1

\------------------------------





=============================================================================

&#x20;- SAMPLE CURL COMMANDS -

=============================================================================



POST http://localhost:8080/SmartCampus/rooms

\------------------------------

{

&#x20;   "id": "LIB-666",

&#x20;   "name": "Classroom 6",

&#x20;   "capacity": 30,

&#x20;   "sensorIds": \[]

}





=============================================================================

&#x20;- REPORT - 

=============================================================================



1.1

\------------------------------

By default, the JAX-RS life cycle follows the request-scoped approach where a new instance 

is instantiated for every incoming request. Once the execution is complete, the instance is 

then discarded. This makes it thread-safe but does not allow for persistent shared data with static lists or maps. If the singleton lifecycle is followed instead, the instance 

gets shared among different threads, allowing for the shared state to persist. However, 

this can lead to concurrency issues when two threads try to access the same data. There 

might be synchronization problems, causing race conditions and loss of data.



In order to solve this problem, synchronization strategies could be applied, such as, 

thread-safe collections (ex: ConcurrentHashMap), synchronized blocks, or avoiding a shared 

mutable state by using an external database as storage.





1.2

\------------------------------

The provision of "Hypermedia" (links and navigation within responses) is considered a 

hallmark of advanced RESTful design under HATEOAS (Hypermedia as the Engine of Application 

State) as it allows the API to be self-descriptive and easily to navigate. This removes the 

need of a static documentation as clients can find out available endpoints easily. In 

comparison to static documentation, HATEOS ensures that the API is self-documenting. This 

makes the system flexible as the URL structures can be updated whenever necessary as long 

as the hypermedia links are updated.





2.1

\------------------------------

If returning a list of rooms, returning only IDs reduces the network bandwidth usage as the 

response is much smaller. However this would require the client to make additional requests 

to retrieve all the details of the room, which could be frustrating with the increased 

number of API calls. While returning full room objects may be more intensive on bandwidth 

as its response size is bigger, it reduces client-side complexity as all the necessary data 

is in one single response. This improves efficiency on the client's end by minimizing the 

number of API calls performed by the client. 





2.2

\------------------------------



The DELETE operation is idempotent in this implementation because multiple identical 

requests lead to the same final server state. When the client sends the initial DELETE 

request for a room, the room is located and removed from the system successfully, changing 

the server state once. If the client sends another DELETE request for the same room, the 

server will find that it no longer exists, thus triggering a DataNotFoundException (404 

response). The server state remains unchanged as the room has already been deleted.





3.1

\------------------------------



When the @Consumes (MediaType.APPLICATION\_JSON) annotation is explicitly used on the POST 

method, it specifies that the server only accepts requests bodies of JSON type. Any other 

format such as text/plain or application/xml will not be processed as it does not match the 

expected media type. JAX-RS automatically identifies the mismatch between the media type 

defined by @Consumes and the content type header, and responds with a 415 Unsupported Media 

Type status code, thus preventing the resource method from being called.





3.2

\------------------------------



In this project, filtering was implemented using @QueryParam. When contrasting this with 

the alternative design where the type is path of the URL path, the query parameter approach 

is generally considered superior as it allows for more flexible filtering. Using this 

approach allows users to combine multiple filters easily without changing the endpoint 

structure. It also allows for scalability as new filters can be added without breaking 

existing routes or needing new endpoints to be made. Using path parameters can lead to a 

more rigid structure for filtering and searching.





4.1

\------------------------------



The Sub-Resource Locator pattern improves modularity in large RESTful APIs. The delegation 

of separate classes helps manage complexity by breaking down APIs into smaller sub-

components. Each sub-resource is responsible for a specific part of the URI, which means it 

easier to maintain and debug. By using sub-resource locators, the API structure is more 

organized, scales better, and is easier to use.





5.1

\------------------------------



HTTP 422 is often considered more semantically accurate than a standard 404 when the issue 

is a missing reference inside a valid JSON payload because the request is syntactically 

correct and successfully reaches the server. However, the data in the request is 

semantically wrong as it does not follow the application's business logic. A 404 is used 

when the requested resource does not exist at the given URL, whereas a 422 shows that the 

server understands the request, but the logic in the input is invalid so it gets rejected 

even though the endpoint is reachable.





5.2

\------------------------------



From a cybersecurity standpoint, exposing internal Java stack traces to external API 

consumers can lead to major security risks as it reveals implementation details of the 

backend. This can expose internal file paths, class structures, and even server settings. 

An attacker could use this information to find out potential flaws in the system. Stack 

traces could also reveal database queries, which would help an attacker with SQL injections 

which are attacks that expose the query structure and input handling logic.





5.3

\------------------------------



It is advantageous to use JAX-RS filters for cross-cutting concerns like logging as it provides a reusable way of applying logic across different resource methods. Instead of manually inserting Logger.info() statements inside every single resource method, filters allow for logging to be applied automatically before and/or after every request is processed. This reduces code duplication and also improves maintainability. In addition to this, it also ensures consistency among all endpoints since all requests pass through the same filter.

