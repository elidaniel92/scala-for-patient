/**
 *	The rule of thumb is that
 *  a parameterless method that
 *  doesn’t modify the object
 *	has no parentheses.
 *  e.g "Hello".distinct
 */


/*
It is considered good style to use () for a mutator
method (a method that changes the object state), and to drop the () for an
accessor method (a method that does not change the object state).
That’s what we did in our example:
myCounter.increment() // Use () with mutator
println(myCounter.current) // Don’t use () with accessor
 */