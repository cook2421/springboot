#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springboot

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl springboot | grep jar | awk '{print $1}')       # (1)

echo "현재 구동 중인 애플리케이션pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME                                          # (2)

echo "> $JAR_NAME 실행"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &                # (3)


# (1) CURRENT_PID
#    - 현재 수행 중인 스프링 부트 애플리케이션의 프로세스 ID를 찾는다.
#    - 실행 중이면 종료하기 위해서이다.
#    - 스프링 부트 애플리케이션 이름(springboot)으로 된 다른 프로그램들이 있을 수 있어
#      springboot로 된 __jar(pgrep -fl springboot | grep jar)__ 프로세스를 찾은 뒤 ID를 찾는다.
#      __( | awk '{print $1}')__

# (2) chmod +x $JAR_NAME
#    - Jar 파일은 실행 권한이 없는 상태이이다.
#    - nohup으로 실행할 수 있게 실행 권한을 부여한다.

# (3) $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
#    - nohup 실행 시 CodeDeploy는 무한 대기이다.
#    - 이 이슈를 해결하기 위해 nohup.out 파일을 표준 입출력용으로 별도로 사용한다.
#    - 이렇게 하지 않으면 nohup.out 파일이 생기지 않고, CodeDeploy 로그에 표준 입출력이 출력된다.
#    - nohup이 끝나기 전까지 CodeDeploy도 끝나지 않으니 꼭 이렇게 해야만 한다.

# ps. step1과 step2 deploy 스크립트의 차이점은 step1에서 git pull을 통해 직접 빌드했던 부분을 제거했는 점이다.
#     그리고 실행하는 단계에서 몇 가지 코드가 추가되었다.