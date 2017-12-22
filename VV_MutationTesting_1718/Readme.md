New repository for the VV project

### Assignment 3: Mutation Testing

* Replace the boolean expression of a conditional instruction by `false` and, in a second moment by `true`.
* Remove all instructions in the body of a `void` method.
* Replace the body of a boolean method by a single `return true` (or `return false`) instruction.
* **Perform the following operator substitution in the context of arithmetic expressions:**
* Remove an arbitrary part of a boolean expression. For example: `a && !b` could become `a` or `!b`
* Replace one method call by a predefined value.
* In the presence of an integer constant `x`, replace it by `x+1`, `x-1`, `2*x` and `x/2`.  
* **Replace a comparison operator by another given the following possible substitutions:**
* Replace the right part of an assignment by a predefined value.

You are free to include all the extra operators you want.
A mutation tool can perform the transformations at bytecode level or static source code. This decision is left to you.
[PIT](http://pitest.org/) is a state of the art mutation testing tool for Java programs. In its web site you can find useful information about Mutation Testing and an extended list of possible mutation operators.
Your tool should provide a way to configure the mutation operators to be used in the analysis.


### Remarks
The assignments described above contain the minimum functionalities excpected, but you are encouraged to add any  functionlities that you find valuable. 
A strong **emphasis** on rigourous testing and other good development practices is expected from you and will be taken into account for the grade.
For the sake of simplicity we expect you to build your project with Maven and junit and you can assume that the same requirements apply for the projects to be analyzed by you.

## Requirements

### Minimal requirements

You are expected to create a public git repository containing at least the following items :
 * a **maven project** containing your code and a **strong junit test suite**
 * a **readme** file specifying how to build your code, how to use the tool on another project, and a section containing the list of targets on which you have tested your tool succesfully.
 * a **report** describing the functionalities of your tool, what problem does it solve, and both the relevence of the solution and its accuracy. A section about how you assessed this accuracy and test your tool overall is expected. You should also mention the problems you have encontered during the developpment and how you solved them. Guidelines concerning this report [can be found here](http://people.rennes.inria.fr/Benoit.Baudry/wp-content/uploads/2013/09/Instructions_for_report.pdf)
