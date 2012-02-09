import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;





//today 250 
//http://thegradcafe.com/survey/index.php?t=n&pp=250


public class parser {

		
		
		static ArrayList<SchoolEntry> schoolList = new ArrayList<SchoolEntry>();
		
		public static ArrayList<SchoolEntry>  getList(){
			
			return schoolList;
		}
		
		/*
		 * instcol, extinfo, datecol
		 * 
		 */
	 private static void read() throws IOException {
		 
		 String schoolNameToken = "instcol";
		 String popOutToken = "<a class=\"extinfo\"";
		 
		 String end1 = "</td><td>";
		 String end2 ="<a class=\"extinfo\"";
		 String end4 = "</strong>";
		 String end5 = "</td><td class=\"datecol\">";
		 String end6 = "<br";
		 String end7 = "class=\"datecol\">";
		 String end8 = "class=\"controlspam\"></li><li>";
		 String end9 = "</li></ul></td></tr>";
		 
		 boolean GPA = false;
		 final boolean debug = false;
		 
		    
	       // Log.d("downloader", "Reading from file.");
	        File fname = new File("1d250.htm");
	        Scanner scanner = new Scanner(new FileInputStream(fname));
	        int counter = 0;
	        
	        try {
	          while (scanner.hasNextLine()){
	            
	        	String line = scanner.nextLine();
				
	            if(line.contains(schoolNameToken)){
	            	//case 1, ones with pop out
	            	int startUniIndex = -1;	int endUniIndex = -1;
	            	int prostartIndex = -1;	int proEndIndex = -1;
	            	int arStartIndex = -1;	int arEndIndex = -1;
	            	int UgradGPAStart = -1;	int UgradGPAEnd = -1;
	            	int GREGeneralStart = -1;	int GREGeneralEnd = -1;
	            	int GRESubStart = -1;	int GRESubEnd = -1;
	            	int studentStatusStart = -1;	int studentStatusEnd = -1;
	            	int dateAddedStart = -1;	int dateAddedEnd = -1;
	            	int notesStart = -1; int notesEnd = -1;
	            	
	            	String programName = ""; // Computer And Information Sciences, PhD (F12)
	            	String UniName = "";	//University Of Delaware (UDel)
	            	String arStatus = "";	//Accepted via E-mail on 4 Feb 2012 
	            	String UgradGPA = " n/a";	//Undergrad GPA: n/a
	            	String GREGeneral = " n/a"; //GRE General (V/Q/W) 700/800/4.50
	            	String GRESub = " n/a";		//GRE Subject: 990
	            	String studentStatus = "?";
	            	String dateAdded = "";
	            	String notes = "";
	            	
	            	
	            	counter ++;
	            	
	            	if(line.contains(popOutToken))
	            		GPA = true;
	            	else
	            		GPA = false;
	            	
            		startUniIndex = line.indexOf(schoolNameToken);
            		if(startUniIndex != -1){
            			//purpose: instcol">University Of British Columbia
            			startUniIndex += schoolNameToken.length() + 2;
            			
            			endUniIndex = line.indexOf(end1,startUniIndex);
            			if(endUniIndex != -1)
            				UniName = line.substring(startUniIndex, endUniIndex);
            		
            		}//startIndex
            		
            		//!!!!!!!!!got school name here!!!!!!!!
            		if(debug)
            		System.out.println("\n"+ UniName);
            		
            		if(endUniIndex != -1){
            			prostartIndex = endUniIndex + end1.length();
            			proEndIndex = line.indexOf(end1, prostartIndex);
            			
            			if(proEndIndex != -1)
            				programName = line.substring(prostartIndex, proEndIndex);
            			
            		}//getting program name
            		
            		//!!!!!!!!!got program name here!!!!!!!!
            		if(debug)
            		System.out.println("\t" + programName);
            		
            		
            		if(proEndIndex!=-1){
            			
            			arStartIndex = proEndIndex + end1.length();
            			if(arStartIndex != -1){
            				if(GPA)
            					arEndIndex = line.indexOf(end2, arStartIndex);
            				else{
            					arEndIndex = line.indexOf(end1, arStartIndex);
            				}
            				if(arEndIndex != -1)
            					arStatus = line.substring(arStartIndex, arEndIndex);
            				else
            					arStatus += arEndIndex;
            			}
            		}
            		
            		//!!!!!!!!!got arStatus here!!!!!!!!
            		if(debug)
            		System.out.println("\t" + arStatus);

            		            		
            		if(GPA){
            			/*
            			 * 	int UgradGPAStart = -1;	int UgradGPAEnd = -1;
			            	int GREGeneralStart = -1;	int GREGeneralEnd = -1;
			            	int GRESubStart = -1;	int GRESubEnd = -1;
			            	
            			 */
            			if(arEndIndex!=-1){
	            			UgradGPAStart = line.indexOf(end4,arEndIndex) + end4.length();
	            			if(UgradGPAStart!=-1){
	            				
	            				UgradGPAEnd = line.indexOf(end6, UgradGPAStart);
	            				if(UgradGPAEnd != -1)
	            					UgradGPA = line.substring(UgradGPAStart, UgradGPAEnd);
	            		
	            			}
            			}
            			//!!!!!!!!!got GPA info here!!!!!!!!
            			
            			
            			if(UgradGPAEnd != -1){
            				
            				GREGeneralStart = line.indexOf(end4, UgradGPAEnd);
            				if(GREGeneralStart != -1){
            					GREGeneralStart += end4.length();
            					GREGeneralEnd = line.indexOf(end6, GREGeneralStart);
            					if(GREGeneralEnd != -1)
            						GREGeneral = line.substring(GREGeneralStart, GREGeneralEnd);
            				}
            			}
            			//!!!!!!!!!got GRE general here!!!!!!!!
            			
            			
            			if(GREGeneralEnd!= -1){
            				GRESubStart = line.indexOf(end4, GREGeneralEnd);
            				if(GRESubStart != -1){
            					
            					GRESubStart += end4.length();
            					GRESubEnd = line.indexOf(end6, GRESubStart);
            					
            					if(GRESubEnd != -1)
            						GRESub = line.substring(GRESubStart, GRESubEnd);
            				}
            				
            				
            			}
            			
            			//!!!!!!!!!got GRE Sub here!!!!!!!!
            			
            			
            			
            		}//GPA
            	
            		
            		if(debug)
        			System.out.println("\tGRE General (V/Q/W)"+ GREGeneral);
        			
            		if(debug)
            		System.out.println("\tGPA: "+ UgradGPA);
            			
        			if(debug)
        			System.out.println("\tGRE Sub "+ GRESub);
        			
        			
        			//int studentStatusStart = -1;	int studentStatusEnd = -1;
        			if(arEndIndex!=-1){
        				
        				studentStatusStart = line.indexOf(end1, arEndIndex);
        				if(studentStatusStart != -1){
        					studentStatusStart += end1.length();
        					studentStatusEnd = line.indexOf(end5, studentStatusStart);
        					if(studentStatusEnd != -1){
        						studentStatus = line.substring(studentStatusStart,studentStatusEnd);
        						
        					}
        					
        				}
        				
        			}
        			//!!!!!!!!!got studentStatusHere here!!!!!!!!
        			if(debug)
                		System.out.println("\tstudent type: " + studentStatus);

        			
        			
        			if(line.contains(end7)){
        				
        				dateAddedStart = line.indexOf(end7) + end7.length();
        				
        				dateAddedEnd = line.indexOf(end1, dateAddedStart);
    					if(dateAddedEnd!=-1)
    						dateAdded = line.substring(dateAddedStart, dateAddedEnd);
    				
        				
        			}
        			
        			//!!!!!!!!!got dateAdded here!!!!!!!!
        			if(debug)
                		System.out.println("\tdate added: " + dateAdded);

        			
        			if(line.contains(end8)){
        				notesStart = line.indexOf(end8) + end8.length();
        				notesEnd = line.indexOf(end9);
        				
        				if(notesEnd!=-1)
        					notes = line.substring(notesStart, notesEnd);	
        					
        				
        			}
        			
        			
        			
        			//!!!!!!!!!got dateAdded here!!!!!!!!
        			if(debug)
                		System.out.println("\tnotes: " + notes);

        			
        			schoolList.add(new SchoolEntry(UniName,programName, arStatus, UgradGPA, GREGeneral, GRESub, studentStatus, dateAdded, notes));
	            }//instcol
	          
	         
	          }//while
	          
	          }//try
	        
	        finally{
	        	System.out.println("counter = " + counter + "listSize= " + schoolList.size());
	        	
	        	 scanner.close();
	        }
	        
	      }//read
	   

	public static void main(String[] a) throws IOException{
	
		parser.read();
		
	}
	
}
