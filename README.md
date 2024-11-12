This is a sample gradle java project that takes several parameters to test different jva functionality.

# Usage

```
usage: Main [-a] [-b] [-f] [-h] [-o] [-r] [-u]
 -a,--array      find missing element in an array example.
 -b,--binary     find max binary gap example.
 -f,--fib        print this Fibonacci sequence starting at x for y
                 iterations.
 -h,--help       print this help message to the output stream.
 -o,--optional   example using optional fields.
 -r,--rotate     rotate an array.
 -u,--unpair     find unpaired element in list example.
```

# Unit Tests

This project has sample Junit test cases in the src/test/java directory.

# Building

To build outside of the IDE execute:

```
./gradlew build
```

To build skippng all unit tests execute:

```
./gradlew build -x test
```