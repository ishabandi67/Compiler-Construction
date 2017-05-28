
import java.io.*;

public class macro_preprocessor {

    public static String MDT[][];
    public static String MNT[][];
    public static String ALA[][];
    public static int mdtc = 0, mntc = 0, ac = 0, index, mdtp, fox;
    public static String current;
    public static BufferedReader ip;
    public static BufferedReader op;
    public static BufferedWriter mf;

    public static void main(String args[]) throws IOException {
        ip = new BufferedReader(new FileReader("Inputmacro.txt"));
        BufferedWriter icw = new BufferedWriter(new FileWriter("ICmacro.txt", true));
        op = new BufferedReader(new FileReader("ICmacro.txt"));
        mf = new BufferedWriter(new FileWriter("OPmacro.txt", true));
        MDT = new String[15][3];
        MNT = new String[15][2];
        ALA = new String[15][4];

        String[] param;
        int len, c;
        long st = System.currentTimeMillis();

        try {
            current = ip.readLine();
            do {
                if (current.trim().equals("MACRO")) {
                    current = ip.readLine();
                    String s1[] = current.split(" ");
                    len = s1.length;
                    switch (len) {
                        case 3:
                            c = 0;
                            MNT[mntc][0] = s1[1];
                            MNT[mntc][1] = Integer.toString(mdtc);
                            MDT[mdtc][0] = s1[0];
                            MDT[mdtc][1] = s1[1];
                            MDT[mdtc][2] = s1[2];
                            ALA[ac][0] = Integer.toString(mntc);
                            ALA[ac][1] = "#" + Integer.toString(c++);
                            ALA[ac][2] = s1[0].substring(1);
                            ac++; 
                            param = s1[2].split(",");
                            
                            for (int i = 0; i < param.length; i++) {
                                ALA[ac][0] = Integer.toString(mntc);
                                ALA[ac][1] = "#" + Integer.toString(c++);
                                int v= param[i].indexOf("=");
                                if (v != -1) {
                                    ALA[ac][2] = param[i].substring(1, v);
                                    ALA[ac++][3] = param[i].substring(v + 1);
                                } else {
                                    ALA[ac++][2] = param[i].substring(1);
                                }
                            }
                            param = null;
                            mdtc++;
                            mntc++;
                            current = ip.readLine();
                            mend(s1[0].substring(1));
                            break;

                        case 2:
                            c = 0;
                            MNT[mntc][0] = s1[0];
                            MNT[mntc][1] = Integer.toString(mdtc);
                            MDT[mdtc][0] = " ";
                            MDT[mdtc][1] = s1[0];
                            MDT[mdtc][2] = s1[1];
                            ALA[ac][0] = Integer.toString(mntc);
                            ALA[ac][1] = "#" + Integer.toString(c++);
                            ALA[ac++][2] = " ";
                            param = s1[1].split(",");
                            for (int i = 0; i < param.length; i++) {
                                ALA[ac][0] = Integer.toString(mntc);
                                ALA[ac][1] = "#" + Integer.toString(c++);
                                int v = param[i].indexOf("=");
                                if (v != -1) {
                                    ALA[ac][2] = param[i].substring(1, v);
                                    ALA[ac++][3] = param[i].substring(v + 1);
                                } else {
                                    ALA[ac++][2] = param[i].substring(1);
                                }

                            }
                            param = null;
                            mntc++;
                            mdtc++;
                            current = ip.readLine();
                            mend(" ");
                            break;
                    }
                } else {
                    icw.write(current);
                    icw.newLine();
                }
                current = ip.readLine();
            } while (!current.equals("END"));
            icw.write(current);
            icw.newLine();
            //pass2();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        icw.close();
        pass2();
        mf.close();
        disp();
        System.out.println("Argument List Array PASS 2");
        for (int i = 0; i < ac; i++) {
            //System.out.print(i + "\t");
            for (int j = 0; j < 4; j++) {
                if (j != 2) {
                    System.out.print(ALA[i][j] + "\t");
                }
            }
            System.out.println();
        }
        long et = System.currentTimeMillis();
        long d = et - st;
        System.out.println("Time in milliseconds: " + d);
    }

    public static void mend(String x) throws IOException {
        int k = 1;
        while (!current.trim().equals("MEND")) {
            String s2[] = current.split(" ");

            if (s2.length == 3) {
                String s3[] = s2[2].split(",");
                String xm = "";
                for (int i = 0; i < s3.length; i++) {
                    if (s3[i].startsWith("&")) {
                        xm = xm + search(s3[i].substring(1)) + ",";

                    } else {
                        xm = xm + s3[i] + ",";
                    }
                }
                xm = xm.substring(0, xm.length() - 1);
                if (k == 1) {
                    MDT[mdtc][0] = search(x);
                    k++;
                } else {
                    MDT[mdtc][0] = s2[0];
                }
                MDT[mdtc][1] = s2[1];
                MDT[mdtc][2] = xm;
            } else if (s2.length == 2) {
                String s3[] = s2[1].split(",");
                String xm = "";
                for (int i = 0; i < s3.length; i++) {
                    if (s3[i].startsWith("&")) {
                        xm = xm + search(s3[i].substring(1)) + ",";
                    } else {
                        xm = xm + s3[i] + ",";
                    }
                }
                xm = xm.substring(0, xm.length() - 1);
                if (k == 1 && !x.equals(" ")) {
                    MDT[mdtc][0] = search(x);
                    k++;
                } else {
                    MDT[mdtc][0] = " ";
                }
                MDT[mdtc][1] = s2[0];
                MDT[mdtc][2] = xm;
            } else {
                MDT[mdtc][0] = " ";
                MDT[mdtc][1] = current;
                MDT[mdtc][2] = " ";
            }
            mdtc++;
            current = ip.readLine();
        }
        MDT[mdtc][0] = " ";
        MDT[mdtc][1] = "MEND";
        MDT[mdtc++][2] = " ";
    }

    public static String search(String s) {
        int flag = 0, i;
        for (i = 0; i < ac; i++) {
            if (s.equals(ALA[i][2])) {
                flag = 1;
                break;
            }
        }
        return ALA[i][1];
    }

    public static void disp() {
        System.out.println("Macro defination table");
        for (int i = 0; i < mdtc; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 3; j++) {

                System.out.print(MDT[i][j] + "\t");

            }
            System.out.println();
        }
        System.out.println("Macro name table");
        for (int i = 0; i < mntc; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 2; j++) {
                System.out.print(MNT[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("Argument List Array");
        for (int i = 0; i < ac; i++) {
            //System.out.print(i + "\t");
            for (int j = 0; j < 3; j++) {
                System.out.print(ALA[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void pass2() throws IOException {
        String param2[];
        current = op.readLine();
        do {
            String s1[] = current.split(" ");
            switch (s1.length) {
                case 1:
                    mf.write(current);
                    mf.newLine();
                    break;
                case 2:
                    if (ismacro(s1[0])) {
                        //int m = 0;
                        mdtp = Integer.parseInt(MNT[index][1]);
                        param2 = s1[1].split(",");
                        // int k = 0;
                        if (s1[1].indexOf("=") == -1) {
                            for (int i = 0; i < param2.length; i++) {
                                for (int j = 0; j < ac; j++) {
                                    if (Integer.parseInt(ALA[j][0]) == index && !ALA[j][1].equals("#0") && ALA[j][3] == null) {
                                        ALA[j][3] = param2[i];
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < param2.length; i++) {
                                for (int j = 0; j < ac; j++) {
                                    String x[] = param2[i].split("=");
                                    //for(int k=0; k< x.length; k++)
                                    if (Integer.parseInt(ALA[j][0]) == index && ALA[j][2].equals(x[0].substring(1))) {
                                        ALA[j][3] = x[1];
                                        x = null;
                                        break;
                                    }
                                }
                            }
                        }
                        mdtp++;
                        param2 = null;
                        while (!MDT[mdtp][1].equals("MEND")) {

                            mf.write(MDT[mdtp][1]);
                            mf.write(" ");
                            String y[] = MDT[mdtp][2].split(",");
                            for (int j = 0; j < y.length; j++) {
                                if (y[j].startsWith("#")) {
                                    for (int i = 0; i < ac; i++) {
                                        if (y[j].equals(ALA[i][1])) {
                                            mf.write(ALA[i][3]);
                                            break;
                                        }
                                    }
                                } else {

                                    mf.write(y[j]);
                                    mf.write(",");
                                }

                            }
                            mdtp++;
                            mf.newLine();
                        }
                    } else {
                        mf.write(current);
                        mf.newLine();
                    }
                    break;
                case 3:
                    if (ismacro(s1[1])) {
                        mdtp = Integer.parseInt(MNT[index][1]);
                        //nt  m = 0;
                        for (int i = 0; i < ac; i++) {
                            if (Integer.parseInt(ALA[i][0]) == index && ALA[i][1].equals("#0")) {
                                ALA[i][3] = s1[0];
                                break;
                            }
                        }

                        param2 = s1[2].split(",");
                         if (s1[2].indexOf("=") == -1) {
                            for (int i = 0; i < param2.length; i++) {
                                for (int j = 0; j < ac; j++) {
                                    if (Integer.parseInt(ALA[j][0]) == index && !ALA[j][1].equals("#0") && ALA[j][3] == null) {
                                        ALA[j][3] = param2[i];
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < param2.length; i++) {
                                for (int j = 0; j < ac; j++) {
                                    String x[] = param2[i].split("=");
                                    //for(int k=0; k< x.length; k++)
                                    if (Integer.parseInt(ALA[j][0]) == index && ALA[j][2].equals(x[0].substring(1))) {
                                        ALA[j][3] = x[1];
                                        x = null;
                                        break;
                                    }
                                }
                            }
                        }
                        mdtp++;
                        param2 = null;

                        while (!MDT[mdtp][1].equals("MEND")) {
                            mf.write(MDT[mdtp][1]);
                            mf.write(" ");
                            String y[] = MDT[mdtp][2].split(",");
                            for (String y1 : y) {
                                if (y1.startsWith("#")) {
                                    for (int i = 0; i < ac; i++) {
                                        if (y1.equals(ALA[i][1]) && Integer.parseInt(ALA[i][0]) == index) {
                                            mf.write(ALA[i][3]);
                                        }
                                    }
                                } else {
                                    mf.write(y1);
                                    mf.write(",");
                                }
                            }
                            mdtp++;
                            mf.newLine();
                        }
                    } else {
                        mf.write(current);
                        mf.newLine();
                    }
                    break;
            }
            current = op.readLine();
        } while (!current.equals("END"));
        mf.write(current);
    }

    public static boolean ismacro(String s) {
        int i;
        boolean flag = false;
        for (i = 0; i < mntc; i++) {
            if (s.equals(MNT[i][0])) {
                flag = true;
                index = i;
                break;
            }
        }
        return flag;
    }
}


/* INPUT FILE: Inputmacro.txt
MACRO
XYZ &A
ST 1,&A
MEND
MACRO
&LABEL MIT &Z,&W=DATA2
LA 1,&Z
A 1,&W
ST 1,&Z
MEND
START
USING *,15
L 2,DATA1
LOOP1 MIT &Z=DATA1
ST 1,DATA2
XYZ DATA1
JNE LOOP1
DATA1 DC F'15'
DATA2 DC F'25'
END
*/

/* Intermediate Code Generated: ICmacro.txt
START
USING *,15
L 2,DATA1
LOOP1 MIT &Z=DATA1
ST 1,DATA2
XYZ DATA1
JNE LOOP1
DATA1 DC F'15'
DATA2 DC F'25'
END
*/

/*Final Output: OPmacro.txt
START
USING *,15
L 2,DATA1
LA 1,DATA1
A 1,DATA2
ST 1,DATA1
ST 1,DATA2
ST 1,DATA1
JNE LOOP1
DATA1 DC F'15'
DATA2 DC F'25'
END
*/
