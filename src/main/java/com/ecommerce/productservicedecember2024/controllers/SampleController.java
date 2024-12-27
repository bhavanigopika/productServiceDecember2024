package com.ecommerce.productservicedecember2024.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This class will now be hosting a set of HTTP API's
//Get request, Put request, delete, post

//over the network I am going to call SampleController and through HTTP API's - use @RestController annotation
@RestController
@RequestMapping("/say") //This is "say type API's" and call it as identifier
public class SampleController {

    //localhost:8080/say/hello - This is API, output: Hello
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    //http://localhost:8080/say/hello/abi ; output: Hello abi
    //if it is in {} -> name is variable parameter, name is coming from the url path
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable ("name") String name){
        return "Hello " + name;
    }

    //http://localhost:8080/say/hello/vidhu/6
    //output: hellovidhuhellovidhuhellovidhuhellovidhuhellovidhuhellovidhu
    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("times") int count){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < count; i++){
            output.append("hello" + name);
            //output.append("hello").append(name);
        }
        return output.toString();
    }

    //localhost:8080/say/bye, output: Bye
    @GetMapping("/bye")
    public String sayBye(){
        return "Bye";
    }

    //http://localhost:8080/say/bye/aarthi ; output: Bye aarthi
    @GetMapping("/bye/{name}")
    public String sayBye(@PathVariable String xyz){
        return "Bye " + xyz;
    }
}


/*
Everyone localhost is mapped to 127.0.0.1 IP address
localhost is server IP address

local laptop IP address is unique IP address assigned to your laptop
http://localhost:8080/say/hello
port no: 8080 is entry where someone is listening for your request
port no: 8080 is commonly port number for web servers especially alternate or local web servers

www.scaler.com - This is domain name which maps to some IP address(123.122.134.129)
 */