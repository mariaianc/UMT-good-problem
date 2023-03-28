import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int MinimumChange = 0;
        System.out.println("Introduce the password: ");
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();

        int upperCase = 0,lowerCase = 0, digit = 0;//assume that in the password are not any upper/lower case or any digit

        for(int i = 0; i < password.length(); i++) {

            //if there exist a lower/upper case or a digit, mark it as found (=1)
            if (Character.isLowerCase(password.charAt(i))) {
                lowerCase = 1;
            } else if (Character.isUpperCase(password.charAt(i))) {
                upperCase = 1;
            } else if (Character.isDigit(password.charAt(i))) {
                digit = 1;
            }
        }

            //here I compute the length of every sequence of repeating characters and the number of changes that have
            // to be done are length of the sequence/3
            char previous_char= password.charAt(0);//this is the first character in the sequence
            int lengthOfSequence = 1; //in this variable we put the length of a sequence of repeating characters
            int modificationSequence = 0;//assume that it doesn't exist any sequence of repeating characters,
                                         // here I will put the number of changes that need to be done
            for(int i = 1; i<password.length();i++)
            {
                //each time I compare the previous character with the character at the actual position to see if they repeat
                if(password.charAt(i)==previous_char) {
                    lengthOfSequence++;
                    previous_char = password.charAt(i);
                }

                else
                {
                    previous_char = password.charAt(i);
                    modificationSequence = modificationSequence + lengthOfSequence / 3; //=how many characters need replacement
                    lengthOfSequence = 1;//reset the length of repeating characters
                }
        }
        //if the sequence is the last part of the password
        if(lengthOfSequence>=3) modificationSequence = modificationSequence+lengthOfSequence/3;

        int cont2 = 0;
            //if 1 of this characters doesn't exist in the password we first need to change them and after to modify
            // the sequence of repeating characters if is needed
            if(lowerCase == 0) cont2++;
            if(upperCase == 0) cont2++;
            if(digit == 0) cont2++;
            modificationSequence-=cont2;

        MinimumChange+=modificationSequence;


        int passwordInitialLength = password.length();
        int cont = 0;

        //case when the password have less than 6 digits
        if (password.length() < 6) {
            if(lowerCase == 0) cont++;
            if(upperCase == 0) cont++;
            if(digit == 0) cont++;
            passwordInitialLength+=cont;

            //first replace some characters if is needed with upper/lower case or digit (=cont)
            //then add characters untill it will have the minimum length
            MinimumChange += cont + 6 - passwordInitialLength;//
        }

        //if the password has more than 20 characters, I have to subtract characters until the length will be 20
        else if (password.length()>20)
        {
            if(lowerCase == 0) cont++;
            if(upperCase == 0) cont++;
            if(digit == 0) cont++;

            //first replace some characters if is needed with upper/lower case or digit (=cont)
            //then take out the unecessary characters
            MinimumChange+=password.length()-20+cont;
        }

        //if the password has an appropriate length
        else if(password.length()>=6 && password.length()<=20)
        {

            //replace some characters if is needed with upper/lower case or digit (=cont)
            if(lowerCase == 0) cont++;
            if(upperCase == 0) cont++;
            if(digit == 0) cont++;

            MinimumChange+=cont;
        }
        System.out.println(MinimumChange);
    }
}