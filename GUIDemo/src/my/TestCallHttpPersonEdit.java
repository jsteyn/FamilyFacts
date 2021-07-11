//package my;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class TestCallHttpPersonEdit {
//  public static void main(String[] args) {
//  httpURLPOSTCase("136");
//}
//	static void httpURLPOSTCase(String filter) {
//		String POST_URL = "http://3.9.172.108:8090/api/person/update/"+ filter;
//		try {
//			URL url = new URL(POST_URL);
//
//			// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接 (标识一个url所引用的远程对象连接)
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
//
//			// 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
//			connection.setDoOutput(true);
//
//			// 设置连接输入流为true
//			connection.setDoInput(true);
//
//			// 设置请求方式为post
//			connection.setRequestMethod("POST");
//
//			// post请求缓存设为false
//			connection.setUseCaches(false);
//
//			// 设置该HttpURLConnection实例是否自动执行重定向
//			connection.setInstanceFollowRedirects(true);
//
//			// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
//			// application/x-javascript text/xml->xml数据 application/x-javascript->json对象
//			// application/x-www-form-urlencoded->表单数据
//			// ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
//			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//			// 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
//			connection.connect();
//
//			// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
//			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
//
//			// 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
//			String parm = "first_name=test&last_name=person&sex=0&birth=1920&death=2010&address=Newcastle";
//			
//			StringBuilder sb1 = new StringBuilder();
//			
//			sb1.append("first_name=");
//			String first_name = firstname;
//			sb1.append(first_name);
//			
//			
//			sb1.append("&last_name=");
//			String last_name = lastname;
//			sb1.append(last_name);
//			
//			sb1.append("&sex=");
//			String sex1 = sex;
//			sb1.append(sex1);
//			
//			sb1.append("&birth=");
//			String birth1 = birth;
////			Integer birth1 = null;
////			if(birth!=null){
////				birth1 = Integer.valueOf(birth);
////			}
//			sb1.append(birth1);
//			
//			sb1.append("&death=");
//			String death1 = death;
////			Integer death1 = null;
////			if(death!=null){
////				death1 = Integer.valueOf(death);
////			}
//			sb1.append(death1);
//			
//			sb1.append("&address=");
//			String address1 = address;
//			sb1.append(address1);
//			
//			String s=sb1.toString();
//			System.out.println("------------------------");
//			System.out.println(s);
//			System.out.println("------------------------");
//			// 将参数输出到连接
//			dataout.writeBytes(s);
//
//			// 输出完成后刷新并关闭流
//			dataout.flush();
//			dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
//
//			// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
//			
//			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//			String line;
//			StringBuilder sb = new StringBuilder(); // 用来存储响应数据
//			System.out.println("--+++++++++++++++++----");
//			// 循环读取流,若不到结尾处
//			while ((line = bf.readLine()) != null) {
//				sb.append(line).append(System.getProperty("line.separator"));
//			}
//			bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
//			connection.disconnect(); // 销毁连接
//			System.out.println(sb.toString());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
//
