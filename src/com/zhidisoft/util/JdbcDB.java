package com.zhidisoft.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class JdbcDB {
	
	private static final String DRIVER_Y = "com.mysql.jdbc.Driver";
	
	private static final String URL_Y = "jdbc:mysql://localhost:3306/source"; 
	
	private static final String USER_Y = "root";
	
	private static final String PW_Y = "root";
	
	
	public static void main(String[] args) {
		//System.out.println(doExcuQuery("select * from t_student a ,t_teacher b where a.teacher_id = b.id"));
		/*List<Teacher> teaList = excuQuery(Teacher.class, "select * from t_teacher ");
		System.out.println(teaList);
		
		User user = new User();
		user.setDatecreate(new Date());
		user.setDateupdate(new Date());
		user.setId(6);
		update(user);*/
		/*update(entity)*/
	}
	
	
	private static Connection getConnection(){
		//加载驱动
		Connection conn = null;
		try {
			Class.forName(DRIVER_Y);
			conn = DriverManager.getConnection(URL_Y, USER_Y, PW_Y);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != ps){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void close(ResultSet rs,Statement st,Connection conn){
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != st){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//新增 INSERT into t_teacher (tea_name,tea_age) VALUES ( '胡老师',50)
	public static <T> long save(T entity){
		//
		String sql = " insert into " ;
		try {
		Class class1 = entity.getClass();
		Table t = (Table)class1.getAnnotation(Table.class);
		sql += t.name();
		
		String sql1 = "";
		String sql2 = "";
		Field[] fields = class1.getDeclaredFields();
		//tea_name,tea_age
		//'胡老师',50
		for(Field field : fields){
			//获取该属性上的注解的column 的name值
			Column column = field.getAnnotation(Column.class);
			if(!"id".equals(column.name())){
				sql1 +=column.name() + ",";
				//获取该属性的实际值
				field.setAccessible(true);
				Object object = field.get(entity);
				if("java.lang.String".equals(field.getType().getName())){
					sql2 += "'"+object+"',";
				}else if("java.util.Date".equals(field.getType().getName())){
					Date date = (Date) object;
					Timestamp ts = new Timestamp(date.getTime());
					sql2 += "'"+ts+"',";
				}else{
					sql2 += object +",";
				}
			}
		}
		//继续拼接
		//INSERT into t_teacher
		//(tea_name,tea_age) VALUES ( '胡老师',50)
		sql += "(";
		sql += sql1.substring(0, sql1.length()-1);
		sql += ") VALUES (";
		sql += sql2.substring(0, sql2.length()-1);
		sql += ")";
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			//System.out.println(sql);
			return doExcu(sql);
		}
	}
	
	//UPDATE t_teacher SET tea_name = "胡老师" , tea_age = 51 where id = 2
	public static <T> long update(T entity){
		String sql = " UPDATE ";
		int id = 0;
		try {
		Class class1 = entity.getClass();
		Table t = (Table)class1.getAnnotation(Table.class);
		sql += t.name();
		//UPDATE t_teacher
		sql += " set ";
		//tea_name = '胡老师' , tea_age = 51
		//id = 2
		String sql1 = "";
		String sql2 = "";
		Field[] fields = class1.getDeclaredFields();
		for(Field field :fields){
				field.setAccessible(true);
				Column column = field.getAnnotation(Column.class);
				if("id".equals(column.name())){//如果我现在处理的属性是id
					id =  (int) field.get(entity);
					sql2 += column.name() + " = " + field.get(entity);
				}else{//我遍历的属性不是id
					if("java.lang.String".equals(field.getType().getName())){
						sql1 += column.name() + " = '" + field.get(entity)+"',";
					}else if("java.util.Date".equals(field.getType().getName())){
						Date date = (Date) field.get(entity);
						Timestamp ts = new Timestamp(date.getTime());
						sql1 += column.name() + " = '" + ts+"',";
					}else{
						sql1 += column.name() + " = " + field.get(entity)+",";
					}
				}
		}
		//tea_name = '胡老师' , tea_age = 51
		sql += sql1.substring(0, sql1.length()-1);
		//UPDATE t_teacher set tea_name = '胡老师' , tea_age = 51
		sql += " where " ;
		//id = 2
		sql += sql2 ;
		
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			doExcu(sql);
			return id;
		}
	}
	
	//删除 delete form 表名 where id = 2
	public static <T> long delete(T entity){
		String sql = " delete from ";
		int id =0;
		try {
		
		Class class1 = entity.getClass();
		Table t = (Table)class1.getAnnotation(Table.class);
		sql += t.name();
		sql += " where " ;
		
		String sql1 = "";
		Field[] fields = class1.getDeclaredFields();
		for(Field field :fields){
				field.setAccessible(true);
				Column column = field.getAnnotation(Column.class);
				if("id".equals(column.name())){//如果我现在处理的属性是id
					id = (int) field.get(entity);
					sql1 += column.name() + " = " + field.get(entity);
				}
		}
		
		sql += sql1 ;
		
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			doExcu(sql);
			return id;
		}
	}
	
	//增删改
	public static long doExcu(String sql){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		//System.out.println(sql);
		int id = 0;
		try {
		//System.out.println(sql);
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//定义要做的事
		ps.executeUpdate();//执行
		rs = ps.getGeneratedKeys();
		while(rs.next()){
			id = rs.getInt(1);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs, ps, conn);
			return id;
		}
	}
	
	public static void doExcu1(String sql){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		long id = 0;
		try {
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//定义要做的事
		ps.executeUpdate();//执行
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs, ps, conn);
			
		}
	}
	
	public static <T> List<T> excuQuery(Class class1,String sql){
		Connection conn = getConnection();
		Statement st = null;
		ResultSet rs = null;
		List list = null;
		
		try {
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			 ResultSetMetaData md = rs.getMetaData();
				int t = md.getColumnCount();
				list = new ArrayList();
				Field[] fields = class1.getDeclaredFields();
				//对应属性的column对应值 是否与getColumnLabel(i+1)相等 
			while(rs.next()){
				Object obj = class1.newInstance();
				for (int i = 0; i < t; i++) {
					for(Field field : fields){
					//field.getAnnotation(Column.class).name()
						Column column  = field.getAnnotation(Column.class);
						String columnName = column.name();
						if (md.getColumnLabel(i+1).equals(field.getAnnotation(Column.class).name())){
							field.setAccessible(true);
							//System.out.println(rs.getObject(i+1));
							field.set(obj, rs.getObject(i+1));
						}
					}
				}
				//System.out.println(obj);
				list.add(obj);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, st, conn);
			return list;
		}
	}
	
	//查询
	public static List<Map<String,Object>> doExcuQuery(String sql){
		Connection conn = getConnection();
		Statement st = null;
		ResultSet rs = null;
		List list = null;
		try {
			 st = conn.createStatement();
			 rs = st.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int t = md.getColumnCount();
			list = new ArrayList();
			while(rs.next()){
				Map map = new HashMap();
				for (int i = 0; i < t; i++) {
					map.put(md.getColumnLabel(i+1), rs.getObject(i+1));
				}
				list.add(map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, null, conn);
		}
		return list;
	}
	//查询
		public static List<Map<String,String>> doExcuQuery2(String sql){
			Connection conn = getConnection();
			Statement st = null;
			ResultSet rs = null;
			List list = null;
			try {
				 st = conn.createStatement();
				 rs = st.executeQuery(sql);
				ResultSetMetaData md = rs.getMetaData();
				int t = md.getColumnCount();
				list = new ArrayList();
				while(rs.next()){
					Map map = new HashMap();
					for (int i = 0; i < t; i++) {
						map.put(md.getColumnLabel(i+1), rs.getObject(i+1));
					}
					list.add(map);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rs, null, conn);
			}
			return list;
		}
}
