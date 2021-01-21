
# 应用程序名称
APP_NAME=test
# jar占用的端口号
SERVER_PORT=8888
# jar包的路径
JAR_PATH=/data/test
# jar包和启动脚本在同一目录下时，可以使用下面这个命令获取当前脚本所在的文件夹
# JAR_PATH=$(cd `dirname $0`;pwd)
# jar包名称
JAR_NAME=test.jar
# pid文件名
JAR_PID="$APP_NAME.pid"
# 日志输出文件夹(相对路径)
LOG_DIR=logs

#进入jar包所在的文件夹
cd $JAR_PATH

# 判断log文件夹是否存在，不存在则创建
if [ ! -d "$JAR_PATH/$LOG_DIR" ];then
  mkdir $JAR_PATH/$LOG_DIR
fi

# java虚拟机启动参数
JAVA_OPTS="-Xms512m -Xmx512m -XX:+PrintGCDetails -Dfile.encoding=utf-8";

# 检查程序是否处于运行状态
is_exist(){
  # 查询应用程序的进程id
  pid=$(ps -ef|grep $JAR_NAME|grep -v grep|awk '{print $2}')

  # []表示条件检测，-z STRING 表示这个字符串长度是否为0，为0返回真，即空返回真
  if [ -z "${pid}" ];then
    return 0
  else
    return 1
  fi
}

# 服务启动
start(){
  # 判断程序是否在运行
  is_exist
  # $? 表示最后运行的命令的结束代码(返回值)，在这里即表示is_exist的返回值
  if [ $? -eq "1" ];then
    echo "$APP_NAME is already running,pid is ${pid}"
  else
    echo "start $APP_NAME..."
    # 启动jar包
    nohup java -jar $JAVA_OPTS -Xloggc:$LOG_DIR/gclog.log --server.port=$SERVER_PORT $JAR_PATH/$JAR_NAME >$JAR_PATH/$LOG_DIR/run.log 2>&1 &
    # 将pid存到文件中
    # $! 表示shell最后运行的后台Process的PID（后台运行的最后一个进程的进程ID号）
    echo $! > $JAR_PATH/$JAR_PID
    # 打印日志
    tail -500f $JAR_PATH/$LOG_DIR/run.log
  fi
}

# 服务停止
stop(){
  # 获取进程id
  pidf=$(cat $JAR_PATH/$JAR_PID)
  echo "pid = $pidf begin kill -15 "
  # kill -15不会强制杀掉进程，会等待进程中线程任务处理完自动停止
  kill -15 "$pidf"
  sleep 10
  # 判断进程是否还存在
  is_exist
  if [ $? -eq "1" ];then
    echo "pid = $pid begin kill -9 "
    # kill -9 会强制杀掉进程，正在进行中任务也会强行停止
    kill -9 "$pid"
    sleep 5
    echo "$APP_NAME process stopped!"
  else
    echo "$APP_NAME has already stopped"
  fi
}

#查看服务运行状态
status(){
  is_exist
  if [ $? -eq "1" ];then
    echo "$APP_NAME is running,pid is ${pid}"
  else
    echo "$APP_NAME is not running!"
  fi
}

# 重启服务
restart(){
  # 先停止
  stop
  #再启动
  start
}

# 帮助说明，用于提示输入参数信息
help(){
  echo "Usage:sh run-service.sh [ start | stop | restart |status ]"
  exit 1
}

# 读取脚本的第一个参数$1，进行判断
# 参数读取范围，start|stop|restart|status
# 如果参数不在指定范围内，则打印帮助信息

# $0表示shell本身的文件名
# $1~$n表示添加到shell的各个参数，$1 表示第一个参数

# 根据输入的参数，选择对应的执行方法
case "$1" in
 'start')
 start
 ;;
 'stop')
 stop
 ;;
 "restart")
 restart
 ;;
 'status')
 status
 ;;
 *)
 help
 ;;
esac
exit 0
