package dates;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class DBOperation{
	private MyDBConnection myDB=null;
	private Connection conn=null;
	private Statement stmt=null;
	private int scores;
	private int number1;
	private int number2;
	private String name;
	private String password;
	public DBOperation(MyDBConnection myDB){
		conn=myDB.getMyConnection();
		stmt=myDB.getMyStatement();
		number1=0;
		number2=0;
	}
	public void insertData(String name,String password,int scores){
		try{
			String newType1=new String(name.getBytes(),"GBK");//�ֽ�ת��
			String newType2=new String(password.getBytes(),"GBK");
			String sql="INSERT INTO player(scores,name,password)VALUES("+scores+",'"+newType1+"','"+newType2+"')";
			stmt.executeUpdate(sql);//�������
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	public void deleteData(int scores){
		String sql="DELETE FROM player WHERE scores="+scores+"";
		System.out.print(sql);
		try{
			stmt.executeUpdate(sql);
			//System.out.println("һ����¼��ɾ��");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void updateData(int mscores,int scores,String name,String password){//�޸�
		String sql="UPDATE player SET scores="+scores+",name='"+name+"',password='"+password+"'where scores="+mscores+"&&name='"+name+"'&&password='"+password+"'";
		try{
			stmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean  selectPassword(String mpassword){
		String sql="SELECT scores,name,password FROM player";
		try{
			ResultSet rs=stmt.executeQuery(sql);//���ؽ����
			while(rs.next()){//ָ������ƶ�
				password=rs.getString("password");
				number2++;
				//System.out.print(rs.getString("password")+"  ");
				if(password.equals(mpassword)&&(number2==number1)){
					//System.out.print("number2:"+number2);
					return true;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean selectName(String mname){
		String sql="SELECT scores,name,password FROM player";
		try{
			ResultSet rs=stmt.executeQuery(sql);//���ؽ����
			while(rs.next()){//ָ������ƶ�
				name=rs.getString("name");
				number1++;
				if(name.equals(mname)){
					//System.out.print("number1:"+number1);
					return true;
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public int selectScores(String name,String password){
		String sql="SELECT scores,name,password FROM player where name='"+name+"'&&password='"+password+"'";
		try{
			ResultSet rs=stmt.executeQuery(sql);//���ؽ����
			while(rs.next()){//ָ������ƶ�
				scores=rs.getShort("scores");
				return scores;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	return 0;
	}
	public void selectAll(){
		int i=0;
		String sql="SELECT scores,name,password FROM player";
		try{
			ResultSet rs=stmt.executeQuery(sql);//���ؽ����
			while(rs.next()){//ָ������ƶ�
				name=rs.getString("name");
				password=rs.getString("password");
				scores=rs.getShort("scores");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public int getScores(){
		return scores;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	
	public void setNumber1(){
		number1=0;
	}
	public void setNumber2(){
		number2=0;
	}
}