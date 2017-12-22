package fr.istic.bytecodeModificator;

import javassist.bytecode.Opcode;

public class BytecodeElements {

	public static final int IADD_OPERATOR = Opcode.IADD;
	public static final int ISUB_OPERATOR = Opcode.ISUB;
	public static final int IMUL_OPERATOR = Opcode.IMUL;
	public static final int IDIV_OPERATOR = Opcode.IDIV;
	public static final int EQUALS_OPERATOR = Opcode.IFEQ;
	public static final int NOT_EQUALS_OPERATOR = Opcode.IFNE;
	public static final int GREATER_THAN_OPERATOR = Opcode.IFGT;
	public static final int GREATER_OR_EQUALS_OPERATOR = Opcode.IFGE;
	public static final int LESS_THAN_OPERATOR = Opcode.IFLT;
	public static final int LESS_OR_EQUALS_OPERATOR = Opcode.IFLE;
}
