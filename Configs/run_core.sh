nohup java -jar -Xms64m -Xmx128m tools-dashboard-discovery.jar > tools-dashboard-discovery.out 2>&1 &
nohup java -jar -Xms64m -Xmx128m tools-dashboard-api-gateway.jar > tools-dashboard-api-gateway.out 2>&1 &
nohup java -jar -Xms64m -Xmx128m tools-dashboard-config.jar > tools-dashboard-config.out 2>&1 &