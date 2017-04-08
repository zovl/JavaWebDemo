package zovlzhongguanhua.demo.thread;

import org.quartz.*;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SchedulerDemo {

    private static final Logger LOGGER = Logger.getLogger("Scheduler");

    public static void main(String[] args) {

        // 1.从现在开始n秒钟之后，每隔n秒钟执行一次job
        // 2.每个星期二执行一次job
        // 3.每个月15好执行一次job

        // newTimer();
        // newService();

        quartz();
    }

    private static void quartz() {

        JobListener jobListener = new JobListener() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public void jobToBeExecuted(JobExecutionContext context) {

            }

            @Override
            public void jobExecutionVetoed(JobExecutionContext context) {

            }

            @Override
            public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

            }
        };

        try {
            SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.addJobListener(jobListener);
            scheduler.addGlobalJobListener(jobListener);
            scheduler.start();
            // 创建一个JobDetail，指明name，groupName，Job类名，
            // 该Job负责定义需要执行任务
            JobDetail jobDetail = new JobDetail("myJob", "myJobGroup", OutJob.class);
            jobDetail.getJobDataMap().put("type", "FULL");
            // 创建一个每周触发的Trigger，指明星期几几点几分执行
            Trigger trigger = TriggerUtils.makeWeeklyTrigger(6, 10, 49);
            // Trigger trigger = TriggerUtils.makeImmediateTrigger(10, 1000);
            trigger.setGroup("myTriggerGroup");
            // 从当前时间的下一秒开始执行
            trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
            // 指明Trigger的name
            trigger.setName("myTrigger");
            // 用Scheduler将JobDetail与Trigger关联在一起，开始调度任务
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class OutJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Generating report - "
                    + context.getJobDetail().getFullName() + ", type ="
                    + context.getJobDetail().getJobDataMap().get("type"));
            System.out.println(new Date().toString());

            LOGGER.info("Generating report - "
                    + context.getJobDetail().getFullName() + ", type ="
                    + context.getJobDetail().getJobDataMap().get("type"));
            LOGGER.info(new Date().toString());
        }
    }

    private static void newService() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job
        service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledExecutorService schedule：job-1 every 1 sec after 1 sec");
                    }
                }, 1, 1, TimeUnit.SECONDS);
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job
        service.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledExecutorService schedule：job-2 every 2 secs after 2 secs");
                    }
                }, 2, 2, TimeUnit.SECONDS);
    }

    private static void newTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            private int index;

            @Override
            public void run() {
                ++index;
                System.out.println("Timer Task：" + index);
            }
        };
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
