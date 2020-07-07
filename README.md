# Social Networking Kata

## Compile and run
If your box has java 11 and maven already installed, you can compile the code with
```bash
mvn clean package
```

and run it with
```bash
mvn exec:java -Dexec.mainClass=org.fissore.kata.socialnetwork.CLI
```

Interrupt the program with CTRL+C

## Docker
If your box has docker installed, you can prepare an image with
```bash
docker build . -t ffissore/social_networking_kata
```
and run it with
```bash
docker run --rm -it ffissore/social_networking_kata
```

Interrupt the program with CTRL+C
