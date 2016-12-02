import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class readData {

	public static void main(String[] args) {
		
		//Establishing A connection
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","aaaa");
		
		/*
		 * Reading the file
		 * */
		String csvFile ="C:/Users/VuaData/Desktop/data.csv";
		 BufferedReader br = null,cr=null;
	        String line = "";
	        String cvsSplitBy = ",";
	        String tagNo,status,t,dd;
	        //Date d;
	        int time;

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	           
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] entry = line.split(cvsSplitBy);
                   //store entry to database 
	                tagNo=entry[0];
	                status=entry[1];
	                t=entry[2];
	                time=Integer.parseInt(t);
	                dd=entry[3];
	                System.out.println("readData.main()"+":::"+entry[0]+"::::"+entry[1]+":::"+entry[2]+":::"+Integer.parseInt(t)+"::::"+entry[3]);
	               
	                if((Validate.vali(tagNo,status,time,dd))&&(status.equalsIgnoreCase("I"))&&(FirstIn.inCheck(tagNo,status,dd))){
	                	//d=DateFormat.getDateInstance().parse(dd);
	                	/*SimpleDateFormat f = new SimpleDateFormat("YYYY/MM/DD");
	                	Date date2 = (Date)f.parse(dd);*/
	                	//date=formatter.format(date2);
	                	//System.out.println("adata:::: "+date2);
	                	//System.out.println(date2);
	                	
	                	PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into empTime values(?,?,?,?)");  
	                	ps.setString(1,tagNo);
						ps.setString(4,dd); 
						//java.sql.Date sqlDate = new java.sql.Date(date2.getTime());
						
						//boolean bb=FirstIn.inCheck(tagNo, status, dd);
						if(status.equalsIgnoreCase("I")){
							ps.setInt(2,time);
							
						}
					
					
						cr = new BufferedReader(new FileReader(csvFile));
						while ((line = cr.readLine()) != null)
						{
							String tagNo1,status1,t1,dd1;
					        int time1;
					        String[] entry1=line.split(cvsSplitBy);
					        
					        tagNo1=entry1[0];
					        status1=entry1[1];
					        t1=entry1[2];
					        time1=Integer.parseInt(t1);
					        dd1=entry1[3];
					        System.out.println(entry1[0]+"::::"+entry1[1]+":::"+entry1[2]+":::"+Integer.parseInt(t1)+"::::"+entry1[3]);
					        
					        if(Validate.vali(tagNo1,status1,time1,dd1)&&(status1.equalsIgnoreCase("O"))){
					        	boolean a=tagNo1.equalsIgnoreCase(tagNo);
					        	boolean b=status1.equalsIgnoreCase("O");
					        	boolean c=dd1.equalsIgnoreCase(dd);
					        	System.out.println(a+"*"+b+"*"+c);
					        	if((a) && (b) && (c)){
					        		ps.setInt(3,time1);
					        		System.out.println(time1);
					        	}
					        	else{
					        		
					        	}
					        
					        }
					        /*else{
					        	continue;
					        }*/
					       
					        
						}
						 int i=ps.executeUpdate();  
						 if(i>0)  
						 System.out.print("success"); 
	                	
	                }
	                
	              /*
	              else{
	                	//break;
	                	continue; 
	                }*/
	                
	              
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                }		
		}
	       
	}
		catch(Exception e){System.out.println(e);}	

	}

}
