package zovlzhongguanhua.demo.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.concurrent.Executor;

public class JettyStarter {

    public static void main(String[] args) {

        String jettyXmlPath = "./jetty-demo/jetty/etc/jetty.xml";
        String webdefaultXmlPath = "./jetty-demo/jetty/etc/webdefault.xml";
        String contextPath = "/testContext";
        String warPath = null;

        String webPath = "/web";
        String webXmlPath = "/web/WEB-INF/web.xml";

        Server server = new Server();
        WebAppContext context = new WebAppContext();

        try {
            InputStream is = new FileInputStream(jettyXmlPath);
            XmlConfiguration configuration = new XmlConfiguration(is);
            configuration.configure(server);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ContextHandlerCollection handler = new ContextHandlerCollection();
        context.setContextPath(contextPath);
        context.setDefaultsDescriptor(webdefaultXmlPath);
        if (warPath != null) {
            context.setWar(warPath);
        } else {
            context.setResourceBase(webPath);
            context.setDescriptor(webXmlPath);
        }
        handler.addHandler(context);
        handler.addLifeCycleListener(new LifeCycle.Listener() {

            @Override
            public void lifeCycleStarting(LifeCycle lifeCycle) {
                System.out.println("lifeCycleStarting：lifeCycle=" + lifeCycle);
            }

            @Override
            public void lifeCycleStarted(LifeCycle lifeCycle) {
                System.out.println("lifeCycleStarted：lifeCycle=" + lifeCycle);
            }

            @Override
            public void lifeCycleFailure(LifeCycle lifeCycle, Throwable throwable) {
                System.out.println("lifeCycleStarted：lifeCycleFailure=" + lifeCycle);
            }

            @Override
            public void lifeCycleStopping(LifeCycle lifeCycle) {
                System.out.println("lifeCycleStopping：lifeCycleFailure=" + lifeCycle);
            }

            @Override
            public void lifeCycleStopped(LifeCycle lifeCycle) {
                System.out.println("lifeCycleStopped：lifeCycleFailure=" + lifeCycle);
            }
        });
        server.setHandler(handler);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        printServer(server);
    }

    private static void printServer(Server server) {
        System.out.println("server=" + server);

        ThreadPool threadPool = server.getThreadPool();
        System.out.println("threadPool=" + threadPool);

        Enumeration<String> enumeration = server.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String s = enumeration.nextElement();
            System.out.println(s + "=" + server.getAttribute(s));
        }

        Connector[] connectors = server.getConnectors();
        for (Connector connector : connectors) {
            String name = connector.getName();
            Executor executor = connector.getExecutor();
            long idleTimeout = connector.getIdleTimeout();
            System.out.println("connector=" + name);
            System.out.println("connector=" + executor);
            System.out.println("connector=" + idleTimeout);
        }

        Handler handler = server.getHandler();
        System.out.println("handler=" + handler);

        SessionIdManager sessionIdManager = server.getSessionIdManager();
        String workerName = sessionIdManager.getWorkerName();
        System.out.println("sessionIdManager=" + sessionIdManager);
        System.out.println("workerName=" + workerName);

        long stopTimeout = server.getStopTimeout();
        System.out.println("stopTimeout=" + stopTimeout);

        String state = server.getState();
        System.out.println("state=" + state);
    }
}
