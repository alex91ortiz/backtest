package com.coomeva.test.utiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class generate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		makeModel();
		makeRepository();
		makeServices();
		makeController();
		//makeAngular();
	}
	
	public static void makeModel(){
		  File model = null;
	      Scanner br = null;
	      JSONParser parser = new JSONParser();
	      JSONArray data=null;
	      try {
	    
	    	 model = new File ("src/main/resources/template/model.txt");
	         data = (JSONArray) parser.parse(new FileReader("src/main/resources/template/data.json"));
	         
	         
	         // Lectura del fichero
	         String linea;
	         if(data.size()>0) {
	        	 for(int i=0;i<data.size();i++) {
	        		 JSONObject table = (JSONObject) data.get(i);
	        		 JSONObject dtable = (JSONObject) table.get("table");
	        		 String name = (String) dtable.get("name");
	        		 JSONArray dattributes = (JSONArray) dtable.get("attributes");
	        		 String filename = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
	        		 
	        		 File exist = new File("src/main/java/com/coomeva/test/model/"+filename+".java");
	        		 if(!exist.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist));
	        			 br = new Scanner(model);
				         while(br.hasNextLine()) {
				        	 String line = br.nextLine();
				        	 System.out.println(line); 
				        	 line=line.replace("uname", filename);
				        	 line=line.replace("lname", filename.toLowerCase()+"s");
				        
				        	 if(line.contains("{")) {
				        		 for(int j=0;j<dattributes.size();j++) {
				        			 JSONObject tattributes = (JSONObject) dattributes.get(j);
				        			 if(((boolean)tattributes.get("key"))) {
				        				 line+="@Id\n"; 
				        				 line+="@GeneratedValue(strategy = GenerationType.IDENTITY)\n";
				        				 line+="private "+((String)tattributes.get("type"))+" "+((String)tattributes.get("name"))+";\n";
				        			 }else {
				        				 String foreign = ((String)tattributes.get("foreign"));
				        				 if(foreign.isEmpty()){
				        					line+="@Column(name = \""+((String)tattributes.get("name"))+"\", nullable = "+((boolean)tattributes.get("nullable"))+")\n";
				        				 	line+="private "+((String)tattributes.get("type"))+" "+((String)tattributes.get("name"))+";\n";
				        				 }else{
				        					String[] foreignname=(String[]) ((String)tattributes.get("type")).split("_");
				        					line+="@"+foreign+"(cascade = CascadeType.ALL)\n";
				        					line+="@JoinColumn(name = \""+((String)tattributes.get("name"))+"\", nullable = "+((boolean)tattributes.get("nullable"))+")\n";
					        				line+="private "+((String)tattributes.get("type"))+" "+foreignname[0].toLowerCase()+";\n";
				        				 }
				        			 }
				        		 }
				        	 }
				        	 
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        	 }
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != br ){   
	            	br.close();    
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	public static void makeRepository(){
		  File repository = null;
	      Scanner br = null;
	      JSONParser parser = new JSONParser();
	      JSONArray data=null;
	      try {
	    
	    	 repository = new File ("src/main/resources/template/repository.txt");
	         data = (JSONArray) parser.parse(new FileReader("src/main/resources/template/data.json"));
	         
	         
	         // Lectura del fichero
	         String linea;
	         if(data.size()>0) {
	        	 for(int i=0;i<data.size();i++) {
	        		 JSONObject table = (JSONObject) data.get(i);
	        		 JSONObject dtable = (JSONObject) table.get("table");
	        		 String name = (String) dtable.get("name");
	        		 JSONObject validation = (JSONObject) dtable.get("validation");
	        		 JSONArray dattributes = (JSONArray) dtable.get("attributes");
	        		 String filename = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
	        		 
	        		 File exist = new File("src/main/java/com/coomeva/test/repository/"+filename+"Repository.java");
	        		 if(!exist.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist));
	        			 br = new Scanner(repository);
				         while(br.hasNextLine()) {
				        	 String line = br.nextLine();
				        	 System.out.println(line); 
				        	 line=line.replace("uname", filename);
				        	 line=line.replace("lname", filename.toLowerCase()+"s");
				        	 if(((boolean)validation.get("success"))) {
					        	 if(line.contains("validation")) {
					        		 
					        		 String subline="";
					        		 String attrname=(String) validation.get("atrr");
					        		 subline="	@Query(\"from "+filename+" l  where l."+attrname+"=:"+attrname+"\")\n" + 
					        		 		 "  public Iterable<"+filename+"> findBy"+String.valueOf(attrname.charAt(0)).toUpperCase()+attrname.substring(1)+"(@Param(/"+attrname+"/) String "+attrname+"); \n" ;
					        		 line=line.replace("validation", subline);
					        	 }
				        	 }else {
				        		 line=line.replace("validation", "");
				        	 }
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        	 }
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != br ){   
	            	br.close();    
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	public static void makeServices(){
		File model = null;
		File model1 = null;
		Scanner br = null;
		Scanner br1 = null;
		JSONArray data=null;
		JSONParser parser = new JSONParser();
		try {
			data = (JSONArray) parser.parse(new FileReader("src/main/resources/template/data.json"));
			model = new File ("src/main/resources/template/service.txt");
			
			model1 = new File ("src/main/resources/template/serviceImpl.txt");
			
		     // Lectura del fichero
	         String linea;
	         if(data.size()>0) {
	        	for(int i=0;i<data.size();i++) {
	        		 JSONObject table = (JSONObject) data.get(i);
	        		 JSONObject dtable = (JSONObject) table.get("table");
	        		 String name = (String) dtable.get("name");
	        		 JSONObject validation = (JSONObject) dtable.get("validation");
	        		 JSONArray dattributes = (JSONArray) dtable.get("attributes");
	        		 String filename = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
	        		 
	        		 File exist = new File("src/main/java/com/coomeva/test/service/"+filename+"Service.java");
	        		 if(!exist.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist));
	        			 br1 = new Scanner(model1);
	        			 br = new Scanner(model);
				         while(br.hasNextLine()) {
				        	 String line = br.nextLine();
				        	 System.out.println(line);
				        	 line=line.replace("uname", filename);
				        	 if(((boolean)validation.get("success"))) {
					        	 if(line.contains("validation")) {
					        		 
					        		 String subline="";
					        		 String attrname=(String) validation.get("atrr");
					        		 subline=filename+" findBy"+String.valueOf(attrname.charAt(0)).toUpperCase()+attrname.substring(1)+"(String "+attrname+");";
					        		 line=line.replace("validation", subline);
					        	 }
				        	 }else {
				        		 line=line.replace("validation", "");
				        	 }
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        		 
	        		 File exist1 = new File("src/main/java/com/coomeva/test/service/"+filename+"ServiceImpl.java");
	        		 if(!exist1.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist1));
	        			 br1 = new Scanner(model1);
	        			 br = new Scanner(model);
				         while(br1.hasNextLine()) {
				        	 String line = br1.nextLine();
				        	 System.out.println(line);
				        	 line=line.replace("uname", filename);
				        	 line=line.replace("lname", filename.toLowerCase());
				        	 if(((boolean)validation.get("success"))) {
					        	 if(line.contains("validation")) {
					        		 
					        		 String subline="";
					        		 String attrname=(String) validation.get("atrr");
					        		 subline="	@Override\n" + 
					        		 		"	public "+filename+" findBy"+String.valueOf(attrname.charAt(0)).toUpperCase()+attrname.substring(1)+"(String "+attrname+") {\n" + 
					        		 		"		  Iterable<"+String.valueOf(attrname.charAt(0)).toUpperCase()+attrname.substring(1)+"> "+filename.toLowerCase()+" = " +filename.toLowerCase()+"Repository findBy"+String.valueOf(attrname.charAt(0)).toUpperCase()+attrname.substring(1)+"("+attrname+");\n"+ 
					        		 		"\n" + 
					        		 		"         return "+filename.toLowerCase()+";\n" + 
					        		 		"	}";
					        		 line=line.replace("validation", subline);
					        	 }
				        	 }else {
				        		 line=line.replace("validation", "");
				        	 }
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        	}
	         }
		}catch(Exception e){
	         e.printStackTrace();
	    }finally{
	         try{                    
	            if( null != br ){   
	            	br.close();    
	            }  
	            if( null != br1 ){   
	            	br1.close();    
	            }   
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	    }
	}
	
	
	
	public static void makeController(){
		File model = null;
		Scanner br = null;
		JSONArray data=null;
		JSONParser parser = new JSONParser();
		try {
			data = (JSONArray) parser.parse(new FileReader("src/main/resources/template/data.json"));
			model = new File ("src/main/resources/template/controller.txt");
			
			
		     // Lectura del fichero
	         String linea;
	         if(data.size()>0) {
	        	for(int i=0;i<data.size();i++) {
	        		 JSONObject table = (JSONObject) data.get(i);
	        		 JSONObject dtable = (JSONObject) table.get("table");
	        		 String name = (String) dtable.get("name");
	        		 JSONObject validation = (JSONObject) dtable.get("validation");
	        		 JSONArray dattributes = (JSONArray) dtable.get("attributes");
	        		 String filename = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
	        		 
	        		 File exist = new File("src/main/java/com/coomeva/test/controller/"+filename+"Controller.java");
	        		 if(!exist.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist));
	        			 br = new Scanner(model);
				         while(br.hasNextLine()) {
				        	 String line = br.nextLine();
				        	 System.out.println(line);
				        	 line=line.replace("uname", filename);
				        	 line=line.replace("lname", filename.toLowerCase());
				        	 if(line.contains("settings")) {
				        		 String subline="";
				        		 for(int j=0;j<dattributes.size();j++) {
				        			 JSONObject tattributes = (JSONObject) dattributes.get(j);
				        			 String type =(String)tattributes.get("type");
				        			 if(!((boolean)tattributes.get("key"))) {
				        				 String attrname=((String)tattributes.get("name"));
				        				 String attrnameu=String.valueOf(attrname.charAt(0)).toUpperCase() + attrname.substring(1);
				        				 String foreign = ((String)tattributes.get("foreign"));
				        				 if(!foreign.isEmpty()){
				        					 String[] foreignname=(String[]) ((String)tattributes.get("type")).split("_");
				        					 attrnameu = foreignname[0];
				        				 }
				        				 subline+="current"+filename+".set"+attrnameu+"("+filename.toLowerCase()+((type.equals("boolean"))?".is":".get")+attrnameu+"());\n";
				        			 }
				        		 }
				        		 line=line.replace("settings", subline);
				        	 }
				        	 if(((boolean)validation.get("success"))) {
					        	 if(line.contains("validation")) {
					        		 String subline="";
					        		 String attrname=(String) validation.get("atrr");
					        		 String attrnameu=String.valueOf(attrname.charAt(0)).toUpperCase()+attrname.substring(1);
					        		 subline=" System.out.println(\"Creating  \");\n" + 
					        		 		"        "+filename+" "+filename.toLowerCase()+"Old = "+filename+"ServiceImpl.findBy"+attrnameu+"("+filename.toLowerCase()+".get"+attrnameu+"());\n" + 
					        		 		"        if ("+filename.toLowerCase()+"Old!=null) {\n" + 
					        		 		"            System.out.println(\"A Companie with "+attrname+" \" + "+filename.toLowerCase()+".get"+attrnameu+"() + \" already exist\");\n" + 
					        		 		"            return new ResponseEntity<Void>(HttpStatus.CONFLICT);\n" + 
					        		 		"        }\n" + 
					        		 		"  ";
					        		 line=line.replace("validation", subline);
					        	 }
					         }else {
					        	 line=line.replace("validation", "");
					         }
				        	 
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        	}
	         }
		}catch(Exception e){
	         e.printStackTrace();
	    }finally{
	         try{                    
	            if( null != br ){   
	            	br.close();    
	            }  
	          
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	    }
	}
	public static void makeAngular(){
		File model = null;
		File model1 = null;
		Scanner br = null;
		Scanner br1 = null;
		JSONArray data=null;
		JSONParser parser = new JSONParser();
		try {
			data = (JSONArray) parser.parse(new FileReader("src/main/resources/template/data.json"));
			model = new File ("src/main/resources/template/angularservice.txt");
			
			model1 = new File ("src/main/resources/template/angularcontroll.txt");
			
		     // Lectura del fichero
	         String linea;
	         if(data.size()>0) {
	        	for(int i=0;i<data.size();i++) {
	        		 JSONObject table = (JSONObject) data.get(i);
	        		 JSONObject dtable = (JSONObject) table.get("table");
	        		 String name = (String) dtable.get("name");
	        		 JSONObject validation = (JSONObject) dtable.get("validation");
	        		 JSONArray dattributes = (JSONArray) dtable.get("attributes");
	        		 String filename = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
	        		 
	        		 File exist = new File("WebContent/WEB-INF/views/js/service/"+filename+"Service.js");
	        		 if(!exist.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist));
	        			 br = new Scanner(model);
	        			 br1 = new Scanner(model1);
				         while(br.hasNextLine()) {
				        	 String line = br.nextLine();
				        	 System.out.println(line);
				        	 line=line.replace("uname", filename);
				        	 line=line.replace("lname", filename.toLowerCase());
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        		 
	        		 File exist1 = new File("WebContent/WEB-INF/views/js/controller/"+filename+"Controller.js");
	        		 if(!exist1.exists()) {
	        			 BufferedWriter output = new BufferedWriter(new FileWriter(exist1));
	        			 
				         while(br1.hasNextLine()) {
				        	 String line = br1.nextLine();
				        	 System.out.println(line);
				        	 line=line.replace("uname", filename);
				        	 line=line.replace("lname", filename.toLowerCase());
				        	 if(line.contains("settings")) {
				        		 String subline="";
				        		 for(int j=0;j<dattributes.size();j++) {
				        			 JSONObject tattributes = (JSONObject) dattributes.get(j);
				        			 String attrname=((String)tattributes.get("name"));
				        			 subline+=((((boolean)tattributes.get("key")))?attrname+":null":","+attrname+":''");
				        			 
				        		 }
				        		 line=line.replace("settings", subline);
				        	 }
				        	 output.write(line);
				         }
				         if(output!=null)
				        	 output.close();
	        		 }
	        	}
	         }
		}catch(Exception e){
	         e.printStackTrace();
	    }finally{
	         try{                    
	            if( null != br ){   
	            	br.close();    
	            }  
	            if( null != br1 ){   
	            	br1.close();    
	            }   
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	    }
	}
	
}
