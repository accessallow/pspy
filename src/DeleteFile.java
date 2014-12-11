import java.io.File;
 
public class DeleteFile
{
    public static void delete(String filename)
    {	
    	try{
 
    		File file = new File(filename);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
			
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
 
    }
}
