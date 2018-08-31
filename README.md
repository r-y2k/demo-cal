# demo-cal

Command line integer calculator maven project

## Getting Started:
```
         git clone https://github.com/r-y2k/demo-cal.git
         cd demo-cal\cal
         mvn clean install
```

## Target folder will contain jars: 
```
         All in one Jar:  cal-1.0-jar-with-dependencies.jar
         Only project jar without dependencies jars: cal-1.0.jar 
```

## To run program:
```
         cd demo-cal\cal\target
         java -jar cal-1.0-jar-with-dependencies.jar "add(1, mult(2, 3))"	 
```

## Usage: 
```
         java -jar cal-1.0-jar-with-dependencies.jar "add(3,5)"
         java -DlogLevel=INFO -jar cal-1.0-jar-with-dependencies.jar "sub(3,5)"
         java -DlogLevel=ERROR -jar cal-1.0-jar-with-dependencies.jar "div(3,5)"
         java -DlogLevel=DEBUG -jar cal-1.0-jar-with-dependencies.jar "mult(3,5)"
```

### To change Log Level to DEBUG, INFO or ERROR use VM OPTION -DlogLevel=INFO
```
         java -DlogLevel=INFO -jar cal-1.0-jar-with-dependencies.jar "sub(3,5)"
```

## Examples: 
```
         Input	output
         add(1, 2)	3
         add(1, mult(2, 3))	7
         mult(add(2, 2), div(9, 3))	12
         let(a, 5, add(a, a))	10
         let(a, 5, let(b, mult(a, 10), add(b, a)))	55
         let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))	40
```

