#!/bin/bash
# service name
SERVICE=v30
# war 파일 경로
WAR_PATH=./target/${SERVICE}.jar
# 운영 서버 디플로이 경로
DIST_PATH=/var/${SERVICE}/${SERVICE}.jar
DIST_BASH_PATH=/root/${SERVICE}
# 개발/운영 서버 정보
if [ "release" = "$1" ]
then
	# 운영서버 IP
	echo "DIST=release"
	DIST_IPADDRS=(220.230.119.138 220.230.119.138 220.230.119.138 220.230.119.138 220.230.119.138 220.230.119.138 220.230.119.138 220.230.119.138)
	DIST_SSH_PORTS=(10122 10222 10322 10422 10522 10622 10722 10822)
	DIST_USER=root
else
	# 개발서버 IP
	echo "DIST=dev"
	DIST_IPADDRS=(220.230.125.110)
	DIST_SSH_PORTS=(22)
	DIST_USER=root
fi

for ((i = 0; i < ${#DIST_IPADDRS[@]}; ++i)); do
    # war 파일 정보 출력
    ls -l ${WAR_PATH}
    # war 파일을 원격 서버에 복사
    scp -vP ${DIST_SSH_PORTS[$i]} ${WAR_PATH} ${DIST_USER}@${DIST_IPADDRS[$i]}:${DIST_PATH} |& egrep "Sending file modes|Transferred"
    # 원격 서버의 쉘 스크립트 실행
    ssh -p ${DIST_SSH_PORTS[$i]} ${DIST_USER}@${DIST_IPADDRS[$i]} ${DIST_BASH_PATH}/restart.sh
done
