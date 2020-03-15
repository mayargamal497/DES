/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gen_desaes_key;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Gen_DESAES_key {

    public static Scanner s = new Scanner(System.in);
    public static ArrayList<String> Fkey = new ArrayList<String>();
    public static ArrayList<Byte> Lkey = new ArrayList<Byte>();
    public static String str = "11122211";
    public static String BigMayar;

    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            InvalidKeyException,
            BadPaddingException,
            UnsupportedEncodingException,
            InvalidAlgorithmParameterException {

        // String str = s.next();
        int n = str.length();
        byte[] key = str.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "DES");
        //   System.err.println("The key value is : " + secretKeySpec);

        Cipher cipher = Cipher.getInstance("DES/CTR/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[8]));
        byte[] data1 = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
        byte[] data2 = "zyxwvutsrqponmlkjihgfedcba".getBytes("UTF-8");
        byte[] data3 = "01234567890123456789012345".getBytes("UTF-8");

        byte[] cipherText1 = cipher.update(data1);
        byte[] cipherText2 = cipher.update(data2);
        byte[] cipherText3 = cipher.doFinal(data3);
        System.err.println("First Cipher text and its Plain text: ");
        System.out.println(new String(cipherText1) + "\n" + new String(cipherText2) + "\n" + new String(cipherText3));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[8]));
        byte[] decryptText1 = cipher.update(cipherText1);
        byte[] decryptText2 = cipher.update(cipherText2);
        byte[] decryptText3 = cipher.doFinal(cipherText3);
     //   System.err.println("Plain text: ");
        System.out.println(new String(decryptText1) + "\n" + new String(decryptText2) + "\n" + new String(decryptText3));

        outerloop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        for (int m = 0; m < 3; m++) {
                            for (int e = 0; e < 3; e++) {
                                for (int o = 0; o < 3; o++) {
                                    for (int p = 0; p < 3; p++) {
                                        String Mayar = Integer.toString(i);
                                        String Mayar2 = Integer.toString(j);
                                        String Mayar3 = Integer.toString(k);
                                        String Mayar4 = Integer.toString(l);
                                        String Mayar5 = Integer.toString(m);
                                        String Mayar6 = Integer.toString(e);
                                        String Mayar7 = Integer.toString(o);
                                        String Mayar8 = Integer.toString(p);
                                        BigMayar = Mayar + Mayar2 + Mayar3 + Mayar4 + Mayar5 + Mayar6 + Mayar7 + Mayar8;
                                        //   System.err.println(""+BigMayar);

                                        int Size = BigMayar.length();

                                        if (BigMayar.equalsIgnoreCase(str)) {

                                            byte[] SizeKey = BigMayar.getBytes();
                                            SecretKeySpec secretKeySpec2 = new SecretKeySpec(SizeKey, "DES");
                                            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec2, new IvParameterSpec(new byte[8]));
                                            byte[] data11 = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
                                            byte[] data22 = "zyxwvutsrqponmlkjihgfedcba".getBytes("UTF-8");
                                            byte[] data33 = "01234567890123456789012345".getBytes("UTF-8");
                                            byte[] cipherText11 = cipher.update(data1);
                                            byte[] cipherText22 = cipher.update(data2);
                                            byte[] cipherText33 = cipher.doFinal(data3);
                                            System.err.println("Second Cipher text: ");
                                            System.out.println(new String(cipherText11) + "\n" + new String(cipherText22) + "\n" + new String(cipherText33));
                                            if (BigMayar != str) {
                                                System.err.println("Found target key: " + BigMayar);
                                                return;
                                            }

                                        }
                                    //  System.out.println(Mayar + Mayar2 + Mayar3 + Mayar4 + Mayar5 + Mayar6 + Mayar7 + Mayar8);


                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private void permute(String str, int l, int r) {
        if (l == r) {
            // System.out.println("" + str);
            Fkey.add(str);

        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

}
