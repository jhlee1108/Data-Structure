package fibonacci;

import java.math.BigInteger;

public class FibonacciSequence {
	private BigInteger[] fiboArray;
	private BigInteger[][] matrix;
	
	public BigInteger fibonacciRecursion(int n){
		if(n < 2){
			return new BigInteger(n + "");
		}
		
		else{
			return fibonacciRecursion(n - 1).add(fibonacciRecursion(n - 2));
		}
	}
	
	public BigInteger fibonacciArray(int n){
		if(n < 2){
			fiboArray[n] = new BigInteger(n + "");
			return fiboArray[n];
		}
		
		else{
			fiboArray[n] =  fiboArray[n - 1].add(fiboArray[n - 2]);
			return fiboArray[n];
		}
	}
	
	public BigInteger fibonacciRecursiveSquaring(int n){
		if(n < 2){
			return new BigInteger(n + "");
		}
		
		else{
			return powMatrix(n)[0][1];
		}
	}
	
	private BigInteger[][] powMatrix(int n) {
		if(n == 1){
			return matrix;
		}
		
		else{
			if(n % 2 == 0){
				return multMatrix(powMatrix(n / 2), powMatrix(n / 2));
			}
			
			else{
				return multMatrix(multMatrix(powMatrix((n - 1) / 2), powMatrix((n - 1) / 2)), matrix);
			}
		}
	}

	private BigInteger[][] multMatrix(BigInteger[][] a, BigInteger[][] b) {
		BigInteger[][] resultMatrix = new BigInteger[2][2];
		resultMatrix[0][0] = (a[0][0].multiply(b[0][0])).add((a[0][1].multiply(b[1][0])));
		resultMatrix[0][1] = (a[0][0].multiply(b[0][1])).add((a[0][1].multiply(b[1][1])));
		resultMatrix[1][0] = (a[1][0].multiply(b[0][0])).add((a[1][1].multiply(b[1][0])));
		resultMatrix[1][1] = (a[1][0].multiply(b[0][1])).add((a[0][1].multiply(b[1][1])));
		
		return resultMatrix;
	}

	public void startFibonacciRecursion(int max){
		long start, end;
		
		for(int i = 0; i <= max; i++){
			start = System.currentTimeMillis();
			BigInteger fibo = fibonacciRecursion(i);
			end = System.currentTimeMillis();
			System.out.println("f<" + i + "> = " + fibo + " " + (end - start) + "sec");
		}
	}
	
	public void startFibonacciArray(int max){
		long start, end;
		fiboArray = new BigInteger[max];
		
		for(int i = 0; i < max; i++){
			start = System.currentTimeMillis();
			BigInteger fibo = fibonacciArray(i);
			end = System.currentTimeMillis();
			System.out.println("f<" + i + "> = " + fibo + " " + (end - start) + "sec");
		}
	}
	
	public void startFibonacciRecursiveSquaring(int max){
		long start, end;
		matrix = new BigInteger[2][2];
		matrix[0][0] = new BigInteger("1");
		matrix[0][1] = new BigInteger("1");
		matrix[1][0] = new BigInteger("1");
		matrix[1][1] = new BigInteger("0");
		
		for(int i = 0; i < max; i++){
			start = System.currentTimeMillis();
			BigInteger fibo = fibonacciRecursiveSquaring(i);
			end = System.currentTimeMillis();
			System.out.println("f<" + i + "> = " + fibo + " " + (end - start) + "sec");
		}
	}
}
