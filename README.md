# Advent of Code 2023 Solutions

![Advent of Code](https://img.shields.io/badge/Advent%20of%20Code-2023-brightgreen.svg)
![Language](https://img.shields.io/badge/Language-Java-blue.svg)

## Table of Contents

- [About](#about)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [Contributing](#contributing)
- [License](#license)

## About

This repository contains my solutions to the [Advent of Code 2023](https://adventofcode.com/2023) challenges. Advent of Code is an annual event where participants solve programming puzzles for each day of December leading up to Christmas.

## Usage

Code is written in Java and needs [Java jdk](https://www.geeksforgeeks.org/download-and-install-java-development-kit-jdk-on-windows-mac-and-linux/) installed and ready to run.

Option 1:
Once Java jdk is setup, open the project in [VSCode](https://code.visualstudio.com/download). In VSCode navigate to .vscode/launch.json file and change in args which day and which problem you would like to run.
For Example (day 3, part 2):
```
...
  "args": [
      "3", // day number
      "2", // part number
  ],
...
```

Option 2:
Once Java jdk is setup, go to the root of project and open the terminal and run:
```
javac Main.java
javac day[number of day in two digits. e.g. 02 for day 2 or 14 for day 14]\Part[1/2].java
java Main
```


## Folder Structure

- day01: Contains solutions for day 1.
  - Part1.java: Solution to part 1.
  - Part2.java: Solution to part 2.
  - input.txt: Input file of the day.
  - test.txt: Short helpful input file for part 1 and 2.
  - may include some helper .java files.
- day02: Contains solutions for Day 2.
  - ...

## Contributing
Dimnik(dominikpin).

## License

This project is licensed under the [MIT License](https://opensource.org/license/mit/).
