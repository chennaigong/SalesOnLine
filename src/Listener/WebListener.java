package Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebListener implements ServletContextListener 
{
	private ServletContext context = null;

	/* ���������WebӦ�÷����Ƴ���û�������ٽ��������ʱ�򱻵��á�
	 */
	public void contextDestroyed(ServletContextEvent event){
		//Output a simple message to the server's console
		System.out.println("��վ�ر�");
		this.context = null;

	}

	// ���������WebӦ�÷������ý��������ʱ�򱻵��á�
	public void contextInitialized(ServletContextEvent event){
		this.context = event.getServletContext();

		//Output a simple message to the server's console
		System.out.println("��վ����");
		System.out.println(context.getServerInfo());
		
		

	}
	class mythread extends Thread
	{
		public void run()
		{
			while (true) {
				System.out.println("��վ����ʱ");
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
