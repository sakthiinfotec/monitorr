#/usr/bin/env bash
APP_HOME=$(cd "`dirname $0`/.." && pwd)
echo "Starting system monitor application from ${APP_HOME} path"

# To load external configuration
JAVA_OPTS="${JAVA_OPTS} -Dlogging.config=${APP_HOME}/config/log4j.properties"

java $JAVA_OPTS -cp "$APP_HOME/config:$APP_HOME/lib/*" -jar $APP_HOME/lib/system-monitor-0.0.1-SNAPSHOT.jar "$@"