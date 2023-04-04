import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ArrayRunner {
  public static void main(String[] args) {
    // System.out.println(isUnique("Lundesh"));

    // System.out.println(checkPerm("aca","aacb"));

    // Character[] arr = { 'm', 'r', ' ', 'j', 'o', 'h', 'n', ' ', 's', 'm', 'i',
    // 't', 'h', ' ', ' ', ' ', ' ' };
    // System.out.println(Arrays.toString(urlify(arr)));

    // System.out.println(palindromePermutation("abc"));

    // System.out.println(palindromePermutation("tacoc cat"));

    // System.out.println(oneAway("pale","pales"));
    // System.out.println(oneAway("pales","pale"));
    // System.out.println(oneAway("pale","bale"));
    // System.out.println(oneAway("pale","bake"));
    // System.out.println(stringCompression("aabcccccaaa"));

    Integer[][] input = { { 1, 2, 3 }, { 2, 0, 4 }, { 5, 1, 2 } };
    // System.out.println(Arrays.toString(zeroMatrix(input)));
    input = zeroMatrix2(input);
    for (int i = 0; i < input.length; i++) {
    for (int j = 0; j < input[i].length; j++) {
        System.out.print(input[i][j] + " ");
    }
    System.out.println();
}
  }

  static Boolean checkPerm(String a, String b) {
    HashMap<Character, Integer> refMap = new HashMap<>();

    for (Character i : a.toCharArray()) {
      if (!refMap.containsKey(i))
        refMap.put(i, 1);
      else
        refMap.put(i, refMap.get(i) + 1);
    }

    for (Character i : b.toCharArray()) {
      if (!refMap.containsKey(i))
        return false;
      else
        refMap.put(i, refMap.get(i) - 1);
    }

    for (Character i : refMap.keySet()) {
      if (refMap.get(i) > 0)
        return false;
    }

    return true;
  }

  static boolean isUnique(String paraString) {

    HashMap<Character, Boolean> refMap = new HashMap<>();
    for (Character c : paraString.toCharArray()) {
      if (refMap.containsKey(c))
        return false;
      refMap.put(c, true);
    }
    return true;
  }

  static Character[] urlify(Character[] input) {

    Boolean trigger = false;
    Integer start = input.length - 1;
    for (int i = input.length - 1; i >= 0; i--) {
      if (!trigger && input[i] != ' ') {
        trigger = true;
        input[start] = input[i];
        start--;
      } else if (trigger && input[i] != ' ') {
        input[start] = input[i];
        start--;
      } else if (trigger && input[i] == ' ') {
        input[start] = '0';
        input[start - 1] = '2';
        input[start - 2] = '%';
        start = start - 3;
      }

    }

    return input;
  }

  static Boolean palindromePermutation(String input) {
    if (input.equals("") || input.length() == 1)
      return true;
    HashMap<Character, Integer> refMap = new HashMap<>();

    for (Character c : input.toCharArray()) {
      if (c == ' ')
        continue;
      // c = c.toLowerCase();
      if (!refMap.containsKey(c))
        refMap.put(c, 1);
      else
        refMap.put(c, refMap.get(c) + 1);
    }
    Boolean trigger = false;
    for (Character c : refMap.keySet()) {
      if (refMap.get(c) % 2 != 0 && !trigger)
        trigger = true;
      else if (refMap.get(c) % 2 != 0 && trigger)
        return false;
    }
    return true;

  }

  // Assumption: All the strings will be lower case
  static Boolean oneAway(String a, String b) {
    if (Math.abs(a.length() - b.length()) >= 2)
      return false;

    if (a.equals(b))
      return true;
    if (a.length() == 0 || b.length() == 0)
      return true;

    HashMap<Character, Integer> refMap = new HashMap<>();

    for (Character c : a.toCharArray()) {
      if (!refMap.containsKey(c))
        refMap.put(c, 1);
      else
        refMap.put(c, refMap.get(c) + 1);
    }

    // System.out.println(refMap.toString());

    for (Character c : b.toCharArray()) {
      if (refMap.containsKey(c)) {
        // This approach doesn't work
        // if(refMap.get(c)<=0) refMap.remove(c);
        // else refMap.put(c,refMap.get(c)-1);
        refMap.put(c, refMap.get(c) - 1);
        if (refMap.get(c) <= 0)
          refMap.remove(c);
      }
    }
    // System.out.println(refMap.toString());

    Character[] refArray = refMap.keySet().toArray(new Character[0]);
    // System.out.println(Arrays.toString(refArray));

    if (refArray.length > 1)
      return false;
    if (refArray.length == 1 && refMap.get(refArray[0]) > 1)
      return false;

    return true;
  }

  static String stringCompression(String input) {

    StringBuilder sb = new StringBuilder();

    // if(input.length()==0 || input.length()==1) return input;
    // Better way to write this
    if (input.length() < 2)
      return input;

    Integer count = 0;
    Character current = input.charAt(count);

    // System.out.println(input);
    // System.out.println(count);
    // System.out.println(current);

    for (Character c : input.toCharArray()) {
      if (c != current) {
        sb.append(current);
        sb.append(count);
        count = 1;
        current = c;
      } else {
        count++;
      }
    }

    sb.append(current);
    sb.append(count);

    if (input.length() < sb.length())
      return input;
    return sb.toString();
  }

  static Integer[][] zeroMatrix(Integer[][] input) {
    // We'll first do it in O(n) space using sets
    Set<Integer> row = new HashSet<>();
    Set<Integer> column = new HashSet<>();

    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (input[i][j] == 0) {
          row.add(i);
          column.add(j);
        }
      }
    }

    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (row.contains(i) || column.contains(j))
          input[i][j] = 0;
      }
    }  
    return input;
  }



  static Integer[][] zeroMatrix2(Integer[][] input){


    Boolean firstRowZero = false;
    Boolean firstColumnZero = false;


    // Check if zeroes exist in the first row or first column
    for(int i=0;i<input[0].length;i++){
      if(input[0][i]==0) firstRowZero=true;
    }

    for(int i=0;i<input.length;i++){
      if(input[i][0]==0) firstColumnZero=true;
    }


    for(int i=1;i<input.length;i++){
      for(int j=1;j<input[i].length;j++){
        if(input[i][j]==0) {
          input[0][j]=0;
          input[i][0]=0;
        }
      }
    }

    for(int i=1;i<input.length;i++){
      for(int j=1;j<input[i].length;j++){
        if(input[0][j]==0 ||input[i][0]==0) input[i][j]=0;
      }
    }


    if(firstRowZero){
      for(int i=0;i<input[0].length;i++) input[0][i]=0;
    }

     if(firstColumnZero){
      for(int i=0;i<input.length;i++) input[i][0]=0;
    } 
    return input;
  }



  static Boolean isRotation(String s1, String s2){
    if(s1==null || s2==null || s1.length()!=s2.length()) return false;
    
    // Check if s1 is the rotation of s2
    String newString = s1+s1;
    if(newString.contains(s2)) return true;
    return false;
  }

}