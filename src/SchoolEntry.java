
public class SchoolEntry {
	
		
		String programName = ""; // Computer And Information Sciences, PhD (F12)
		String major; // Computer science
		String program; // Phd
		String term; // F2011
		        	
    	String UniName = "";	//University Of Delaware (UDel)
    	String arStatus = "";	//Accepted via E-mail on 4 Feb 2012 
    	String UgradGPA = " n/a";	//Undergrad GPA: n/a
    	String GREGeneral = " n/a"; //GRE General (V/Q/W) 700/800/4.50
    	String GRESub = " n/a";		//GRE Subject: 990
    	String studentStatus = "?";
    	String dateAdded = "";
    	String notes = "";	
    	boolean accepted = false;
    	final boolean debug = false;
    	
    	private int LastIndexOf(String ori, char tar){
    		
    		for(int i=ori.length()-1;i!=-1; i--){
    			
    			if(ori.charAt(i) == tar){
    				return i;
    			}
    		}
    		return -1;
    		
    	}

    	public SchoolEntry(String n, String p, String a, String u, String general, String sub, String ss, String date, String ns){
    		
    		UniName = n;
    		programName = p;
    		arStatus = a;
    		UgradGPA = u;
    		GREGeneral = general;
    		GRESub = sub;
    		studentStatus = ss;
    		dateAdded = date;
    		notes = ns;
    	
    		programName = programName.toLowerCase();
    	
    		
    		if(!programName.equals(null)){
    			if(programName.contains(",")){
    				major = programName.substring(0, programName.indexOf(","));
    				major = programName.substring(0, LastIndexOf(programName, ','));
    			}
    			if(programName.contains("phd")){
    				program = "Phd";
    			}else if(programName.contains("masters")){
    				program = "MS";
    			}else 
    				program = "?";
    		}
    		
    		if(programName.contains("(")){
    			term = programName.substring(programName.indexOf("(")+1, programName.indexOf(")"));
    		}else
    			term = "?";
    	
    		
    		
    		
    		if(arStatus.contains("Accepted"))
    			accepted = true;
    		else
    			accepted = false;
    	
    		if(debug){
    			/*
    			 * Harvard Graduate School Of Arts And Sciences (GSAS)
					Mathematics, PhD (F12)
					Rejected via Postal Service on 8 Feb 2012 
					GRE General (V/Q/W): 163/170/4.50
					GPA: : 4.00
					GRE Sub : 830
					student type: I
					date added: 9 Feb 2012
    			 */
    			System.out.println("\n"+ UniName);
    			System.out.println("\tmajor: "+ major);
    			System.out.println("\tprogram: "+ program);
    			System.out.println("\t"+programName);
    			System.out.println("\t"+ term);
    			System.out.println("\t"+ dateAdded);
    			System.out.println("\taccepted?  "+ String.valueOf(accepted));
    			System.out.println("\tstudent status "+  studentStatus);
    			System.out.println("\tnotes: "+ notes);
    			
    		}
    	
    	}
    	
    	
    	public boolean getAcceptance(){
    		return accepted; 
    	}
    	public String getName(){
    		return UniName;
    		
    	}
    	public String getMajor(){
    		return major;
    	}
    	public String getTerm(){
    		return term;
    	}
    	public String getProgram(){
    		return program;
    	}
    	public String getNotes(){
    		return notes;
    	}
    	public String getDate(){
    		return dateAdded;
    	}
    	
	}