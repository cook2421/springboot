#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=springboot

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME을 profile=$IDLE_PROFILE로 실행합니다."
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE  \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &





# 기본적인 스크립트는 step2의 deploy.sh와 유사하다.
# 다른 점이라면 IDLE_PROFILE을 통해 properties 파일을 가져오고(application-#IDLE_PROFILE.properties), active profile을 지정하는 것(-Dspring.profiles.active=$IDLE_PROFILE) 뿐이다.
# 여기서도 IDLE_PROFILE을 사용하니 profile.sh을 가져와야 한다.