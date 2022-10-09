nohup java -jar -Xms64m -Xmx128m -Djava.util.concurrent.ForkJoinPool.common.parallelism=10 tools-dashboard-authorization.jar > tools-dashboard-authorization.out 2>&1 &
nohup java -jar -Xms64m -Xmx512m -Djava.util.concurrent.ForkJoinPool.common.parallelism=50 tools-dashboard-baidu.jar > tools-dashboard-baidu.out 2>&1 &
nohup java -jar -Djava.util.concurrent.ForkJoinPool.common.parallelism=50 tools-dashboard-aatool.jar > tools-dashboard-aatool.out 2>&1 &
nohup java -jar -Xms64m -Xmx256m -Djava.util.concurrent.ForkJoinPool.common.parallelism=10 tools-dashboard-web-facade.jar > tools-dashboard-web-facade.out 2>&1 &
nohup java -jar -Xms64m -Xmx128m -Dco.paralleluniverse.fibers.detectRunawayFibers=false tools-dashboard-myspace-cap.jar > tools-dashboard-myspace.out 2>&1 &