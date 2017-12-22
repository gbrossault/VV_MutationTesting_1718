package fr.istic.mutationTarget;

/**
 * Represent the mutation to operate.
 */
public class MutationTarget {

    private String ctClassName;
    private String ctMethodName;

    //The index of the begin place where bytecode is modified.
    private Integer targetIndex;

    private Integer currentInstructions;
    private Integer mutatedInstructions;

    public MutationTarget(String name, String methodName, Integer index, Integer currentInstructions, Integer mutatedInstructions) {
    	this.ctClassName = name;
        this.ctMethodName = methodName;
        this.targetIndex = index;
        this.currentInstructions = currentInstructions;
        this.mutatedInstructions = mutatedInstructions;
    }

    public String getCtClassName() {
		return ctClassName;
	}

	public void setCtClassName(String ctClassName) {
		this.ctClassName = ctClassName;
	}

	public String getCtMethodName() {
		return ctMethodName;
	}

	public void setCtMethodName(String ctMethodName) {
		this.ctMethodName = ctMethodName;
	}

	public Integer getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(Integer targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Integer getCurrentInstructions() {
        return currentInstructions;
    }

    public void setCurrentInstructions(Integer currentInstructions) {
        this.currentInstructions = currentInstructions;
    }

    public Integer getMutatedInstructions() {
        return mutatedInstructions;
    }

    public void setMutatedInstructions(Integer mutatedInstructions) {
        this.mutatedInstructions = mutatedInstructions;
    }
}
