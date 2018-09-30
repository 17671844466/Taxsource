	package com.zhidisoft.util;
	
	import java.lang.annotation.Annotation;
	import java.lang.reflect.Field;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	
	import javax.print.attribute.standard.Fidelity;
	

	
	public class MySqlDB {
	
		public static void main(String[] args) {
			/*
			 * // 增加一条数据 String sqlC =
			 * "INSERT INTO student (t_no, t_name, t_age, t_sex) VALUES ('9527', '叶总', '18', '3')"
			 * ; // testSql(sqlC);
			 * 
			 * // 删除id为7的数据 String sqlR = "DELETE FROM zhidisoft.student WHERE t_id = '7'";
			 * // testSql(sqlR);
			 * 
			 * // 修改id为8的数据 String sqlU =
			 * "UPDATE zhidisoft.student SET t_name='陈总' , t_age='23' WHERE t_id = '8'"; //
			 * testSql(sqlU);
			 */
			String sqlD = "select * from student";
			/*
			 * //获取list集合 List<Map<String,Object>> list = chaSql(sqlD);
			 * //System.out.println(list); //将list集合进行迭代得到的是所有的map集合 Iterator<Map<String,
			 * Object>> it = list.iterator(); while (it.hasNext()) { //遍历所有的map集合
			 * Map<String, Object> map = it.next(); //System.out.println(map); }
			 */
			/*Student st = new Student();
			st.setName("龙");
			st.setAge(243);
			st.setSex(3);
			st.setNo("12");
			st.setId(10);
			long l = save(st);
			System.err.println(l);*/
			//xiuGai(st);
			//shanChu(12);
			/*List<Object> list = chaSql1(Student.class, sqlD);
			
			Iterator<Object> it = list.iterator();
			Student next = (Student) it.next();
			int age = next.getAge();
			long i = next.getId();
			String name = next.getName();
			String no = next.getNo();
			int sex = next.getSex();
			Student stu = new Student( i, no, name, age, sex);
			System.out.println(stu.getName());*/
			
		}


		public static List<Map<String, Object>> JieGuoJi() {
			String sql = "select * from tb_tax_payer";
			List<Map<String, Object>> chaSql = chaSql(sql);
			return chaSql;
		}
				
			
		//添加sql语句
		public static <T> long save(T entity) {
			
			// 拼接sql语句insert into 表名(字段名) values(值)
			String sql = "insert into ";
			// 创建一个class对象
			Class c1 = entity.getClass();
			// 获取该对象:类的Table注解
			Table an = (Table) c1.getAnnotation(Table.class);
			// System.out.println(an.name());
			sql += an.name();// 拼接为insert into student
			String sql1 = "";
			String sql2 = "";
			try {
				// 获得所有私有的属性
				Field[] f = c1.getDeclaredFields();
				for (Field fld : f) {
					// 获得所有属性的注解
					//System.err.println(fld+"---------------------------------------");
					Column an1 = fld.getAnnotation(Column.class);
					// System.err.println(an1.name());
					if (!"t_id".equals(an1.name())) {
						sql1 += an1.name() + ",";// 拼接为t_id,
						fld.setAccessible(true);
						Object obj = fld.get(entity);// 获取对象student中t_id的值
						// System.out.println(obj);
						if ("java.lang.String".equals(fld.getType().getName())) {
							// System.err.println(fld.getType().getName());
							sql2 += "'" + obj + "',";
							// System.err.println(sql2);
						} else {
							sql2 += obj + ",";
						}
					}
				}
				/*
				 * "INSERT INTO student (t_no, t_name, t_age, t_sex) VALUES ('9527', '叶总', '18', '3')"
				 */
				sql1 = sql1.substring(0, sql1.length() - 1);
				sql2 = sql2.substring(0, sql2.length() - 1);
				sql2 = sql + "(" + sql1 + ")values(" + sql2 + ")";
				 

	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return testSql1(sql2);
			
		}
			
		//删除sql语句
		public static <T> void shanChu(long i,T entity) {
			String sql = "delete from ";
			Class c1 = entity.getClass();
			Table an = (Table) c1.getAnnotation(Table.class);
			// System.err.println(an.name());
	
			try {
				Field ido = c1.getDeclaredField("id");
				String id = ido.getAnnotation(Column.class).name();
				// Table id = (Table) ido.getAnnotation(Column.class);
				// System.err.println(id.name());
			/*
			 			"delect from " "student" "where" "t_id" "=" "'"+7+"'"
			 */
				String sql1 = sql + an.name() + " where " + id + " = " + i + ";";
				System.out.println(sql1);
				 testSql1(sql1);
				 //System.out.println(l);
	
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
	
		}
			
			//修改sql语句
		public static <T> long xiuGai(T entity) {
			long l=1;
			String sql = "update ";
			Class c1 = entity.getClass();
			Table an = (Table) c1.getAnnotation(Table.class);//an.name()=student
			sql = sql+an.name()+" set ";
			//System.err.println(sql);
			String sql1="";
			String sql2="";
			try {
				// 获得所有私有的属性
				Field[] f = c1.getDeclaredFields();
				for (Field fld : f) {
					// 获得所有属性的注解
					Column an1 = fld.getAnnotation(Column.class);
					 //System.out.println(an1.name());
						sql1 = an1.name();// 拼接为t_id,		
						fld.setAccessible(true);
						Object obj = fld.get(entity);// 获取对象student中属性的值
						if ("java.lang.String".equals(fld.getType().getName())) {
							sql2 += sql1+"="+"'" + obj + "',";
						}else {
							sql2 += sql1+"="+obj+",";
						}
							
						
				}
				//"UPDATE student SET t_name='陈总' , t_age='23' WHERE t_id = '8'";
				Field ido = c1.getDeclaredField("id");
				String id = ido.getAnnotation(Column.class).name();
				ido.setAccessible(true);
				Object obj = ido.get(entity);
				
				sql2=sql2.substring(0, sql2.length()-1);
				
				sql2= sql + sql2 + " where " + id + "=" +  obj + ";";
				//System.out.println(sql2);
				 testSql1(sql2);
				//System.out.println(sql12);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return l;
			
			
	
		}

		
		// 查询表//testSql(sqlD);//返回一个list对象,里面存的是student对象
				public static <T> List<T> chaSql1(Class cls,String sql) {
					Connection conn = null;
					Statement st = null;
					ResultSet rs = null;
					List list = null;
					try {
						conn = getConnection();
						st = conn.createStatement();
						rs = st.executeQuery(sql);
						// 获取Sql执行完毕返回的数据表结构
						ResultSetMetaData md = rs.getMetaData();
						// 获取表字段数量
						int ccount = md.getColumnCount();
						list = new ArrayList();
						// md.getColumnLabel(i+1)获得字段的名称
						Field[] fies = cls.getDeclaredFields();
						while (rs.next()) {
							//创建一个cls的对象
							Object obj = cls.newInstance();
							for (int i = 0; i < ccount; i++) {
								for (Field fie : fies) {
									//获取对应属性的标签是否与getColumnLabel(i + 1)相等
									if (md.getColumnLabel(i+1).equals(fie.getAnnotation(Column.class).name())) {
										fie.setAccessible(true);
										fie.set(obj, rs.getObject(i+1));										
									}
								}
								
							}
							list.add(obj);
	
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						closeSql(conn, st, rs);
						return list;
					}
			
				}


			// 查询表//testSql(sqlD);//返回一个list对象,里面存的是map集合
		public static List<Map<String, Object>> chaSql(String sql) {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			List list = null;
			try {
				list = new ArrayList();
				conn = getConnection();
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				// 获取Sql执行完毕返回的数据表结构
				ResultSetMetaData md = rs.getMetaData();
				// 获取表字段数量
				int ccount = md.getColumnCount();
				// md.getColumnLabel(i+1)获得字段的名称
				while (rs.next()) {
					// 创建一个键值对的map集合
					Map<String, Object> map = new HashMap();
	
					for (int i = 0; i < ccount; i++) {
						// 根据表字段名称获得对应的值
						map.put(md.getColumnLabel(i + 1), rs.getString(md.getColumnLabel(i + 1)));
						//String c1 = md.getColumnLabel(i + 1);
						//String c2 = rs.getString(md.getColumnLabel(i + 1));
	
					}
					/*
					 * map.put("id", rs.getString("t_id")); map.put("name", rs.getString("t_name"));
					 * map.put("no", rs.getString("t_no")); map.put("age", rs.getInt("t_age"));
					 * map.put("sex", rs.getInt("t_sex"));
					 */
					// 将map集合添加到list里面
					list.add(map);
					/*
					 * String id = rs.getString("t_id"); String name = rs.getString("t_name");
					 * String no = rs.getString("t_no"); int age = rs.getInt("t_age"); int sex =
					 * rs.getInt("t_sex"); System.out.println("id是" + id + "名字:" + name + "编号:" + no
					 * + "年纪:" + age + "性别:" + sex);
					 */
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(conn, st, rs);
				return list;
			}
	
		}
	
		private static void closeSql(Connection conn, Statement st, ResultSet rs) {
			if (null != rs) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (null != st) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
		// 数据库操作
		private static void testSql(String sql) {
			Statement st = null;
			Connection conn = null;
			// 引入驱动
			conn = getConnection();
			st = null;
			try {
				// 创建Statement对象发送sql语句
				st = conn.createStatement();
				// 执行Sql语句
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(conn, st, null);
			}
		}
	
		// 数据库操作
		public static long testSql1(String sql) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			long id = 0;
			// 引入驱动
			conn = getConnection();
			try {
				// 创建一个默认 PreparedStatement 对象，该对象能获取自动生成的键。
				ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				// 执行sql语句的方法,必须是增删改
				ps.executeUpdate();
				// 获取由于执行此 Statement 对象而创建的所有自动生成的键,如果此 Statement 对象没有生成任何键，则返回空的 ResultSet 对象。
				rs = ps.getGeneratedKeys();
				while (rs.next()) {
					id = rs.getLong(1);
				}
			} catch (SQLException e) {
	
				e.printStackTrace();
			} finally {
				closeSql(conn, ps, rs);
			}
			return id;
	
		}
	
		// 加载JDBC数据库驱动
		private static Connection getConnection() {
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/source", "root", "root");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
	}
