# Machine learning - Theory and algorithms (MLTA)

Project made for MLTA lecture for ED-SPIM at the Université de Technologie de Belfort-Montbéliard (UTBM)

## Project purpose

The purpose of the project is to illustrate two linear regression methods frequently used in machine learning algorithms:
* Linear regression using normal equations
* Locally weighted linear regression (LWLR)

## Compilation

The project is made in Java 8. It can be built with Maven using the command:

```
mvn package
```

## Results

Following figures illustrate the results of application of the linear regression algorithms on Leland dataset
(located here in src/main/resources).

The red line shows the results of applying normal equations method. The orange one shows the result of applying LWLR.
LWLR takes two parameters x (central value for weights) and sigma (a factor adjusting the weights for values according
to their distance from x: if sigma is small, weight decrease really quickly with distance to x, if sigma is big,
weight decrease slowly with distance to x).