package Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebListener implements ServletContextListener 
{
	private ServletContext context = null;

	/* 这个方法在Web应用服务被移除，没有能力再接受请求的时候被调用。
	 */
	public void contextDestroyed(ServletContextEvent event){
		//Output a simple message to the server's console
		System.out.println("网站关闭");
		this.context = null;

	}

	// 这个方法在Web应用服务做好接受请求的时候被调用。
	public void contextInitialized(ServletContextEvent event){
		this.context = event.getServletContext();

		//Output a simple message to the server's console
		System.out.println("网站启动");
		System.out.println(context.getServerInfo());
		
		

	}
	class mythread extends Thread
	{
		public void run()
		{
			while (true) {
				System.out.println("网站运行时");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
