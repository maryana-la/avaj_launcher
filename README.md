# avaj_launcher
This project is the introduction to the Java world at 42.

Aim of this project is to implement an aircraft simulation program based on the UML class diagram. 
All classes are required to be implemented respecting every detail provided in the diagram. 
If necessary, add more classes or include additional attributes, but changing access modifiers and class hireharchy for the classes provided in the diagram are forbidden.

Following design patterns need to be used Observer, Singleton and Factory.

More details in [subject].

### Project structure

![avaj_uml](https://github.com/maryana-la/avaj_launcher/assets/75047240/0e29b252-5e9e-488a-b378-7bd32cd6a95c)


### Launch the program
- Clone the project and access the folder
```
git clone https://github.com/maryana-la/avaj_launcher && cd avaj_launcher/src
```

- Build project and run simulation
```
./compile.sh && ./run.sh scenarios/scenario_valid.txt && cat simulation.txt
```

#### Build and run simulation with MD5 encoding/decoding
- Compile the project
```
./compile.sh 
```
- Encrypt any scenario file in MD5
```
./codeToMD5.sh scenarios/scenario_valid.txt
```
- Launch encrypted file scenario.MD5
```
./run.sh scenario.MD5 && cat simulation.txt
```

[subject]: https://github.com/maryana-la/avaj_launcher/blob/master/avaj-launcher.en.pdf
