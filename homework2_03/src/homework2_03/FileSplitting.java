package homework2_03;
import java.io.*;
import java.util.*;
public class FileSplitting {
	String file_Name=null;
	long file_Size=0;
	long block_Number=0;
    public void getFileAttribute(String fileAndPath)
    {
		File file = new File(fileAndPath);
		file_Name=file.getName();
		file_Size=file.length();
	}
	public long getBlockNumber(long block_Size)
	{
		if(file_Size<=block_Size)
			return 1;
		else if(file_Size%block_Size>0){
			return (file_Size/block_Size)+1;
		}else
			return file_Size/block_Size;
	}
	public String getCurrentFileName(String fileAndPath,int currentBlock)
	{
		String splittingFile=fileAndPath + " file"+currentBlock;
		System.out.println("�ļ�����ֳɣ�"+splittingFile);
		return splittingFile;
	}
	
	
	public boolean writeFile(String fileAndPath,String fileName,long blockSize,long beginPosition )
	{
		RandomAccessFile raf=null;
		FileOutputStream fos=null;
		byte[] bt=new byte[1024];
		long writeByte=0;
		int length=0;
		try{
			raf=new RandomAccessFile(fileAndPath,"r");
	        raf.seek(beginPosition);
	        fos=new FileOutputStream(fileName);
	        while((length=raf.read(bt))>0){
	        	if(writeByte<blockSize)
	        	{
	        		writeByte=writeByte+length;
	        		if(writeByte<=blockSize)
	        			fos.write(bt, 0, length);
	        		else{
	        			length=length-(int)(writeByte-blockSize);
	        			fos.write(bt, 0, length);
	        		}
	        	}
	        }
	        fos.close();
			raf.close();
		}catch(Exception e){
			e.printStackTrace();
			try{
				if(fos!=null)
					fos.close();
				if(raf!=null)
					raf.close();
			}catch(Exception f){
				f.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	
		
	
	public boolean splitFile(String fileAndPath,long blockSize){
		getFileAttribute(fileAndPath);
		block_Number=getBlockNumber(blockSize);
		System.out.println("ԭ�ļ�����ֳ� "+block_Number+" ���ļ�");
		if(block_Number==1)
			blockSize=file_Size;
		long writeSize=0;
		long writeTotal=0;
		String currentNameAndPath=null;
		for(int i=1;i<=block_Number;i++){
			if(i<block_Number)
				writeSize=blockSize;
			else
				writeSize=file_Size-writeTotal;
			if(block_Number==1)
				currentNameAndPath=fileAndPath+".bat";
			else
				currentNameAndPath=getCurrentFileName(fileAndPath,i);
			if(!writeFile(fileAndPath,currentNameAndPath,writeSize,writeTotal))
				return false;
			writeTotal=writeTotal+writeSize;
		}
		return true;
	}	
	public String[] splittedFiles(String fileAndPath,long blockSize){
		block_Number=getBlockNumber(blockSize);
		if(block_Number==1)
			blockSize=file_Size;
		String[] name=new String[(int)block_Number];
		String currentName=null;
		for(int i=1;i<=block_Number;i++){
			if(block_Number==1)
				currentName=fileAndPath+".bat";
			else
				currentName=getCurrentFileName(fileAndPath,i);
			name[i-1]=currentName;
		}
		return name;
	}

	public static String combine(String[]fileNames,String targetFileName)
	throws Exception{
		File inFile =null ;
		File outFile=new File(targetFileName);
		FileOutputStream out=new FileOutputStream(outFile);
		for(int i=0;i<fileNames.length;i++){
			inFile=new File(fileNames[i]);
			FileInputStream in =new FileInputStream(inFile);
			int c;
			while((c=in.read())!=-1){
				out.write(c);
			}
			in.close();
		}
			out.close();
			return outFile.getAbsolutePath();
	}
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		FileSplitting split=new FileSplitting();
		System.out.println("������Ҫ��ֵ��ļ�(�ļ�·���ͺ�׺)��");
		Scanner keyboard =new Scanner(System.in);
		String fileAndPath =keyboard.next();
		long blockSize=100*100;
		if(split.splitFile(fileAndPath, blockSize))
		{
			System.out.println("�ļ���ֳɹ�");
		}
		else
		{
			System.out.println("�ļ����ʧ��");
		}
		System.out.println("������ϲ����Ŀ���ļ�(�ļ�·���ͺ�׺)��");
		String targetFileName = keyboard.next();
		String merge = combine(split.splittedFiles(fileAndPath, blockSize),targetFileName);
		System.out.println("�ļ��ϲ�Ϊ�� "+ merge);
	}
	
}