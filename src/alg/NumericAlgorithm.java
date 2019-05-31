package alg;


import java.math.BigInteger;

import static java.lang.System.out;
import static java.math.BigInteger.valueOf;


public final class NumericAlgorithm
{

  public static BigInteger pow(BigInteger num, int n)
  {
	if (n == 0)
	  return valueOf(1);

	if (n % 2 == 0)
	{
	  BigInteger p = pow(num, n / 2);
	  p = p.multiply(p);
	  return p;
	}
	else
	{
	  BigInteger p = pow(num, n - 1);
	  p = p.multiply(num);
	  return p;
	}
  }


  public static void main(String[] args)
  {
	BigInteger num = valueOf(2);
	BigInteger pow = pow(num, 88);
	out.println(pow);
  }


}