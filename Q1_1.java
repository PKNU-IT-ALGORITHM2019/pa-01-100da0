package assignment01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Q1_1 {

		static String [] words=new String[500000];   // �ܾ�+����  ����
		static String [] terms=new String[500000];   // �ܾ ����
		static int N=0;
		
		public static void main(String [] args) {
			
			Scanner kb = new Scanner(System.in);
			
			while( true ) { 
				System.out.print("$ ");
				String command = kb.next();
				
				if(command.equals("read")) {
					String fileName = kb.next();
					makeIndex( fileName );
				}
				
				else if(command.equals("find")) {
					String str=kb.nextLine().substring(1);
					int n = findWord(str);
					if(n > 0) 
						System.out.println("Found "+ n +" items.");
						
					else System.out.println("Not found.");
					
					String []sign= { " ","-","'"};	 //��ȣ�� �ִ��� Ȯ��
					int T =0;
					for(int i=0;i<3;i++) {
						if(str.contains(sign[i])) 
							T =1;	
					}
					if(T==0)
					findPrint(findIndex(str+" ",0,N-1),n);		
					
					else findPrint(findIndexsign(str+" ",0,N-1),n);	
				}
				
				else if(command.equals("size")) {		
					System.out.println(N);	
				}
				
				else if(command.equals("exit")) 
					break;
					
			}
			kb.close();
			
		}
		
		static void makeIndex( String fileName ){		
			try {
			 File file = new File( fileName);
			 FileReader filereader = new FileReader(file);
			 BufferedReader bufReader = new BufferedReader(filereader);
			 String line = "";
			 while((line = bufReader.readLine()) != null){
				 if(line.equals(""))
					 continue;
				 else
				 addWord(line);
	            
	         }
			 bufReader.close();	 
		     // System.out.println("Success");
			
			}catch(FileNotFoundException e){
				System.out.println("No file");
				return; 
				}catch(IOException e){
		            System.out.println(e);
		        }
		}
		
		static void addWord( String str) {			
			String str2 = str;
			String[] arr= str2.split("\\(");
			words[N] = str;
			terms[N]= arr[0];
			N++;		
		    
		  //  System.out.println(words[N-1]);
		  //  System.out.println(terms[N-1]);
		}

		static int findWord(String str) {     //�����ܾ��
			int result = 0;
			for(int i=0;i<N;i++)
				if(terms[i].equalsIgnoreCase(str + " ")) result++;		
			return result;
		}

		
		static int findIndex(String str,int begin, int end) {   //�ܾ ��ȣ������ �����˻�		
			if (begin > end) {
		         if(end >= 0)   return end;
		         else         return -1;
		      }
			else {
				int middle =(begin+end)/2;
					 int compResult2 = str.compareToIgnoreCase(terms[middle]);
						if(compResult2 == 0) return middle;
			            else if(compResult2<0) return  findIndex(str,begin,middle-1 );	
						else return  findIndex(str,middle+1,end);	
				}
				
			}
		

		static int findIndexsign(String str,int begin, int end) {   //�ܾ ��ȣ ������ �����˻�		
			if (begin > end) {
		         if(end >= 0)   return end;
		         else         return -1;
		      }
			else {
				int middle =(begin+end)/2;
				String str2=str.replaceAll(" ",""); //��������
				String terms2=terms[middle].replaceAll(" ", "");
				String str3=str2.replaceAll("'","");    //���۽�Ʈ��������
				String terms3=terms2.replaceAll("'", "");				
				String str4=str3.replaceAll("-","");    //-����
				String terms4=terms3.replaceAll("-", ""); 				
	            int compResult2 = str4.compareToIgnoreCase(terms4);
				if(compResult2 == 0) return middle;
	            else if(compResult2<0) return  findIndexsign(str,begin,middle-1 );	
				else return findIndexsign(str,middle+1,end);	
				
			}
		}

		static void findPrint(int p,int q) {  //p=�ε�����,q=����
			if(q==0 && p >= N-1) {	       //ã�� �ܾ ����, �ڿ��ִ´ܾ ������������
				System.out.println(words[N-1]);	
				System.out.println("- - -");			
			}
			else if(q==0 && p<0) {        //ã�� �ܾ ����, �տ��ܾ �������� ����
				System.out.println("- - -");
				System.out.println(words[0]);
			}
			else if(q==0) {        //ã�� �ܾ�� ������ ��,�յ� �ܾ�� ����
				System.out.println(words[p]);
				System.out.println("- - -");
				System.out.println(words[p+1]);
			}
			else   {                     //ã�� �ܾ ����
				if( p-q <0) {           //�� �ʿ� ������
					for(int i=0;i<p+q;i++) {
						if(terms[p].equalsIgnoreCase(terms[i]))
						System.out.println(words[i]);
			     	}
				}
				else if (p+q > N){                //�� �ʿ� ������
					for(int i=p-q;i<N;i++) {      
						if(terms[p].equalsIgnoreCase(terms[i]))
						System.out.println(words[i]);
			     	}
				}
				else {
				for(int i=p-q;i<p+q;i++) {             //�߰��� ���� ��
					if(terms[p].equalsIgnoreCase(terms[i])) {
						System.out.println(words[i]);
					}
					
				}	
				}
		}
	
     }
}
