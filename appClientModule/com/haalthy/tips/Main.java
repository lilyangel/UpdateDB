package com.haalthy.tips;
import java.sql.*;
import java.util.Properties;

public class Main {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver") ;
	    Properties props = new Properties();
	    props.put("user", "root");
	    props.put("password", "");

	    props.put("autoReconnect", "true");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/haalthy",props);
		Statement stmt = conn.createStatement() ;
		String tagquery = "select TagID from Tag" ;
		ResultSet tagRs = stmt.executeQuery(tagquery) ;
		while (tagRs.next()) {
			String id = tagRs.getString("TagID");
			Statement stmt2 = conn.createStatement() ;
			String userQueryByTag = "select * from User where Username in (Select Username From UserTag where TagID = "+id+") order by FollowCount desc";
			ResultSet userRs = stmt2.executeQuery(userQueryByTag);
			int i = 0;
//			| Stage        | tinyint(4)   | YES  |     | NULL    |       |
//			| Pathological | varchar(20)  | YES  |     | NULL    |       |
//			| IsSmoking    | tinyint(4)   | YES  |     | NULL    |       |
//			| Gender       | varchar(10)  | YES  |     | NULL    |       |
//			| Image        | longblob     | YES  |     | NULL    |       |
//			| Age          | int(4)       | YES  |     | NULL    |       |
//			| Role         | varchar(20)  | YES  |     | NULL    |       |
//			| CancerType   | varchar(20)  | YES  |     | NULL    |       |
//			| metastasis
			while(userRs.next()){
//			    String sql = "INSERT INTO SuggestUserByTags (TagID, RankID, UserID, Password, Email,CreateDate, UpdateDate, Username, displayname, FollowCount, Stage, Pathological, IsSmoking, Gender, Image, Age, CancerType,metastasis) " +
//		                   "VALUES ("+ Integer.parseInt(id)+", "+i+","+Integer.parseInt(userRs.getString("UserID"))+", '"+userRs.getString("Password")+"','"+userRs.getString("Email")+"','"+userRs.getString("CreateDate")+"','"
//		                   +userRs.getString("UpdateDate")+"','"+userRs.getString("Username")+"','"+userRs.getString("Displayname")+"',"+Integer.parseInt(userRs.getString("FollowCount"))+","+Integer.parseInt(userRs.getString("Stage"))+",'"
//		                   +userRs.getString("Pathological")+"',"+Integer.parseInt(userRs.getString("IsSmoking"))+",'"+userRs.getString("gender")+"','"+userRs.getString("image")+"',"+Integer.parseInt(userRs.getString("Age"))+
//		                   ",'"+ userRs.getString("cancerType") + "','"+userRs.getString("metastasis")+"'"+                   
//		                   ")";
				int stage = 0;
				int isSmoking = 0;
				if (userRs.getString("Stage") != null){
					stage = Integer.parseInt(userRs.getString("Stage"));
				}
				if (userRs.getString("IsSmoking")!= null){
					isSmoking = Integer.parseInt(userRs.getString("IsSmoking"));
				}
//			    String sql = "INSERT INTO SuggestUserByTags (TagID, RankID, UserID, Password, Email,CreateDate, UpdateDate, Username, displayname, FollowCount, Stage, Pathological, IsSmoking, Gender, Image, Age, CancerType,metastasis) " +
//		                   "VALUES ("+ Integer.parseInt(id)+", "+i+","+Integer.parseInt(userRs.getString("UserID"))+", '"+userRs.getString("Password")+"','"+userRs.getString("Email")+"','"+userRs.getString("CreateDate")+"','"
//		                   +userRs.getString("UpdateDate")+"','"+userRs.getString("Username")+"','"+userRs.getString("Displayname")+"',"+Integer.parseInt(userRs.getString("FollowCount"))+","+stage+",'"
//		                   +userRs.getString("Pathological")+"',"+isSmoking+",'"+userRs.getString("gender")+"','"+userRs.getString("image")+"',"+Integer.parseInt(userRs.getString("Age"))+
//		                   ",'"+ userRs.getString("cancerType") + "','"+userRs.getString("metastasis")+"'"+                   
//		                   ")";
			    String sql = "INSERT INTO SuggestUserByTags (TagID, RankID, UserID, Password, Email,CreateDate, UpdateDate, Username, displayname, FollowCount, Stage, Pathological, IsSmoking, Gender, Age, CancerType,metastasis) " +
		                   "VALUES ("+ Integer.parseInt(id)+", "+i+","+Integer.parseInt(userRs.getString("UserID"))+", '"+userRs.getString("Password")+"','"+userRs.getString("Email")+"','"+userRs.getString("CreateDate")+"','"
		                   +userRs.getString("UpdateDate")+"','"+userRs.getString("Username")+"','"+userRs.getString("Displayname")+"',"+Integer.parseInt(userRs.getString("FollowCount"))+","+stage+",'"
		                   +userRs.getString("Pathological")+"',"+isSmoking+",'"+userRs.getString("gender")+"',"+Integer.parseInt(userRs.getString("Age"))+
		                   ",'"+ userRs.getString("cancerType") + "','"+userRs.getString("metastasis")+"'"+                   
		                   ")";
			    Statement stmt1 = conn.createStatement();
			    stmt1.executeUpdate(sql);
				i++;
			}
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}