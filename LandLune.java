import java.lang.Math;
import java.util.Scanner;


public class LandLune {
   
   public static double[][] calculate(double dMass, double fMass, double mThrust, double isp) {
      double TWR = mThrust/(1.6*(dMass+fMass));
      
       double t_orb = ((int)((double)(3.59/(1.6*TWR))*1000));
       t_orb /= 1000;
       
       
       double D_orb =  ((3745.714/TWR)-(4.028/TWR));
       D_orb = cutDecimal(D_orb);
       
       double dV_orb =  (3.59);
       
       double throt_orb = (100*isp*9.8)/(isp*9.8+TWR*1.6*t_orb);
       throt_orb = cutDecimal(throt_orb);
       
       double t_cab = (-1669.4)/(1.6*TWR*-Math.cos(Math.toRadians(Math.asin(1/TWR))));
       t_cab = cutDecimal(t_cab);
       
       double D_cab = 2786896.36/(3.2*Math.cos(Math.toRadians(Math.asin(1/TWR))));
       D_cab = cutDecimal(D_cab);
       
       double dV_cab = 1669.4;
       dV_cab = cutDecimal(dV_cab);
       
       double throt_cab = (100*isp*9.8)/(isp*9.8+TWR*1.6*(t_cab+t_orb));
       throt_cab = cutDecimal(throt_cab);
      
       double t_sui = Math.sqrt(2*1.6*(15000-(15000/TWR)))/(1.6*(TWR-1));
       t_sui = cutDecimal(t_sui);
       
       double D_sui = 15000/TWR;
       D_sui = cutDecimal(D_sui);
       
       double dV_sui = Math.sqrt(2*1.6*15000);
       dV_sui = cutDecimal(dV_sui);
       
       double throt_sui = (100*isp*9.8)/(isp*9.8+TWR*1.6*(t_orb+t_cab+t_sui));
       throt_sui = cutDecimal(throt_sui);
       
      System.out.println("TWR: " + TWR + "\n");
      
       double[][] data = {{t_orb, t_cab, t_sui},  {D_orb, D_cab, D_sui},  {dV_orb, dV_cab, dV_sui},  {throt_orb, throt_cab, throt_sui}};
      return data;
   }
   
   public static double cutDecimal(double data)
   {
      data = ((int)((double)(data)*1000));
      data /= 1000;
      return data;
   }
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Dry Mass(kg): ");
      double dryMass = sc.nextDouble();
      
      System.out.print("\nFuel Mass(kg): ");
      double fuelMass = sc.nextDouble();
      
      System.out.print("\nMax Thrust(N): ");
      double maxThrust = sc.nextDouble();
      
      System.out.print("\nSpecific Impulse(s): ");
      double Isp = sc.nextDouble();
      
      double[][] info = calculate(dryMass,fuelMass,maxThrust,Isp);

      System.out.println("\t\t\t\t\t\t\t\t\t  Deorbit\t  \t CADM \t  \t Suicide Burn"); 
      
      System.out.print("Time(s): \t\t\t\t\t\t\t");    
      for (int col = 0; col < 3; col++) 
         System.out.print(info[0][col] + " \t \t");
      System.out.println();
         
      System.out.print("Maneuver Distance(m):\t\t\t");   
      for (int col = 0; col < 3; col++) 
         System.out.print(info[1][col] + "\t\t");
      System.out.println();
       
      System.out.print("delta-V for this phase(m/s): ");   
      for (int col = 0; col < 3; col++)
         System.out.print(info[2][col] + " \t   \t");
      System.out.println();
         
      System.out.print("End throttle(%): \t\t\t\t  ");
      for (int col = 0; col < 3; col++) 
         System.out.print(info[3][col] + " \t \t");
      
          
   }
}