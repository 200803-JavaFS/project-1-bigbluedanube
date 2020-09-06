console.log("Hello JavaScript. We meet again.");

var a = 100;
a = true;
a = null;
a = 'I am a String.';

console.log(a); // prints I'm a String.

var b;
console.log(b); // prints undefined

//This is a JS function--it has an identifier.
function logMessageFromInput() {
    message = document.getElementById('messageInput').value;
    console.log(message);
}

//This is an ANONYMOUS JS function, and is stored in a Named Variable.
var anon  = function(){
    console.log("I am an anonymous function.")
}

anon();

//Callback Function

function funcOne(x) {
    console.log('x = ' + x);
}

function funcTwo(y, Callback) {
    Callback(y);
}


funcTwo(9, funcOne);
funcTwo(5, funcThree);

/*the funcThree declaration is being hoisted so it can be called on line 38
before it is declared on line 44. Hoisting breaks the top-to-bottom convention
of how code is executed, causing the variables to be declared first.
*/
function funcThree(z) {
    console.log("z = " + z);
}

/* Closure example (no one can change the value of x here, 
and the adder "remembers" the variables and arguments of its outer function, 
but can no longer change them)
*/

var q = 'I am a global trotter.';

function adder(x) {
    return function(y){
        console.log(w);     // This would throw an exception in Java.
        console.log(q);     // This would not.
        var w = 5           // The variable w is assigned AFTER it is declared. But because it is hoisted, the code will run as though I declared it BEFORE assigning it to a value.
        return x + y;
    }
}

var add5 = adder(5);
var add10 = adder(10);

console.log(add5(7));

// Type Coercion Examples. Run them in the browser console to see it happen in real time.

console.log("3" * 2);               // 6
console.log(true == 1);             // true
console.log(true == 8);             // false
console.log('123' == 123);          // true
console.log("123" === 123);         // false
console.log(undefined == false);    // false
console.log("string" == true);      // false
console.log('' == false);           // true
console.log(NaN == true);           // false
console.log(1 == 1.0);              // true
console.log(null == undefined);     // true
console.log(null == 0);             // false
console.log(0 == false);            // true
console.log("true" == true);        // fasle
console.log("string" * 8);          // NaN

// Truthy and False-y Values
console.log("The following are Truthy and False-y Values:")
if(true){
    console.log("true");
} else {
    console.log("false");
} // TRUE


if("true"){
    console.log("true");
} else {
    console.log("false");
} // TRUE


if(""){
    console.log("true");
} else {
    console.log("false");
} // FALSE
// this one is specifically for me and not included in the demo. 
// I want to see if there is a difference between a string with a blank space and a string with NOTHING.
if(" "){
    console.log("true");
} else {
    console.log("false");
} // TRUE


if(0){
    console.log("true");
} else {
    console.log("false");
} // FALSE


if(-35){
    console.log("true");
} else {
    console.log("false");
} // TRUE

console.log("testing if the word String is true...")

if("string"){
    console.log("true");
} else {
    console.log("false");
} // TRUE


if("string" * 8){
    console.log("true");
} else {
    console.log("false");
} // FALSE


if(null){
    console.log("true");
} else {
    console.log("false");
} // FALSE

if(undefined){
    console.log("true");
} else {
    console.log("false");
} // FALSE

function test() {
    return(5||0);
}
console.log(test());
// Returns 0.

function test() {
    return(5 && 0);
}
console.log(test());
// returns 0

// This is the DOM Manupulation Section.
console.log("This is the DOM Manupulation Section.");

let divElements = document.getElementsByClassName("coolclassname");

// Gets me my first div element.
let firstdiv = divElements[0];

let emptyP = document.getElementById("p3");

let myButton = document.getElementsByTagName("button")[0];  // If the square brackets were not included, this would still return an array of buttons with an index of one, so only one button object.

// now we can start our actual DOM Manipulation.

myButton.onclick = domFunc;

function domFunc() {
    firstdiv.style.backgroundColor = "rebeccapurple";
    emptyP.textContent = "I now have content! YASSSSSS."
}

// You just used JavaScript to manipulate the objects in your HTML document. 
// This is how you will do things like add values to tables displayed on the HTML page for your project.