package MatrixProcessing;

import java.util.Random;
import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {
        MatrixMenu matrixMenu = new MatrixMenu();
        matrixMenu.runMenu();
    }
}

class MatrixOperations {
	public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public int[][] matrixOne;
    public int[][] matrixTwo;
    public int[][] matrixThree;
    public double[][] inverse;
    
    private void matrixPrint(double[][] matrix) {
        System.out.println("The result matrix is: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private int[][] userMatrix() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows and columns separated by a space to create a matrix");
        String numbersnRows = scanner.nextLine();

        while (!numbersnRows.matches("^[0-9\\s]+$")) {
            System.out.println("Please enter only numeric values separated by spaces. Try again:");
            numbersnRows = scanner.nextLine();
        }

        String[] partsMatrix = numbersnRows.split(" ");
        int colMatrix = Integer.parseInt(partsMatrix[0]);
        int rowMatrix = Integer.parseInt(partsMatrix[1]);
        int[][] matrix = new int[colMatrix][rowMatrix];

        for (int i = 0; i < colMatrix; i++) {
            for (int j = 0; j < rowMatrix; j++) {
                matrix[i][j] = random.nextInt(10);
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.println();
        }
        return matrix;
    }
    
    private void matrixPrint(int[][] matrix){
        System.out.println("The result matrix is: ");
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void matrixAddition() {
        matrixOne = userMatrix();
        matrixTwo = userMatrix();

        if (matrixOne.length != matrixTwo.length || matrixOne[0].length != matrixTwo[0].length) {
            System.out.println("Matrices cannot be added together. Matrices must have the same dimensions.");
            return;
        }

        System.out.println("Matrix addition:");
        matrixThree = new int[matrixOne.length][matrixOne[0].length];

        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                matrixThree[i][j] = matrixOne[i][j] + matrixTwo[i][j];
            }
        }
        matrixPrint(matrixThree);
    }
	public void matrixMultiplicationConstant() {
		Scanner scanner = new Scanner(System.in);
		matrixOne = userMatrix();
		System.out.println("Enter a constant");
		int constant = 0;
		boolean validInput = false; 
		while (!validInput) {
		    if (scanner.hasNextInt()) {
		        constant = scanner.nextInt();
		        validInput = true; 
		    } else {
		        System.out.println("Please enter an integer value. Try again:");
		        scanner.next();
		    }
		}
		scanner.nextLine();
		System.out.println("Multiply matrix by a constant");
        matrixThree = new int[matrixOne.length][matrixOne[0].length];

        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                matrixThree[i][j] = matrixOne[i][j] * constant;
            }
        }
        matrixPrint(matrixThree);
	}
	public void matrixMultiplication() {
	    matrixOne = userMatrix();
	    matrixTwo = userMatrix();

	    if (matrixOne[0].length != matrixTwo.length) {
	        System.out.println("Matrix multiplication is not possible");
	        return;
	    }

	    System.out.println("Matrix Multiplication:");
	    matrixThree = new int[matrixOne.length][matrixTwo[0].length];

	    for (int i = 0; i < matrixOne.length; i++) {
	        for (int j = 0; j < matrixTwo[0].length; j++) {
	            matrixThree[i][j] = 0;
	            for (int k = 0; k < matrixOne[0].length; k++) {
	                matrixThree[i][j] += matrixOne[i][k] * matrixTwo[k][j];
	            }
	        }
	    }
	    matrixPrint(matrixThree);
	}
	public void horizontalLineTranspose() {
        matrixOne = userMatrix();
        System.out.println("Transpose by horizontal line");
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                System.out.print(" " + matrixOne[matrixOne.length - 1 - i][j] + " ");
            }
            System.out.println();
        }
    }
	public void verticalLineTranspose() {
        matrixOne = userMatrix();
        System.out.println("Transpose by vertical line");
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                System.out.print(" " + matrixOne[i][matrixOne[0].length - 1 - j] + " ");
            }
            System.out.println();
        }
    }

    public void mainDiagonalTranspose() {
        matrixOne = userMatrix();
        System.out.println("Transpose by main diagonal");
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                System.out.print(" " + matrixOne[j][i] + " ");
            }
            System.out.println();
        }
    }
    
	public double calculateDeterminant(int[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;
        for (int i = 0; i < n; i++) {
            int[][] minor = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k < i) {
                        minor[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        minor[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            det += matrix[0][i] * Math.pow(-1, i) * calculateDeterminant(minor);
        }
        return det;
    }
	
	public void determinant() {
        matrixOne = userMatrix();
        if (matrixOne.length == matrixOne[0].length) {
            double determinant = calculateDeterminant(matrixOne);
            System.out.println("Determinant: " + determinant);
        } else {
            System.out.println("The matrix is not square, so its determinant cannot be calculated.");
        }
    }
	
	public void inverseMatrix() {
        matrixOne = userMatrix();
        double[][] doubleMatrix = new double[matrixOne.length][matrixOne[0].length];
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                doubleMatrix[i][j] = matrixOne[i][j];
            }
        }

        inverse = calculateInverse(doubleMatrix);

        if (inverse == null) {
            System.out.println("The matrix is singular, and its inverse cannot be calculated.");
        } else {
            System.out.println("Inverse Matrix:");
            matrixPrint(inverse);
        }
    }
	
	public void sideDiagonalTranspose() {
	    matrixOne = userMatrix();
	    System.out.println("Transpose by side diagonal");
	    for (int i = matrixOne.length - 1; i >= 0; i--) {
	        for (int j = matrixOne[0].length - 1; j >= 0; j--) {
	            System.out.print(" " + matrixOne[j][i] + " ");
	        }
	        System.out.println();
	    }
	}
	
    public double[][] calculateInverse(double[][] matrix) {
        int size = matrix.length;
        double[][] augmentedMatrix = new double[size][2 * size];

        // Create an extended matrix by adding a unit matrix on the right side of the matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
                augmentedMatrix[i][j + size] = (i == j) ? 1 : 0;
            }
        }

        // Conversion to upper-triangular form using Gauss method
        for (int i = 0; i < size; i++) {
        	// Search for the maximum element in the current column
            double maxVal = Math.abs(augmentedMatrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < size; k++) {
                if (Math.abs(augmentedMatrix[k][i]) > maxVal) {
                    maxVal = Math.abs(augmentedMatrix[k][i]);
                    maxRow = k;
                }
            }

            // Rearrange the lines, if necessary
            if (maxRow != i) {
                for (int k = i; k < 2 * size; k++) {
                    double temp = augmentedMatrix[i][k];
                    augmentedMatrix[i][k] = augmentedMatrix[maxRow][k];
                    augmentedMatrix[maxRow][k] = temp;
                }
            }

            // Division of the current line by the main element
            double pivot = augmentedMatrix[i][i];
            if (pivot == 0.0) {
                return null; // The matrix is singular
            }

            for (int k = 0; k < 2 * size; k++) {
                augmentedMatrix[i][k] /= pivot;
            }

            // Zeroing the bottom elements in the column
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];
                    for (int k = 0; k < 2 * size; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }
        }

        // Extract the inverse matrix from the right part of the extended matrix
        double[][] inverse = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverse[i][j] = augmentedMatrix[i][j + size];
            }
        }

        return inverse;
    }
}

class MatrixMenu {
    private MatrixOperations matrixOperations;
    private boolean inTransposeMenu; // Flag to keep track of being in the transpose menu

    public MatrixMenu() {
        this.matrixOperations = new MatrixOperations();
        this.inTransposeMenu = false; // Initially not in the transpose menu
    }

    public void runMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (!inTransposeMenu) {
                    System.out.println("####|MatrixProcessing|####"
                            + "\n1 - [Matrix Addition]"
                            + "\n2 - [Multiply matrix by a constant]"
                            + "\n3 - [Matrix Multiplication]"
                            + "\n4 - [Transpose matrix]"
                            + "\n5 - [Calculate a determinant]"
                            + "\n6 - [Inverse matrix]"
                            + "\n7 - [EXIT]");
                } else {
                    System.out.println("####|TransposeMenu|####"
                            + "\n1 - [Transpose by main diagonal]"
                            + "\n2 - [Transpose by vertical line]"
                            + "\n3 - [Transpose by horizontal line]"
                            + "\n4 - [Transpose by side line]"
                            + "\n5 - [Back to main menu]");
                }

                if (scanner.hasNextInt()) {
                    int userChoice = scanner.nextInt();
                    if (!inTransposeMenu) {
                        processUserChoice(userChoice);
                    } else {
                        processTransposeChoice(userChoice);
                    }
                } else {
                    System.out.println(MatrixOperations.ANSI_RED + "Invalid input!" + MatrixOperations.ANSI_RESET);
                    scanner.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processUserChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                matrixOperations.matrixAddition();
                break;
            case 2:
                matrixOperations.matrixMultiplicationConstant();
                break;
            case 3:
                matrixOperations.matrixMultiplication();
                break;
            case 4:
                inTransposeMenu = true; // Войти в меню транспонирования
                break;
            case 5:
                matrixOperations.determinant();
                break;
            case 6:
                matrixOperations.inverseMatrix();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println(MatrixOperations.ANSI_RED + "Error! Incorrect input." + MatrixOperations.ANSI_RESET);
                break;
        }
    }

    private void processTransposeChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                matrixOperations.mainDiagonalTranspose();
                break;
            case 2:
                matrixOperations.verticalLineTranspose();
                break;
            case 3:
                matrixOperations.horizontalLineTranspose();
                break;
            case 4:
                matrixOperations.sideDiagonalTranspose();
                break;
            case 5:
                inTransposeMenu = false; // Exit the transpose menu
                break;
            default:
                System.out.println(MatrixOperations.ANSI_RED + "Error! Incorrect input." + MatrixOperations.ANSI_RESET);
                break;
        }
    }
}
