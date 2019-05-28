import java.util.Scanner;

import static alg.ArrayUtils.reverse;
import static java.lang.System.*;


public class MirrorInput
{

  public static void main(String[] args)
  {
	try (Scanner sc = new Scanner(in))
	{
	  String line;
	  while (!(line = sc.nextLine()).isEmpty())
	  {
		Character[] chars = toCharacterArray(line);
		reverse(chars);
		String s = toString(chars);
		out.println(s);
	  }
	}
  }


  private static String toString(Character[] chars)
  {
	char[] array = new char[chars.length];
	for (int i = 0; i < chars.length; i++)
	{
	  array[i] = chars[i];
	}

	return String.valueOf(array);
  }


  private static Character[] toCharacterArray(String s)
  {
	if (s == null)
	  return null;

	char[] chars = s.toCharArray();

	Character[] array = new Character[chars.length];
	for (int i = 0; i < chars.length; i++)
	{
	  array[i] = chars[i];
	}

	return array;
  }

}