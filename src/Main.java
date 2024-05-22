import java.sql.*;
import java.util.*;
//package pack;
//import java.sql.*;
//import java.util.*;

//public

class Date {
    private int date;
    private int month;
    private int year;

    public void setyear(int year) {
        this.year = year;
    }

    public boolean setmonth(int month) {
        boolean r = true;
        if (month > 0 && month < 13) {
            this.month = month;
            r = false;
        } else {
            System.out.println("* month should be between 1 and 12 *");

        }
        return r;
    }

    public boolean setdate(int date) {
        boolean r = true;
        if (this.month != 4 && this.month != 6 && this.month != 9 && this.month != 11 && this.month != 2) {
            if (date > 0 && date < 32) {
                this.date = date;
                r = false;
            } else {
                System.out.println("**date should be between 1and31 **");
                r = true;
            }
        } else if (this.month != 2) {
            if (date > 0 && date < 31) {
                this.date = date;
                r = false;
            } else {
                System.out.println("**date should be between 1and30 **");
                r = true;
            }
        } else if (this.month == 2) {
            if (date > 0 && date < 29) {
                this.date = date;
                r = false;
            } else {
                System.out.println("**date should be between 1and28 **");
                r = true;
            }
        }
        return r;
    }

    public int getdate() {
        return date;

    }

    public int getmonth() {
        return month;

    }

    public int getyear() {
        return year;

    }

    public String date_of_birth() {
        return this.getdate() + "-" + this.getmonth() + "-" + this.getyear();
    }
}

interface ProjInterface {
    public static void PrintMessage() {
    }

}

class Account {

    private String firstname;
    private String password;
    private String lastname;
    private Date DOB;
    private String email;
    private String phoneno;
    private String address;

    Scanner sin = new Scanner(System.in);
    Scanner sin2 = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/OopProject";
    String uname = "root";
    String pass = "harshi@25";

    // Connection c1 = DriverManager.getConnection(url, uname, pass);

    public Account() {
    }

    public void setfname(String v1) {
        this.firstname = v1;
    }

    public void setlname(String v2) {
        this.lastname = v2;
    }

    public void setpassword(String v3) {
        this.password = v3;
    }

    public void setmail(String v4) {
        this.email = v4;
    }

    public void setphno(String v5) {
        this.phoneno = v5;
    }

    public void setdob(Date v6) {
        this.DOB = v6;
    }

    public String getpassword() {
        return password;
    }

    public String getfname() {

        return firstname;
    }

    public String getlname() {
        return lastname;
    }

    public String getmail() {
        return email;
    }

    public String getphno() {
        return phoneno;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getaddress() {
        return this.address;
    }

    public void create_account() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/OopP                      roject";
        String uname = "root";
        String pass = "harshi@25";

        Connection c1 = DriverManager.getConnection(url, uname, pass);

        System.out.println("Enter your first name ");
        String fn1 = sin.nextLine();
        System.out.println("Enter your last name ");
        String ln1 = sin.nextLine();
        System.out.println("Enter your email  ");
        String em = sin.nextLine();
        System.out.println("Enter your address");
        String add = sin.nextLine();

        System.out.println("Enter your phoneno ");
        String ph = sin.nextLine();

        System.out.println("Enetr your date of birth ");
        System.out.println("Format : YYYY MM DD ");
        int d1 = sin2.nextInt();
        int m1 = sin2.nextInt();
        int y1 = sin2.nextInt();

        System.out.println("Set your password ");
        String p = sin.nextLine();

        Date dob = new Date();
        dob.setdate(d1);
        dob.setmonth(m1);
        dob.setyear(y1);
        String date = dob.date_of_birth();
        this.setfname(fn1);
        this.setlname(ln1);
        this.setaddress(add);
        this.setdob(dob);
        this.setmail(em);
        this.setphno(ph);
        this.setpassword(p);

        String query = "insert into Account values(?,?,?,?,?,?,?)";

        PreparedStatement ps = c1.prepareStatement(query);

        ps.setString(1, ph);
        ps.setString(2, fn1);
        ps.setString(3, ln1);
        ps.setString(4, em);
        ps.setString(5, date);
        ps.setString(6, add);
        ps.setString(7, p);

        int count = ps.executeUpdate();

        ps.close();
        c1.close();

    }

    public void login_account() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/OopProject";
        String uname = "root";
        String pass = "harshi@25";

        try {

            Connection con = DriverManager.getConnection(url, uname, pass);

            System.out.println(" Enter your phone number  ");
            String ph = sin.nextLine();
            System.out.println(" Enter your password");// check password in the database if password matches with
            // the
            // fname then procced
            String passcode = sin.nextLine();

            String query = "select * from Account where PhoneNumber = '" + ph + "' and passcode = '" + passcode
                    + "'  ";

            Statement s1 = con.createStatement();
            ResultSet rs = s1.executeQuery(query);

            if (rs.next()) {
                System.out.println("Successfully logged in.");
                System.out.println("Welcome ");
            } else
                System.out.println(
                        "Either the entered phone number or password is incorrect. Please try again. If you don't have an account please create a new account ");

            s1.close();
            con.close();

        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }

    }

    public void Edit() throws SQLException {
        System.out.println(" Enter 1 to edit first name ;  2 to edit last name ");
        System.out.println("  3 to edit password ;  4 to edit DOB ");
        System.out.println("  5 to edit email ; -1 to exit");

        String url = "jdbc:mysql://localhost:3306/OopProject";
        String uname = "root";
        String pass = "harshi@25";

        Connection con = DriverManager.getConnection(url, uname, pass);

        int ch = sin.nextInt();

        switch (ch) {

            case 1:
                System.out.println("Enter your new first name: ");
                String nfn = sin2.nextLine();
                this.setfname(nfn);
                String query = "UPDATE Account SET FirstName = '" + nfn + "' where PhoneNumber = '" + this.getphno()
                        + "' ";
                Statement s1 = con.createStatement();
                int count1 = s1.executeUpdate(query);
                break;

            case 2:
                System.out.println("Enter your new Last Name: ");
                String nln = sin.nextLine();
                this.setlname(nln);
                String query2 = "UPDATE Account SET LastName = '" + nln + "' where PhoneNumber = '" + this.getphno()
                        + "' ";
                Statement s2 = con.createStatement();
                int count2 = s2.executeUpdate(query2);
                break;

            case 3:
                System.out.println("Enter your new  Passcode: ");
                String newpass = sin.nextLine();
                this.setpassword(newpass);
                String query3 = "UPDATE Account SET Passcode = '" + newpass + "' where PhoneNumber = '"
                        + this.getphno()
                        + "' ";
                Statement s3 = con.createStatement();
                int count3 = s3.executeUpdate(query3);
                break;

            case 4:
                System.out.println("Enter your new Date of birth :");
                System.out.println("Formant : YYYY MM DD");
                int y1 = sin2.nextInt();
                int m1 = sin2.nextInt();
                int d1 = sin2.nextInt();
                Date dob = new Date();
                dob.setdate(d1);
                dob.setmonth(m1);
                dob.setyear(y1);
                String date = dob.date_of_birth();
                System.out.println("New DOB is: " + date);
                String query4 = "UPDATE Account SET DOB = '" + date + "' where PhoneNumber = '" + this.getphno()
                        + "' ";
                Statement s4 = con.createStatement();
                int count4 = s4.executeUpdate(query4);
                break;

            case 5:
                System.out.println("Enter your new Email");
                String ne = sin.nextLine();
                this.setmail(ne);
                String query5 = "UPDATE Account SET Email = '" + ne + "' where PhoneNumber = '" + this.getphno()
                        + "' ";
                Statement s5 = con.createStatement();
                int count5 = s5.executeUpdate(query5);
                break;

            case -1:
                System.out.println("No changes made");
                break;

            default:
                System.out.println("Enter a valid number.");

        }
    }

    public void getdetails() {
        System.out.println("First Name    :" + this.getfname());
        System.out.println("Last  Name    :" + this.getlname());
        System.out.println("Last  Name    :" + this.getpassword());
        // System.out.println("Date of Birth :"+this.DOB.date_of_birth());
        System.out.println("Email         :" + this.getmail());
        System.out.println("Phone number  :" + this.getphno());
    }
}

class Menu extends Account implements ProjInterface {

    public static void PrintMessage() {
        System.out.println("You are currently viewing menu");
    }

    public int SelectMenu() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/OopProject";
        String uname = "root";
        String pass = "harshi@25";

        Connection c1 = DriverManager.getConnection(url, uname, pass);

        Scanner in = new Scanner(System.in);

        System.out.println(
                "Select the destination :\n 1 for HYDERABAD\n 2 for BANGLORE\n 3 for VIZAG\n 4 for CHENNAI\n 5 for VIJAYAWADA\n");

        int temp = in.nextInt();
        in.close();
        switch (temp) {
            case 1:
                String q1 = "select * from HYDmenu";
                Statement s1 = c1.createStatement();
                ResultSet r1 = s1.executeQuery(q1);
                while (r1.next()) {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.printf("%2d        %-28s         %12s\n", r1.getInt(1), r1.getString(2),
                            r1.getString(3));

                }
                break;

            case 2:
                String q2 = "select * from BANGLOREmenu";
                Statement s2 = c1.createStatement();
                ResultSet r2 = s2.executeQuery(q2);
                while (r2.next()) {
                    System.out.println("--------------------------------------------------------------------");

                    System.out.printf("%2d        %-28s         %12s\n", r2.getInt(1), r2.getString(2),
                            r2.getString(3));
                }
                break;

            case 3:
                String q3 = "select * from VIZAGmenu";
                Statement s3 = c1.createStatement();
                ResultSet r3 = s3.executeQuery(q3);
                while (r3.next()) {
                    System.out.println("--------------------------------------------------------------------");

                    System.out.printf("%2d        %-28s         %12s\n", r3.getInt(1), r3.getString(2),
                            r3.getString(3));
                }
                break;

            case 4:
                String q4 = "select * from CHENNAImenu";
                Statement s4 = c1.createStatement();
                ResultSet r4 = s4.executeQuery(q4);
                while (r4.next()) {
                    System.out.println("--------------------------------------------------------------------");

                    System.out.printf("%2d        %-28s         %12s\n", r4.getInt(1), r4.getString(2),
                            r4.getString(3));
                }
                break;

            case 5:
                String q5 = "select * from VIJAYAWADAmenu";
                Statement s5 = c1.createStatement();
                ResultSet r5 = s5.executeQuery(q5);
                while (r5.next()) {
                    System.out.println("--------------------------------------------------------------------");

                    System.out.printf("%2d        %-28s         %12s\n", r5.getInt(1), r5.getString(2),
                            r5.getString(3));
                }
                break;
        }
        return temp;
    }
}

class Cart extends Menu implements ProjInterface {
    public static void PrintMessage() {
        System.out.println("You are currently viewing cart");
    }

    public void AddToCart() throws SQLException {

        int t = this.SelectMenu();
        ArrayList<Integer> items = new ArrayList<Integer>(3);
        // ArrayList<Integer> price = new ArrayList<Integer>(3);

        Scanner in = new Scanner(System.in);

        switch (t) {
            case 1:
                System.out.println(
                        "Select the items from menu by using their id. Enter -1 if you are done with selecting items.");
                int c = in.nextInt();
                while (c != -1) {
                    items.add(c);
                    c = in.nextInt();
                }
                System.out.println("Your bill is ");

                System.out.println("Enter 1 if you want to delete any selected items.");
                int c2 = in.nextInt();
                if (c2 == 1) {

                }

        }
    }
}

public class Main {
    public static void main(String[] args) throws SQLException {

        Account a = new Account();
        Menu a2 = new Menu();
        Scanner w = new Scanner(System.in);
        System.out.println("Enter 1 to create acccount or 2 to login ");
        int ch = w.nextInt();
        if (ch == 1) {
            a.create_account();
        }
        if (ch == 2) {
            a.login_account();
        }

        System.out.println("Mail :" + a.getmail());
        a.Edit();
        a.getdetails();

        a2.SelectMenu();

    }
}